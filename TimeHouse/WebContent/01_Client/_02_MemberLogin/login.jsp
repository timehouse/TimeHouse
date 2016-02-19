<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<style >
	.f1{
		margin:0 auto;
	}
</style>
</head>
<body>
<form action="<c:url value="/member/login.controller"/>" style="margin: 0 auto;width:300px">
<h1>會員登入</h1>
	<fieldset class="f1">
		<div>
			<label>帳號</label>
			<input type="text" placeholder="Account" name="account" value="${param.account}"/>
			<div>${error.account }</div>
		</div>
		<div>
			<label>密碼</label>
			<input type="password" placeholder="Password" name="password" value="${param.password}"/>
			<div>${error.password }</div>
		</div>
		<div style="text-align: left">
			<input type="submit" value="Login">
			<div>${error.notExist }</div>
		</div>
	</fieldset>
	<a href="<c:url value="/01_Client/_01_MemberRegister/register.jsp"/>">不是會員嗎?</a>	
	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	<a href="<c:url value="/01_Client/_03_MemberPassword/FogetPassword.jsp"/>">忘記密碼</a>	
</form>
</body>
</html>