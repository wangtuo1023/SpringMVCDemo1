/*
 * SimpleCallTemplate.java
 * 版权所有，中国铁道科学研究院，2009-2010。
 * 本文件以及任何本文件的转化形式只限在中国铁道科学研究院内部使用。
 */
package com.util;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cars.ict.otsweb.Constants;
import com.module.usermgr.dao.UserDao;

/**
 * 调用存储过程
 * 
 * @author yushanyuan
 * @version 0.1
 * @date 2010-5-26 下午03:43:10
 */
public class SimpleCallTemplate extends BaseTemplate
		implements
			SimpleCallTemplateOperations {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}

	/**
	 * 执行
	 * 
	 * @author yushanyuan
	 * @param storeName
	 * @param inParamList
	 * @return
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public List<String> execute(final String storeName,
			final List<Object> inParamList) {

		return (List<String>) getJdbcTemplate().execute(storeName,
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
//								if (log.isDebugEnabled()) {
//									String returnTime = DateUtils
//											.getSysTimeStr_yyyyMMddHHmmssSSS();
//									log.debug("ReturnTime:"
//											+ returnTime
//											+ ".The Result Returned by Tran/Query DB SP :"
//											+ sb.toString());
//								}
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
							log.error(
									"SimpleCallTemplate Execute get SQLException!!! The SQL command to DB SP: "
											+ inSb.toString(), e);
							// throw new SystemOtsException(e);
							throw new RuntimeException();
						} finally {
							time = System.currentTimeMillis() - time;
							try {
								// SPLogger.loggerSP(storeName, time,
								// executeStatus, inSb.toString(),
								// resultSb.toString());
							} catch (Exception e) {
								log.error("log splogger failed.");
							}
							try {
								int maxTimeOut = 300 * 1000;// 与外网ws
															// client的超时时间一致
								if (time >= maxTimeOut) {
									StringBuilder sb = new StringBuilder(
											"Db webTimeout excute sql storeName [");
									sb.append(storeName);
									sb.append("] ");
									sb.append(maxTimeOut);
									sb.append("ms");
									log.error(sb.toString());
								}
//								SqlExcuteTimeCollection.instance()
//										.updateDbExcuteTime(time);
							} catch (Throwable e) {

							}
							if (rs != null) {
								try {
									rs.close();
								} catch (SQLException e) {
									log.error(
											"SimpleCallTemplate Close Result Set get  SQLException.The Parameter is: "
													+ inSb.toString(), e);
									// throw new SystemOtsException(e);
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
}