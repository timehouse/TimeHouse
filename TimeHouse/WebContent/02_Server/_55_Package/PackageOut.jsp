<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>sakin.package</title>
</head>
<body>

<!-- 我是查多筆 -->
<c:if test="${not empty select}">
<h3>Select Product Table Result : ${fn:length(select)} row(s) selected-all</h3>
<table>
	<tbody>
	<c:forEach var="bean" items="${select}">
		<c:url value="/02_Server/_55_Package/Package.jsp" var="path">
			<c:param name="package_id" value="${bean.package_id}" />
			<c:param name="name" value="${bean.name}" />
			<c:param name="price" value="${bean.price}" />
			<c:param name="start_date" value="${bean.start_date}" />
			<c:param name="end_date" value="${bean.end_date}" />
			<c:param name="context" value="${bean.context}" />
		</c:url>
	<tr>
		<td><a href="${path}">${bean.package_id}</a></td>
		<td>${bean.name}</td>
		<td>${bean.price}</td>
		<td><fmt:formatDate value="${bean.start_date}" pattern="yyyy-MM-dd EEEE"/></td>
		<td><fmt:formatDate value="${bean.end_date}" pattern="yyyy-MM-dd EEEE"/></td>
		<td>${bean.context}</td>
	</tr>
	</c:forEach>	
	</tbody>
</table>
</c:if>

<!-- 我是查一筆 -->
<c:if test="${not empty selectid}">
<h3>Select Product Table Result : 1 row(s) selected-id</h3>
<table>
	    
		<c:url value="/02_Server/_55_Package/Package.jsp" var="path">
			<c:param name="package_id" value="${selectid.package_id}" />
			<c:param name="name" value="${selectid.name}" />
			<c:param name="price" value="${selectid.price}" />
			<c:param name="start_date" value="${selectid.start_date}" />
			<c:param name="end_date" value="${selectid.end_date}" />
			<c:param name="context" value="${selectid.context}" />
		</c:url>
	<tr>
		<td><a href="${path}">${selectid.package_id}</a></td>
		<td>${selectid.name}</td>
		<td>${selectid.price}</td>
		<td><fmt:formatDate value="${selectid.start_date}" pattern="yyyy-MM-dd EEEE"/></td>
		<td><fmt:formatDate value="${selectid.end_date}" pattern="yyyy-MM-dd EEEE"/></td>
		<td>${selectid.context}</td>
	</tr>
	
	</tbody>
</table>
</c:if>

<!-- 我是跳回初始頁面Package.jsp -->
<h3><a href="<c:url value="/02_Server/_55_Package/Package.jsp" />">Product Table</a></h3>
</body>
</html>