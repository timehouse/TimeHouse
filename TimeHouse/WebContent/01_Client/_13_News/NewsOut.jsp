<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
    <title>Display</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        
    </style>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>


<!-- 查多筆 -->
<c:if test="${not empty select}">
<h3>Select Product Table Result : ${fn:length(select)} row(s) selected-all</h3>
<table class="table table-bordred table-striped">
	<tbody>
	<c:forEach var="bean" items="${select}">
		<c:url value="/01_Client/_13_News/News.jsp" var="path">
	<c:param name="noticeId" value="${bean.noticeId}" />
			<c:param name="title" value="${bean.title}" />
			<c:param name="startdate" value="${bean.startdate}" />
			<c:param name="enddate" value="${bean.enddate}" />
			<c:param name="content" value="${bean.content}" />
		</c:url>
	<tr>
<%-- 		<td><a href="${path}">${bean.noticeId}</a></td> --%>
		<td><a href="${path}">${bean.title}</a></td>
<%-- 		<td><fmt:formatDate value="${bean.startdate}" pattern="yyyy-MM-dd EEEE"/></td> --%>
<%-- 		<td><fmt:formatDate value="${bean.enddate}" pattern="yyyy-MM-dd EEEE"/></td> --%>
<%-- 		<td>${bean.content}</td> --%>
	</tr>
	</c:forEach>	
	</tbody>
</table>
</c:if>

<!-- 查一筆 -->
<%-- <c:if test="${not empty selectid}"> --%>
<!-- <h3>Select Product Table Result : 1 row(s) selected-id</h3> -->
<!-- <table> -->
	    
<%-- 		<c:url value="/01_Client/_13_News/News.jsp" var="path"> --%>
<%-- 			<c:param name="noticeId" value="${bean.noticeId}" /> --%>
<%-- 			<c:param name="title" value="${bean.title}" /> --%>
<%-- 			<c:param name="startdate" value="${bean.startdate}" /> --%>
<%-- 			<c:param name="enddate" value="${bean.enddate}" /> --%>
<%-- 			<c:param name="content" value="${bean.content}" /> --%>
			
<%-- 		</c:url> --%>
<!-- 	<tr> -->
<%-- 		<td><a href="${path}">${bean.noticeId}</a></td> --%>
<%-- 		<td>${bean.title}</td> --%>
<%-- 		<td><fmt:formatDate value="${bean.startdate}" pattern="yyyy-MM-dd EEEE"/></td> --%>
<%-- 		<td><fmt:formatDate value="${bean.enddate}" pattern="yyyy-MM-dd EEEE"/></td> --%>
<%-- 		<td>${bean.content}</td> --%>
<!-- 	</tr> -->
	
<!-- 	</tbody> -->
<!-- </table> -->
<%-- </c:if> --%>

<!-- 跳回初始頁面Package.jsp -->
<h3><a href="<c:url value="/01_Client/_13_News/News.jsp" />">NEWS</a></h3>
</body>
</html>