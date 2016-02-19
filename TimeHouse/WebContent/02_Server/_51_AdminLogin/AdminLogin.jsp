<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<h1>Admin Login</h1>
<form action="<c:url value="/admin/login.controller"/>" method="get">
	<table>
		<tr>
			<td>ID : </td>
			<td><input type="text" name="id" value="${param.id}" placeholder="Insert ID "/></td>
			<td>${error.id}</td>
		</tr>
		<tr>
			<td>Password: </td>
			<td><input type="text" name="password" value="${param.password}" placeholder="Insert Password"/></td>
			<td>${error.password}</td>
		</tr>
		<tr>
			<td>　</td>
			<td align="right"><input type="submit" value="Login"></td>
			<td>${error.notExist}</td>
		</tr>	
	</table>
</form>

</body>
</html>