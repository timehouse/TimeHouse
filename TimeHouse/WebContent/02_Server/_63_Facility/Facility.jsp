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

<h3>Facilities Table</h3>

<form action="<c:url value="/Facilities.controller" />" method="post">
<table>
	<tr>
		<td>設施編號 : </td>
		<td><input type="text" name="f_Id" value="${param.f_Id}"></td>
		<td>${error.f_Id}</td>
	</tr>
	<tr>
		<td>設施名稱 : </td>
		<td><input type="text" name="fName" value="${param.fName}"></td>
		<td>${error.fName}</td>
	</tr>

	<tr>
		<td>設施開放日期 : </td>
		<td><input type="text" name="date" value="${param.date}"></td>
		<td>${error.date}</td>
	</tr>
	<tr>
		<td>設施開放時間 : </td>
		<td><input type="text" name="time" value="${param.time}"></td>
		<td>${error.time}</td>
	</tr>
	<tr>
		<td>設施容納人數 : </td>
		<td><input type="text" name="count" value="${param.count}"></td>
		<td>${error.count}</td>
	</tr>
	<tr>
		<td>設施照片 : </td>
		<td><input type="file" name="fPic" value="${param.fPic}"></td>
		<td>${error.fPic}</td>
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
	<tr><td>f_Id</td><td>${insert.f_Id}</td></tr>
	<tr><td>fName</td><td>${insert.fName}</td></tr>
	<tr><td>date</td><td>${insert.date}</td></tr>
	<tr><td>time</td><td>${insert.time}</td></tr>
	<tr><td>count</td><td>${insert.count}</td></tr>
	<tr><td>fPic</td><td>${insert.fPic}</td></tr>
</table>
<script type="text/javascript">clearForm();</script>
</c:if>

<c:if test="${not empty update}">
<h3>Update Facility Table Success</h3>
<table border="1">
	<tr><td>f_Id</td><td>${update.f_Id}</td></tr>
	<tr><td>fName</td><td>${update.fName}</td></tr>
	<tr><td>date</td><td>${update.date}</td></tr>
	<tr><td>time</td><td>${update.time}</td></tr>
	<tr><td>count</td><td>${update.count}</td></tr>
	<tr><td>fPic</td><td>${update.fPic}</td></tr>
</table>
<script type="text/javascript">clearForm();</script>
</c:if>

</body>
</html>