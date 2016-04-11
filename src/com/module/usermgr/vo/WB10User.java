package com.module.usermgr.vo;

import java.util.Date;

import com.core.Saveable;

public class WB10User implements Saveable {

	private String user_name;
	private String real_name;
	private String id_no;
	private String email;
	private String mobile_no;
	private Date regist_time;

	private static String[] keyColumns = {"user_name"};
	private static String tableName = "WB10_user";
	@Override
	public String getTableName() {
		return tableName;
	}
	@Override
	public String[] getKeyColumns() {
		return keyColumns;
	}

	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getId_no() {
		return id_no;
	}
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public Date getRegist_time() {
		return regist_time;
	}
	public void setRegist_time(Date regist_time) {
		this.regist_time = regist_time;
	}

	@Override
	public String toString() {
		return "WB10User [user_name=" + user_name + ", real_name=" + real_name
				+ ", id_no=" + id_no + ", email=" + email + ", mobile_no="
				+ mobile_no + ", regist_time=" + regist_time + "]";
	}

}
