<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="/TimeHouse/01_Client/css/index.css" rel="stylesheet" />
<link rel="SHORTCUT ICON" href="favicon.ico">
</head>
<body>
<!--HEADER START-->
	<div class="wrap">
		<div class="main">
			<!--左上角logo-->
			<div class="header">
				<div class="logo_page">
					<a href="/TimeHouse/01_Client/index.jsp"><img src="/TimeHouse/01_Client/images/logo_page.png" />
						<h1>時光小屋</h1></a>
				</div>
				<div class="fat-nav">
					<div class="fat-nav__wrapper">
						<ul>
							<li><a href="news.html">最新消息</a></li>
							<li><a href="rooms.html">小屋介紹</a></li>
							<li><a href=".html">房型導覽</a></li>
							<li><a href="facilities.html">服務設施</a></li>
							<li><a href="travel.jsp">週邊景點</a></li>
							<li><a href="location.html">交通指南</a></li>
							<li><a href="location.html">線上訂房</a></li>
							<li><a href="package.jsp">優惠套餐</a></li>
							<li><a href="/TimeHouse/01_Client/_23_Game/StartGame.jsp">好康遊戲</a></li>
							<c:if test="${not empty LoginOK }">
							<li><a href="/TimeHouse/01_Client/memberCenter.jsp">會員專區</a></li>
							</c:if>
							<li><a href="location.html">留言版</a></li>
							<li><a href="/TimeHouse/01_Client/_11_MailHotel/MailHotel.jsp">聯絡我們</a></li>
						</ul>
					</div>
				</div>
				<div class="top_links">
					<div class="top_menu">
						<ul>
							<c:if test="${empty LoginOK }">
								<li><a class="top_notice" href="<c:url value="/01_Client/_02_MemberLogin/login.jsp"/>">登入</a></li>
							</c:if>
							<c:if test="${not empty LoginOK }">
								<li><a class="top_contact" href="<c:url value="/member/logout.controller"/>">登出</a></li>								
							</c:if>
							<li><a class="top_home" href="/TimeHouse/01_Client/index.jsp">回 首 頁</a></li>
						</ul>
					</div>
				</div>
			</div>

	<!--HEADER END-->
</body>
</html>