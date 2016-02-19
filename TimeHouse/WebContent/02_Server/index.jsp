<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>後台管理首頁</title>
</head>
<body>
	<h1>後台管理首頁</h1>
	<h3><a href="<c:url value="/02_Server/_51_AdminLogin/AdminLogin.jsp"/>">51管理員登入</a></h3>
	<h3>52重設管理員密碼不做</h3>
	<h3><a href="<c:url value="/02_Server/_53_News/News.jsp"/>">53公告</a></h3>
	<h3><a href="<c:url value="/02_Server/.jsp"/>">54報表(目前無)</a></h3>
	<h3><a href="<c:url value="/02_Server/_55_Package/Package.jsp"/>">55優惠</a></h3>
	<h3><a href="<c:url value="/02_Server/_56_RoomType/RoomType.jsp"/>">56房型管理</a></h3>
	<h3><a href="<c:url value="/02_Server/_57_Room/Room.jsp"/>">57房間管理</a></h3>
	<h3><a href="<c:url value="/02_Server/_58_RoomSche/RoomSche.jsp"/>">58房況管理 </a></h3>
	<h3><a href="<c:url value="/02_Server/.jsp"/>">59後台訂房(目前無)</a></h3>
	<h3><a href="<c:url value="/server/BAllOrderServlet.controller"/>">60管理訂單</a></h3>
	<h3><a href="<c:url value="/server/BAllMailServlet.controller"/>">61聯絡訊息</a></h3>
	<h3><a href="<c:url value="/02_Server/_62_Msg/.jsp"/>">62即時客服(等解決gradle再弄)</a></h3>
	<h3><a href="<c:url value="/02_Server/_63_Facility/Facility.jsp"/>">63設施管理 </a></h3>
	<h3>64.65.66不做</h3>
	<h3>67留言板管理不確定是否做</h3>
	<h3>68討論區不做</h3>
	<h3><a href="<c:url value="/02_Server/_68_Restaurant/RestaurantOut.jsp"/>">68餐廳管理</a></h3>
	<h3>70.71不做</h3>
	<h3><a href="<c:url value="/02_Server/_72_CorrectFillInfo/CorrectFillInfo.jsp"/>">72修改住房者資料</a></h3>

</body>
</html>