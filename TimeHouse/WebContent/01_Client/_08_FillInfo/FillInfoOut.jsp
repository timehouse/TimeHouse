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
<h3>Select Product Table Result : ${fn:length(select)} row(s) selected-all</h3>
<table>
	<tbody>
	<c:forEach var="bean" items="${select}">
		<c:url value="/01_Client/_08_FillInfo/FillInfo.jsp" var="path">
			<c:param name="guest_id" value="${bean.guest_id}" />
			<c:param name="guest_last_name" value="${bean.guest_last_name}" />
			<c:param name="guest_first_name" value="${bean.guest_first_name}" />
			<c:param name="gender" value="${bean.gender}" />
			<c:param name="id_number" value="${bean.id_number}" />
			<c:param name="tel" value="${bean.tel}" />
			<c:param name="phone_number" value="${bean.phone_number}" />
			<c:param name="email" value="${bean.email}" />
		
			
		</c:url>
	<tr>
		<td><a href="${path}">${bean.guest_id}</a></td>
		<td>${bean.guest_last_name}</td>
		<td>${bean.guest_first_name}</td>
		<td>${bean.gender}</td>
		<td>${bean.id_number}</td>
		<td>${bean.tel}</td>
		<td>${bean.phone_number}</td>	
		<td>${bean.email}</td>
	</tr>
	</c:forEach>	
	</tbody>
</table>
</c:if>

<!-- 我是查一筆 -->
<c:if test="${not empty selectid}">
<h3>Select Product Table Result : 1 row(s) selected-id</h3>
<table>
	    
		<c:url value="/01_Client/_08_FillInfo/FillInfo.jsp" var="path">
			<c:param name="guest_id" value="${selectid.guest_id}" />
			<c:param name="guest_last_name" value="${selectid.guest_last_name}" />
			<c:param name="guest_first_name" value="${selectid.guest_first_name}" />
			<c:param name="gender" value="${selectid.gender}" />
			<c:param name="id_number" value="${selectid.id_number}" />
			<c:param name="tel" value="${selectid.tel}" />
			<c:param name="phone_number" value="${selectid.phone_number}" />
			<c:param name="email" value="${selectid.email}" />
		</c:url>
	<tr>
		<td><a href="${path}">${selectid.guest_id}</a></td>
		<td>${selectid.guest_last_name}</td>
		<td>${selectid.guest_first_name}</td>
		<td>${selectid.gender}</td>
		<td>${selectid.id_number}</td>
		<td>${selectid.tel}</td>
		<td>${selectid.phone_number}</td>	
		<td>${selectid.email}</td>
	</tr>
	
	</tbody>
</table>
</c:if>

<!-- 我是跳回初始頁面Package.jsp -->
<h3><a href="<c:url value="/01_Client/_08_FillInfo/FillInfo.jsp" />">Product Table</a></h3>
</body>
</html>