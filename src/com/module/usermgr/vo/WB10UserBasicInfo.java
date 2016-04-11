package com.module.usermgr.vo;

public class WB10UserBasicInfo {

	private String user_name;
	private String real_name;
	private String id_no;
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
	@Override
	public String toString() {
		return "WB10UserBasicInfo [user_name=" + user_name + ", real_name="
				+ real_name + ", id_no=" + id_no + "]";
	}

}
