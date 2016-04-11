<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">



<html>

<head>
<title>用户信息详情</title>
</head>


<body>
	<h2>用户信息详情：</h2>
	<form action="<%=request.getContextPath()%>/login.htm" method="get">
		<input type="submit" value="返回登录页面" />
	</form>

	<form action="<%=request.getContextPath()%>/usermgr/list.htm"
		method="get">
		<input type="submit" value="返回列表页面" />
	</form>

	注册用户名：	<td>${user.user_name }</td>	<br /> 
	真实姓名 ：	<td>${user.real_name }</td>	<br />
	证件号码 ：	<td>${user.id_no }</td>	<br />
	邮箱地址 ：	<td>${user.email }</td><br /> 
	手机号码 ：	<td>${user.mobile_no }</td>	<br /> 
	注册时间 ：	<td>${user.regist_time }</td>
	<br />

</body>
</html>
