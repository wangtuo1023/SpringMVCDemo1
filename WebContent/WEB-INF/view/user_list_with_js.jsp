<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>用户列表</title>

<script type="text/javascript">
function querySelectedUsers(){
	// 提交form
	document.userForm.action="<%=request.getContextPath()%>/querySelectedUsers.htm";
	document.userForm.submit();
}
</script>

</head>

<body>
	<h2>用户列表</h2>

	<form action="<%=request.getContextPath()%>/login.htm" method="get">
		<input type="submit" value="返回登录页面" />
	</form>
	<form action="<%=request.getContextPath()%>/querySelectedUsers.htm" method="get">
		<!-- <input type="button" value="批量查询" onclick="querySelectedUsers()" /> -->
		<input type="button" value="批量查询"  />
	</form>

	<table>
		<tr>
			<td>选择</td>
			<td>用户名</td>
			<td>姓名</td>
			<td>操作</td>
		</tr>
		<c:forEach var="user" items="${users}" varStatus="status">
			<tr>
<!-- 				<td><input type="checkbox" name="userNameArr" value="${user.user_name} " onclick="querySelectedUsers()"></td> -->
				<td><input type="checkbox" name="userNameArr"
					value="${user.user_name} "
					onclick="<%=request.getContextPath() %>/querySelectedUsers.htm">
				</td>
				<td>${user.user_name }</td>
				<td>${user.real_name }</td>
				<td><a href="javascript:void(0)"
					onclick="javascript:self.location='<%=request.getContextPath() %>/usermgr/get/${user.user_name }.htm'">查看详情</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
