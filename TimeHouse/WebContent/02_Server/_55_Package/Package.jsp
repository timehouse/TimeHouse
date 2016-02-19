<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="../css/main.css" />

<title>package</title>
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

<h3>Product Table</h3>

<form action="<c:url value="/package.controller" />" method="post">
<table>
	<tr>
		<td>優惠代號 : </td>
		<td><input type="text" name="package_id" value="${param.package_id}"></td>
		<td>${error.package_id}</td>
	</tr>
	<tr>
		<td>優惠名稱 : </td>
		<td><input type="text" name="name" value="${param.name}"></td>
		<td>${error.name}</td>
	</tr>

	<tr>
		<td>販售價錢 : </td>
		<td><input type="text" name="price" value="${param.price}"></td>
		<td>${error.price}</td>
	</tr>
	<tr>
		<td>優惠時間(起) : </td>
		<td><input type="text" name="start_date" value="${param.start_date}"></td>
		<td>${error.start_date}</td>
	</tr>
	<tr>
		<td>優惠時間(末) : </td>
		<td><input type="text" name="end_date" value="${param.end_date}"></td>
		<td>${error.end_date}</td>
	</tr>
	<tr>
		<td>優惠簡介 : </td>
		<td><input type="text" name="context" value="${param.context}"></td>
		<td>${error.context}</td>
	</tr>
	<tr>
		<td>
			<input type="submit" name="prodaction" value="Insert">
			<input type="submit" name="prodaction" value="Update">
		</td>
		
		<td>
			<input type="submit" name="prodaction" value="Delete">
			<input type="submit" name="prodaction" value="Select">
			<input type="submit" name="prodaction" value="SelectId">
			<input type="button" value="Clear" onclick="clearForm()">
		</td>
	</tr>
</table>

</form>

<h3><span class="error">${error.action}</span></h3>

<c:if test="${not empty delete and delete}">
<h3>Delete Product Table Success</h3>
<script type="text/javascript">clearForm();</script>
</c:if>

<c:if test="${not empty insert}">
<h3>Insert Product Table Success</h3>
<table border="1">
	<tr><td>id</td><td>${insert.package_id}</td></tr>
	<tr><td>name</td><td>${insert.name}</td></tr>
	<tr><td>price</td><td>${insert.price}</td></tr>
	<tr><td>start_date</td><td>${insert.start_date}</td></tr>
	<tr><td>end_date</td><td>${insert.end_date}</td></tr>
	<tr><td>context</td><td>${insert.context}</td></tr>
</table>
<script type="text/javascript">clearForm();</script>
</c:if>

<c:if test="${not empty update}">
<h3>Update Product Table Success</h3>
<table border="1">
	<tr><td>id</td><td>${update.package_id}</td></tr>
	<tr><td>name</td><td>${update.name}</td></tr>
	<tr><td>price</td><td>${update.price}</td></tr>
	<tr><td>start_date</td><td>${update.start_date}</td></tr>
	<tr><td>end_date</td><td>${update.end_date}</td></tr>
	<tr><td>context</td><td>${update.context}</td></tr>
</table>
<script type="text/javascript">clearForm();</script>
</c:if>

</body>
</html>