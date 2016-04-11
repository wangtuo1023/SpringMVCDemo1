package com.command;

import org.springframework.beans.factory.annotation.Autowired;

import com.core.exception.ServiceException;
import com.module.usermgr.service.UserService;

public class QueryUser {

	@Autowired
	private UserService userMgrService;
	
	public static void main(String[] args) {
		QueryUser q = new QueryUser();
		q.query();
	}
	
	
	private void query(){
		try {
			userMgrService.getUserByUserName("wangtuo1023");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
