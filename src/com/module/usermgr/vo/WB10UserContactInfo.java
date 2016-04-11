package com.module.usermgr.vo;

public class WB10UserContactInfo {

	private String user_name;
	private String email;
	private String mobile_no;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
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
	@Override
	public String toString() {
		return "WB10UserContactInfo [user_name=" + user_name + ", email="
				+ email + ", mobile_no=" + mobile_no + "]";
	}

}
