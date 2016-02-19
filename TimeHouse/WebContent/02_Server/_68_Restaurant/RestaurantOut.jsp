<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/table.css" />
<title>Display</title>
</head>
<body>

<!-- 我是查多筆 -->
<c:if test="${not empty select}">
<h3>Select Facilities Table Result : ${fn:length(select)} row(s) selected-all</h3>
<table>
	<tbody>
	<c:forEach var="bean" items="${select}">
		<c:url value="/02_Server/_68_Restaurant/Restaurant.jsp" var="path">
			<c:param name="rest_id" value="${bean.rest_id}" />
			<c:param name="rest_name" value="${bean.rest_name}" />
			<c:param name="rest_time" value="${bean.rest_time}" />
			<c:param name="rest_address" value="${bean.rest_address}" />
			<c:param name="rest_context" value="${bean.rest_context}" />
			<c:param name="rest_pic" value="${bean.rest_pic}" />
		</c:url>
	<tr>
		<td><a href="${path}">${bean.rest_id}</a></td>
		<td>${bean.rest_name}</td>
		<td>${bean.rest_time}</td>
		<td>${bean.rest_address}</td>
		<td>${bean.rest_context}</td>
		<td>${bean.rest_pic}</td>
	</tr>
	</c:forEach>	
	</tbody>
</table>
</c:if>
<!-- 我是跳回初始頁面Package.jsp -->
<h3><a href="<c:url value="/02_Server/_68_Restaurant/Restaurant.jsp" />">Facilities Table</a></h3>
</body>
</html>