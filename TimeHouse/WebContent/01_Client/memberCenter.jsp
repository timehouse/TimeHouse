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
	<h1>會員中心</h1>
	<a href="<c:url value="/01_Client/_03_MemberPassword/ChangePassword.jsp"/>">更改密碼</a>
	<br/>
	<a href="<c:url value="/01_Client/_04_ReviseMInfo/ReviseInfo.jsp"/>">修改會員資料</a>
	<br/>
	<a href="<c:url value="/client/MemberMailServlet.controller"/>">會員的聯絡訊息</a>
	<br/>
	<a href="<c:url value="/client/MemberOrderServlet.controller"/>">會員的歷史訂單</a>
	<br/>
	<h2><a href="<c:url value="/01_Client/index.jsp"/>">回首頁</a></h2>
	
</body>
</html>