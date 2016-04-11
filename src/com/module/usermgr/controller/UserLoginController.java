package com.module.usermgr.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.module.usermgr.vo.WB10UserVO;
/**
 * 用户登录管理
 * 
 * @author joe
 * @date 2011-10-24 下午03:34:39
 */
@Controller
public class UserLoginController extends BaseContoller {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "saveLogin.htm", method = RequestMethod.POST)
	// @RequestMapping(value = "saveLogin.htm", method = RequestMethod.GET)
	// 绑定pojo：WB10User对象的user_name属性。
	// 如果Controller方法的形参中也有个同名的形参，那么也可以绑定上。 简单类型参数绑定与pojo参数绑定互不影响。
	public ModelAndView saveLogin(HttpSession session, WB10UserVO userVO,
			ModelMap modelMap, String user_name) {
		if (userVO != null) {
			System.out.println(">>> user_name=" + user_name);
			String userName = userVO.getWb10UserBasicInfo().getUser_name();
			String idNo = userVO.getWb10UserBasicInfo().getId_no();
			String mobileNo = userVO.getWb10UserContactInfo().getMobile_no();
			try {
				WB10User dbuser = null;
				if (!StringUtils.isBlank(userName))
					// dbuser = (WB10User)
					// userService.queryUserBySP("{call query_user_by_user_name(?)}",userName);
					dbuser = userService.getUserByUserName(userName);
				if (!StringUtils.isBlank(idNo))
					dbuser = userService.getUserByIdNo(idNo);
				// if (!StringUtils.isBlank(mobileNo))
				// userService
				// .queryUserByStoreProcedure("{call test_call_sp(?)}");

				if (dbuser == null) {
					// 成功后重定向的欢迎界面，防止重复提交
					return new ModelAndView("login", COMMON_FAIL_ALERT_KEY,
							"用户不存在!");
				}

				session.setAttribute(USER_SESSION_KEY, dbuser);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		// 成功后重定向的欢迎界面，防止重复提交
		// 重定向到login_succ.htm，就进入到了signuoSucc(HttpSession session, ModelMap
		// modelMap)方法中。
		return new ModelAndView("redirect:login_success.htm");
	}

	// 获取
	@RequestMapping(value = "get/{id}.htm", method = RequestMethod.GET)
	public ModelAndView get(@PathVariable String userName) throws Exception {
		WB10User user = userService.getUserByUserName(userName);
		if (user == null) {
			throw new ActionException("用户[" + userName + "]不存在!");
		}
		return new ModelAndView("user_detail", "user", user);
	}

	// 列表
	@RequestMapping(value = "list.htm", method = RequestMethod.GET)
	public ModelAndView list() throws ServiceException {
		List<WB10User> users = userService.getUsers();
		return new ModelAndView("user_list", "users", users);
	}

	// 登录成功
	// 通过形参中的ModelMap将model数据传至页面，相当于ModelAndView.addObject方法
	@RequestMapping(value = "login_success.htm")
	public String loginSuccess(HttpSession session, ModelMap modelMap) {
		WB10User user = (WB10User) session.getAttribute(USER_SESSION_KEY);
		if (user == null) {
			return "redirect:login.htm";
		}
		modelMap.put("user", user); // 模型数据名，相当于"ModelAndView"中的"Model"部分。页面根据key的"user"取数据。
		return "index"; // 逻辑视图名，相当于"ModelAndView"中的"View"部分。
		// return "redirect:list.htm"; //
		// 测试重定向，重定向到"list.htm"这个RequestMapping中，在UserController中。
	}

	@RequestMapping("logout.htm")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login.htm";
	}

	// 首页
	@RequestMapping(value = "index.htm")
	public String index() {
		// 页面转发
		return "forward:login_success.htm";
	}

	@RequestMapping(value = "querySelectedUsers.htm")
	public void querySelectedUsers(String[] userNameArr) {

		System.out.println(userNameArr.toString());

	}

}
