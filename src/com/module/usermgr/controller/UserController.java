package com.module.usermgr.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.core.BaseContoller;
import com.core.exception.ActionException;
import com.core.exception.ServiceException;
import com.module.usermgr.service.UserService;
import com.module.usermgr.vo.WB10User;

// 窄化请求映射：RequestMapping根路径：为了对url进行分类管理，可以在这里定义根路径，最终访问url是根路径+子路径。
// 在@Controller注解后紧跟@RequestMapping注解，写上根路径。
@Controller
@RequestMapping(value = "usermgr")
public class UserController extends BaseContoller {

	@Autowired
	private UserService userService;

	// 获取单个用户
	// 2016.01.24 GET请求会把 userName 放到url中，这样只要更换 userName 就能查看到用户信息了。
	@RequestMapping(value = "get/{userName}.htm", method = RequestMethod.GET)
	public ModelAndView get(@PathVariable String userName) throws Exception {
		userName = StringUtils.trim(userName);
		WB10User user = userService.getUserByUserName(userName);
		if (user == null) {
			throw new ActionException("用户[" + userName + "]不存在!");
		}
		return new ModelAndView("user_detail", "user", user);
	}

	// 获取全部用户
	@RequestMapping(value = "list.htm", method = RequestMethod.GET)
	public ModelAndView list() throws ServiceException {
		List<WB10User> users = userService.getUsers();
		return new ModelAndView("user_list", "users", users);
	}

	// 注册成功
	@RequestMapping(value = "signup_succ/{userName}.htm")
	public String singnupSuccess(@PathVariable("userName") String userName,
			ModelMap modelMap) {
		modelMap.addAttribute("userName", userName);
		return "signup_succ";
	}
}
