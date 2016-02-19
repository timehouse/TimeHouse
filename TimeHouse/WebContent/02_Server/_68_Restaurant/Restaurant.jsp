<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="../css/main.css" />

<title>Facilities</title>
<script type="text/javascript">
function clearForm() {
	var inputs = document.getElementsByTagName("input");
	for(var i=0; i<inputs.length; i++) {
		if(inputs[i].type=="text") {
			inputs[i].value="";
		}
	}
}
</script>
</head>
<body>

<h3>Welcome</h3>

<h3>Restaurant Table</h3>

<form action="<c:url value="/Restaurant.controller" />" method="post">
<table>
	<tr>
		<td>設施編號 : </td>
		<td><input type="text" name="rest_id" value="${param.rest_id}"></td>
		<td>${error.rest_id}</td>
	</tr>
	<tr>
		<td>設施名稱 : </td>
		<td><input type="text" name="rest_name" value="${param.rest_name}"></td>
		<td>${error.rest_name}</td>
	</tr>

	<tr>
		<td>設施開放日期 : </td>
		<td><input type="text" name="rest_time" value="${param.rest_time}"></td>
		<td>${error.rest_time}</td>
	</tr>
	<tr>
		<td>設施開放時間 : </td>
		<td><input type="text" name="rest_address" value="${param.rest_address}"></td>
		<td>${error.rest_address}</td>
	</tr>
	<tr>
		<td>設施容納人數 : </td>
		<td><input type="text" name="rest_context" value="${param.rest_context}"></td>
		<td>${error.rest_context}</td>
	</tr>
	<tr>
		<td>設施照片 : </td>
		<td><input type="file" name="rest_pic" value="${param.rest_pic}"></td>
		<td>${error.rest_pic}</td>
	</tr>
	<tr>
		<td>
			<input type="submit" name="prodaction" value="Insert">
			<input type="submit" name="prodaction" value="Update">
		</td>
		<td>
			<!-- <input type="submit" name="prodaction" value="Delete"> -->
			<input type="submit" name="prodaction" value="Select">
			<!-- <input type="submit" name="prodaction" value="SelectId"> -->
			<input type="button" value="Clear" onclick="clearForm()">
		</td>
	</tr>
</table>

</form>

<h3><span class="error">${error.action}</span></h3>

<c:if test="${not empty delete and delete}">
<h3>Delete Facility Table Success</h3>
<script type="text/javascript">clearForm();</script>
</c:if>

<c:if test="${not empty insert}">
<h3>Insert Facility Table Success</h3>
<table border="1">
	<tr><td>rest_id</td><td>${insert.rest_id}</td></tr>
	<tr><td>rest_name</td><td>${insert.rest_name}</td></tr>
	<tr><td>rest_time</td><td>${insert.rest_time}</td></tr>
	<tr><td>rest_address</td><td>${insert.rest_address}</td></tr>
	<tr><td>rest_context</td><td>${insert.rest_context}</td></tr>
	<tr><td>rest_pic</td><td>${insert.rest_pic}</td></tr>
</table>
<script type="text/javascript">clearForm();</script>
</c:if>

<c:if test="${not empty update}">
<h3>Update Facility Table Success</h3>
<table border="1">
	<tr><td>rest_id</td><td>${update.rest_id}</td></tr>
	<tr><td>rest_name</td><td>${update.rest_name}</td></tr>
	<tr><td>rest_time</td><td>${update.rest_time}</td></tr>
	<tr><td>rest_address</td><td>${update.rest_address}</td></tr>
	<tr><td>rest_context</td><td>${update.rest_context}</td></tr>
	<tr><td>rest_pic</td><td>${update.rest_pic}</td></tr>
</table>
<script type="text/javascript">clearForm();</script>
</c:if>

</body>
</html>