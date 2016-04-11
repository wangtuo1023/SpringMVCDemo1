<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>登 录</title>
</head>

<body>
	<h3>用户登录</h3>
	${ fail_key }
	<br />
	<form action="<%=request.getContextPath() %>/saveLogin.htm"
		method="post">
		<!-- 怎样将user_name属性也赋值给其他对象？ -->
		用户名 :<input type="text" name="wb10UserBasicInfo.user_name"><br />
		证件号码:<input type="text" name="wb10UserBasicInfo.id_no"><br />
		手机号码:<input type="text" name="wb10UserContactInfo.mobile_no"><br />
		<input type="submit" value="登入" />
	</form>

	<br />

	<a href="javascript:void(0);"
		onclick="javascript:self.location='<%=request.getContextPath() %>/usermgr/signup.html'">注册</a>
</body>
</html>
