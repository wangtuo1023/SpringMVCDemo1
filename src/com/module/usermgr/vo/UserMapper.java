package com.module.usermgr.vo;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<WB10User> {

	@Override
	public WB10User mapRow(ResultSet rs, int rowNum) throws SQLException {

		WB10User user = new WB10User();
		try {
			user.setEmail(StringUtils.trim(rs.getString("email")));
			user.setId_no(StringUtils.trim(rs.getString("id_no")));
			user.setMobile_no(StringUtils.trim(rs.getString("mobile_no")));
//			user.setReal_name(StringUtils.trim(new String(rs.getString("real_name").getBytes(),
//					"ISO8859-1")));
			user.setReal_name(StringUtils.trim(new String(rs.getString(
					"real_name").getBytes("ISO8859-1"), "GBK")));
			user.setRegist_time(rs.getDate("regist_time"));
			user.setUser_name(StringUtils.trim(rs.getString("user_name")));

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return user;
	}

}
