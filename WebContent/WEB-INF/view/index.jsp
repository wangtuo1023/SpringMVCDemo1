<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>登录成功</title>
</head>

<body>
	登录成功！欢迎：${user.user_name}(${user.mobile_no})，
	<br>你可以：
	<br>
	<a href="javascript:void(0)"
		onclick="avascript:self.location='<%=request.getContextPath()%>/usermgr/list.htm'">查看全部用户列表</a>
	<br>
	<a href="javascript:void(0)"
		onclick="avascript:self.location='<%=request.getContextPath()%>/logout.htm'">返回登录页面</a>
</body>
</html>
