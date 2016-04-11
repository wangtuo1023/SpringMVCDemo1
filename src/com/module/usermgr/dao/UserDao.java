package com.module.usermgr.dao;

import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.stereotype.Repository;

import com.cars.ict.otsweb.Constants;
import com.core.BaseDao;
import com.module.usermgr.vo.WB10User;
import com.module.usermgr.vo.UserMapper;

@Repository("userDao")
public class UserDao extends BaseDao {

	// public WB10User addUser(final WB10User sUser) {
	// sUser.setId(super.saveAndReturnKey(sUser).toString());
	// return sUser;
	// }

	public List<WB10User> getUsers() {
		// return getJdbcTemplate().query("select *  from web..WB10_user",
		// new BeanPropertyRowMapper(WB10User.class));
		return getJdbcTemplate().query("select *  from web..WB10_user",
				new UserMapper());
	}

	public WB10User getUserByColunm(String columnName, Object value) {
		// return getJdbcTemplate().queryForObject(
		// "select *  from web..WB10_user where " + columnName + "=?",
		// new BeanPropertyRowMapper(SUser.class), value);
		return getJdbcTemplate().queryForObject(
				"select *  from web..WB10_user where " + columnName + "=?",
				new UserMapper(), value);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public List<String> callSP(final String spName,
			final List<Object> inParamList) {

		return (List<String>) getJdbcTemplate().execute(spName,
				new CallableStatementCallback() {
					public List<String> doInCallableStatement(
							CallableStatement cs) throws SQLException,
							DataAccessException {
						String executeStatus = "0";// 0代表执行成功 1代码执行发生业务异常
													// 2代码发生系统异常
						StringBuilder inSb = new StringBuilder();// 入参
						StringBuilder resultSb = new StringBuilder();// 出参
						if (inParamList != null) {
							int index = 1;
							for (Object param : inParamList) {
								cs.setObject(index,
										UserDao.transformParams(param)); // 转码，UTF-8转成数据库需要的ISO-8859-1
								index++;
								if (param != null) {
									inSb.append(param.toString());
								}
								inSb.append("::::");
							}
						}
						List<String> returnList = new ArrayList<String>();
						ResultSet rs = null;
						long time = System.currentTimeMillis();
						try {
							rs = cs.executeQuery();
							ResultSetMetaData rsmd = rs.getMetaData();
							int columnCount = rsmd.getColumnCount();

							while (rs.next()) {
								StringBuffer sb = new StringBuffer();
								for (int i = 1; i < columnCount; i++) {
									// 存储过程处理结果集：trim()、转码、null转成空字符串
									sb.append(UserDao.transformRs(rs
											.getObject(i))
											+ Constants.SPLIT_CODE);
								}
								sb.append(UserDao.transformRs(rs
										.getObject(columnCount)));
								returnList.add(sb.toString());
							}

							if (returnList != null && returnList.size() != 0) {
								for (int i = 0; i < returnList.size(); i++) {
									resultSb.append(returnList.get(i));
									resultSb.append("::::");
								}
								String[] fields = returnList.get(0).split(
										Constants.SPLIT_CODE);
								if (fields.length == 2) {
									if (Constants.BUSINESS_EX_CODE
											.equals(fields[0])) {
										executeStatus = "1";
									} else {
										executeStatus = "2";
									}
								}
							} else {
								resultSb.append("SP_Result is null or size is 0");
							}

						} catch (SQLException e) {
							executeStatus = "2";
							resultSb.append(e.toString());
							throw new RuntimeException();
						} finally {
							time = System.currentTimeMillis() - time;
							try {
							} catch (Exception e) {
							}
							try {
								int maxTimeOut = 300 * 1000;// 与外网ws
															// client的超时时间一致
								if (time >= maxTimeOut) {
									StringBuilder sb = new StringBuilder(
											"Db webTimeout excute sql storeName [");
									sb.append(spName);
									sb.append("] ");
									sb.append(maxTimeOut);
									sb.append("ms");
								}
							} catch (Throwable e) {

							}
							if (rs != null) {
								try {
									rs.close();
								} catch (SQLException e) {
									throw new RuntimeException();
								}
							}
						}
						// 添加返回两列结果集的判断处理（业务异常）
						checkReturnList(returnList);
						return returnList;
					}
				});
	}

	public static Object transformParams(Object object) {
		if (null == object || "null".equals(object)) {
			// 若是null或者"null"字符串则替换成空串 入口参数校验
			object = "";
		} else {
			if (object instanceof String) {
				object = UTF8ToISO(object.toString()); // UTF-8转ISO-8859-1
				object = object.toString().trim(); // trim()
			}
		}
		return object;
	}

	public static String UTF8ToISO(String str) {
		try {
			return new String(str.getBytes("GB18030"), "iso8859-1");
			// return new String(str.getBytes(), "iso8859-1");
		} catch (UnsupportedEncodingException e) {
			// logger.error(
			// "Convert Encoding from  UTF8 To ISO Error.OtsUtils.UTF8ToISO() fail. str="
			// + str, e);
			return str;
		}
	}

	public static String transformRs(Object obj) {
		if (null == obj || "null".equals(obj)) { // "null"字符串转成空字符串
			obj = "";
		} else if (obj instanceof String) {
			obj = ISOToUTF8(((String) obj).trim());
		}

		return obj.toString();
	}

	public static String ISOToUTF8(String str) {
		try {
			return new String(str.getBytes("iso8859-1"), "GB18030");
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}

	protected void checkReturnList(List<String> returnList) {
		if (returnList != null && returnList.size() > 0) {
			String[] fields = returnList.get(0).split(Constants.SPLIT_CODE);
			if (fields.length == 2) {
				if (Constants.BUSINESS_EX_CODE.equals(fields[0])) { // 1.业务异常
					throw new RuntimeException();
				} else { // 2.系统异常
					throw new RuntimeException();
				}
			}

		}
	}
}
