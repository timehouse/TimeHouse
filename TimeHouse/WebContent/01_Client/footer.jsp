<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<link href="/TimeHouse/01_Client/css/index.css" rel="stylesheet" />
</head>
<body>
<!--FOOTER START-->
		</div>
	</div>
	<div class="footer">
		<div class="FooterNav">
			<div class="FooterLogo">
				<a href="/TimeHouse/01_Client/index.jsp">時光小屋TimeHouse</a>
			</div>
			<a href="/TimeHouse/01_Client/_13_News/News.jsp">最新消息</a>
			<a href="news.html">小屋介紹</a>
			<a href=".html">房型導覽</a> 
			<a href="facilities.html">服務設施</a>
			<a href="travel.jsp">週邊景點</a> 
			<a href="location.html">交通指南</a> 
			<a href=".html">線上訂房</a> 
			<a href="package.jsp">優惠套餐</a>
			<a href="/TimeHouse/01_Client/_23_Game/StartGame.jsp">好康遊戲</a>
			<c:if test="${not empty LoginOK }">
			<a href="contact.html">會員專區</a>
			</c:if>
			<a href=".html">留言版</a> <a href="/TimeHouse/01_Client/_11_MailHotel/MailHotel.jsp" class="line_end">聯絡我們</a>
			<!--分隔線結束-->
		</div>
		<div class="copyright">
			Copyright © 2016 時光小屋 <span class="copyright_en">TimeHouse
				&nbsp;&nbsp;&nbsp;</span><br class="rwd-break"> TEL/ 04 6631 6666 FAX/
				04 6631 5555 
		</div>
	</div>
	<!--FOOTER END-->
</body>
</html>