<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台管理首頁</title>
</head>
<body>
	<h1>前台管理首頁</h1>
	<h3><a href="<c:url value="/01_Client/_01_MemberRegister/register.jsp"/>">01註冊會員</a></h3>
	<c:if test="${empty LoginOK }">
		<h3><a href="<c:url value="/01_Client/_02_MemberLogin/login.jsp"/>" >02登入</a></h3>
	</c:if>
	<c:if test="${not empty LoginOK }">
		<h3><a href="<c:url value="/member/logout.controller"/>">02登出</a></h3>
	</c:if>
	<h3><a href="<c:url value="/01_Client/memberCenter.jsp"/>">會員中心(含03.04.09.11)</a></h3>
	<h3><a href="<c:url value="/01_Client/_03_MemberPassword/ChangePassword.jsp"/>">03更改密碼</a></h3>
	<h3><a href="<c:url value="/01_Client/_04_ReviseMInfo/ReviseInfo.jsp"/>">04修改會員資料</a></h3>
	<h3><a href="<c:url value="/01_Client/.jsp"/>">05房型列表(目前無)</a></h3>
	<h3><a href="<c:url value="/01_Client/.jsp"/>">06查詢空房(目前無)</a></h3>
	<h3><a href="<c:url value="/01_Client/.jsp"/>">07訂房(目前無)</a></h3>
	<h3><a href="<c:url value="/01_Client/_08_FillInfo/FillInfo.jsp"/>">08填寫住房資料</a></h3>
	<h3><a href="<c:url value="/client/MemberOrderServlet.controller"/>">09會員的歷史訂單</a></h3>
	<h3><a href="<c:url value="/01_Client/.jsp"/>">10設施列表(目前無，之前併到後端)</a></h3>
	<h3><a href="<c:url value="/client/MemberMailServlet.controller"/>">11會員的聯絡飯店訊息</a></h3>
	<h3><a href="<c:url value="/01_Client/_12_Msg/LiveMsg.jsp"/>">12即時客服(目前無法使用，gradle問題)</a></h3>
	<h3><a href="<c:url value="/01_Client/.jsp"/>">13最新消息(目前無，之前併到後端)</a></h3>
	<h3>14飯店介紹、15訂房須知</h3>
	<h3><a href="<c:url value="/01_Client/.jsp"/>">16.17位置+景點(檔案未給)</a></h3>
	<h3><a href="<c:url value="/01_Client/.jsp"/>">18留言版(目前無，需要確定網址才能用FB)</a></h3>
	<h3>19.20.21討論區不做</h3>
	<h3><a href="<c:url value="/01_Client/.jsp"/>">22餐廳(目前無，之前併到後端)</a></h3>
	<h3><a href="<c:url value="/01_Client/_23_Game/StartGame.jsp"/>">23遊戲</a></h3>
	
</body>
</html>