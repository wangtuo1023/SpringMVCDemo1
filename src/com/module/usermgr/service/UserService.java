package com.module.usermgr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.core.BaseService;
import com.core.exception.ServiceException;
import com.module.usermgr.dao.UserDao;
import com.module.usermgr.vo.WB10User;

@Service("userService")
public class UserService implements BaseService {

	@Autowired
	private UserDao userDao;

	// public void signUp(WB10User user) throws ServiceException {
	// try {
	// user.setSignUpTime(new Date());
	// userMgrDao.addUser(user);
	// } catch (Exception e) {
	// throw new ServiceException("注册失败", e);
	// }
	//
	// }

	@Override
	public List<WB10User> getUsers() throws ServiceException {
		try {
			return userDao.getUsers();
		} catch (Exception e) {
			throw new ServiceException(">>> UserService.getUsers()方法，获取失败", e);
		}
	}

	@Override
	public WB10User getUserByUserName(String userName) throws ServiceException {
		try {
			return userDao.getUserByColunm("user_name", userName);
		} catch (Exception e) {
			if (e instanceof EmptyResultDataAccessException) {
				return null;
			}
			throw new ServiceException(
					">>> UserService.getUserByUserName(...)方法，获取失败", e);
		}
	}
	
	
	@Override
	public WB10User getUserByIdNo(String idNo) throws ServiceException {
		try {
			return userDao.getUserByColunm("id_no", idNo);
		} catch (Exception e) {
			if (e instanceof EmptyResultDataAccessException) {
				return null;
			}
			throw new ServiceException(
					">>> UserService.getUserByIdNo(...)方法，获取失败", e);
		}
	}
	
	
	
	public Object queryUserBySP(String spName , String userName){
		
		List<Object> inParamList = new ArrayList<Object>();
		inParamList.add(userName);
		
//		List<String> resultList = userDao.callSP(spName, inParamList);
		List<String> resultList = userDao.callSP(spName, inParamList);
		
		
		return resultList;
	}
	

}
