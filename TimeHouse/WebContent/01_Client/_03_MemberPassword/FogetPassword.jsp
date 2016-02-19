<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value="/forgetPassword.controller"/>" method="post">
		<div>帳號:<input type="text" value="${param.account }" name="account"></div>
		<div>${error.account}</div>
		<div>電子信箱:<input type="text" value="${param.email }" placeholder="example@gmail.com" name="email"></div>
		<div>${error.email }</div>
		<input type="submit" value="確認">
		<input type="reset" value="重置">
	</form>
</body>
</html>