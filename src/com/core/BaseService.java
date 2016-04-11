package com.core;

import java.util.List;

import com.core.exception.ServiceException;
import com.module.usermgr.vo.WB10User;

public interface BaseService {

	// 查询全部用户
	public List<WB10User> getUsers() throws ServiceException;

	// 查询单个用户
	public WB10User getUserByUserName(String userName) throws ServiceException;

	// 查询用户，按照证件号码
	public WB10User getUserByIdNo(String idNo) throws ServiceException;

}
