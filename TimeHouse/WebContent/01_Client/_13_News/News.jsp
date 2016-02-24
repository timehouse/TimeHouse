<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="../css/main.css" />

<title>Product</title>

</head>

<body>
<%@ include file="../header.jsp"%> 

<br>

<h1>NEWS</h1>

<form action="<c:url value="/newsc.controller" />" method="post">

	<input type="submit" name="prodaction" value="Select">
<!-- 點title後進入內容 -->
    <h1>${param.title}</h1>
    <h1>${param.startdate}</h1>
    <h1>${param.enddate}</h1>
    <h1>${param.content}</h1>
</form>

<br>

<%@ include file="../footer.jsp"%> 
</body>
</html>