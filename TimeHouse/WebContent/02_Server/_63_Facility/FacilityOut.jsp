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
		<c:url value="/02_Server/_63_Facility/Facility.jsp" var="path">
			<c:param name="f_Id" value="${bean.f_Id}" />
			<c:param name="fName" value="${bean.fName}" />
			<c:param name="date" value="${bean.date}" />
			<c:param name="time" value="${bean.time}" />
			<c:param name="count" value="${bean.count}" />
			<c:param name="fPic" value="${bean.fPic}" />
		</c:url>
	<tr>
		<td><a href="${path}">${bean.f_Id}</a></td>
		<td>${bean.fName}</td>
		<td><fmt:formatDate value="${bean.date}" pattern="yyyy-MM-dd EEEE"/></td>
		<td><fmt:formatDate value="${bean.time}" pattern="yyyy-MM-dd EEEE"/></td>
		<td>${bean.count}</td>
		<td>${bean.fPic}</td>
	</tr>
	</c:forEach>	
	</tbody>
</table>
</c:if>

<!-- 我是查一筆 --><!-- 
<c:if test="${not empty selectid}">
<h3>Select Facilities Table Result : 1 row(s) selected-id</h3>
<table>
	    
		<c:url value="/02_Server/_63_Facility/Facility.jsp" var="path">
			<c:param name="f_Id" value="${selectid.f_Id}" />
			<c:param name="fName" value="${selectid.fName}" />
			<c:param name="date" value="${selectid.date}" />
			<c:param name="time" value="${selectid.time}" />
			<c:param name="count" value="${selectid.count}" />
			<c:param name="fPic" value="${selectid.fPic}" />
		</c:url>
	<tr>
		<td><a href="${path}">${selectid.f_Id}</a></td>
		<td>${selectid.fName}</td>
		<td><fmt:formatDate value="${selectid.date}" pattern="yyyy-MM-dd EEEE"/></td>
		<td><fmt:formatDate value="${selectid.time}" pattern="yyyy-MM-dd EEEE"/></td>
		<td>${selectid.count}</td>
		<td>${selectid.fPic}</td>
	</tr>
	
	</tbody>
</table>
</c:if>
 -->
<!-- 我是跳回初始頁面Package.jsp -->
<h3><a href="<c:url value="/02_Server/_63_Facility/Facility.jsp" />">Facilities Table</a></h3>
</body>
</html>