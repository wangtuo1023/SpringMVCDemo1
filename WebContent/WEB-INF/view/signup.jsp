<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body>
	<h3>用户注册</h3>
	<form action="<%=request.getContextPath() %>/saveSignup.htm"
		method="post">
		用户名<input type="text" name="userName"> 
		密码<input type="password" name="pwd" /> 
		<input type="submit" />
	</form>
</body>
</html>
