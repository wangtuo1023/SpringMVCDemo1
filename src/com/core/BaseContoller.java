package com.core;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * action ����
 * 
 * @author joe
 * @date 2011-10-24 ����03:29:32
 */
public class BaseContoller {

	public static final String COMMON_FAIL_PAGE = "fail";
	public static final String COMMON_FAIL_ALERT_KEY = "fail_key";
	public static final String USER_SESSION_KEY = "_user_session_key_";

	// ֱ�ӷ���jsp
	@RequestMapping("/{jsp}.htm")
	public String jsp(@PathVariable(value = "jsp") String jsp) {
		System.out.println(">>> jsp=" + jsp);
		return jsp;
	}
}
