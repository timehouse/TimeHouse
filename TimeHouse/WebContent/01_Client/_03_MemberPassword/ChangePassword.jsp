<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%-- <c:if test="${empty LoginOK}"> --%>
<%-- 	<c:redirect url="/01_Client/_02_MemberLogin/login.jsp" /> --%>
<%-- </c:if> --%>
</head>
<body>
	<form action="<c:url value="/changePassword.controller"/>" method="post">
		<div>
			<h3>舊密碼:</h3>
			<input type="text" name="oldPassword" value="${param.oldPassword}"> 
			<span>${error.oldPassword}</span>
			<span>${error.notFound }</span>
		</div>
		<div>
			<h3>新密碼:</h3>
			<input type="text" name="newPassword" value="${param.newPassword}"> 
			<span>${error.newPassword }</span>
		</div>
		<div>
			<h3>確認密碼:</h3>
			<input type="text" name="confirmPassword" value="${param.confirmPassword}"> 
			<span>${error.confirmPassword }</span>
			<span>${error.wrong}</span>
		</div>
		<input type="submit" value="送出">
		<input type="reset" value="清除">
	</form>
</body>
</html>