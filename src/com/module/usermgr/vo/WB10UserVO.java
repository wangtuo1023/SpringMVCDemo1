package com.module.usermgr.vo;

/**
 * 用户包装对象，包括基本信息和联系方式信息。
 * 
 * @author WANGTUO-PC
 * 
 */
public class WB10UserVO {

	private WB10User wb10User;
	private WB10UserBasicInfo wb10UserBasicInfo;
	private WB10UserContactInfo wb10UserContactInfo;

	public WB10User getWb10User() {
		return wb10User;
	}
	public void setWb10User(WB10User wb10User) {
		this.wb10User = wb10User;
	}
	public WB10UserBasicInfo getWb10UserBasicInfo() {
		return wb10UserBasicInfo;
	}
	public void setWb10UserBasicInfo(WB10UserBasicInfo wb10UserBasicInfo) {
		this.wb10UserBasicInfo = wb10UserBasicInfo;
	}
	public WB10UserContactInfo getWb10UserContactInfo() {
		return wb10UserContactInfo;
	}
	public void setWb10UserContactInfo(WB10UserContactInfo wb10UserContactInfo) {
		this.wb10UserContactInfo = wb10UserContactInfo;
	}

}
