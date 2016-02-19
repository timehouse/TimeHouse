<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Product</title>
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

<form action="<c:url value="/CorrectFillInfo.controller" />" method="post">
<table>
	<tr>
		<td>房客編號 : </td>
		<td><input type="text" name="guest_id" value="${param.guest_id}"></td>
		<td>${error.guest_id}</td>
	</tr>
	<tr>
		<td>房客姓名(姓) : </td>
		<td><input type="text" name="guest_last_name" value="${param.guest_last_name}"></td>
		<td>${error.guest_last_name}</td>
	</tr>
	<tr>
		<td>房客姓名(名) : </td>
		<td><input type="text" name="guest_first_name" value="${param.guest_first_name}"></td>
		<td>${error.guest_first_name}</td>
	</tr>
	
	<tr>
		<td>性別 : </td>
		<td><input type="checkbox" name="gender" value="male"  >male</td>
		<td><input type="checkbox" name="gender" value="female" >female</td>
		<td>${error.gender}</td>
	</tr>
	
	<tr>
		<td>房客身分證字號 : </td>
		<td><input type="text" name="id_number" value="${param.id_number}"></td>
		<td>${error.id_number}</td>
	</tr>
	
	<tr>
		<td>市話 : </td>
		<td><input type="text" name="tel" value="${param.tel}"></td>
		<td>${error.tel}</td>
	</tr>
	
	<tr>
		<td>手機 : </td>
		<td><input type="text" name="phone_number" value="${param.phone_number}"></td>
		<td>${error.phone_number}</td>
	</tr>
	
	<tr>
		<td>電子信箱 : </td>
		<td><input type="text" name="email" value="${param.email}"></td>
		<td>${error.email}</td>
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
<h3>Insert Table Success</h3>
<table border="1">
	<tr><td>guest_id</td><td>${insert.guest_id}</td></tr>
	<tr><td>guest_last_name</td><td>${insert.guest_last_name}</td></tr>
	<tr><td>guest_first_name</td><td>${insert.guest_first_name}</td></tr>
	<tr><td>gender</td><td>${insert.gender}</td></tr>
	<tr><td>id_number</td><td>${insert.id_number}</td></tr>
	<tr><td>tel</td><td>${insert.tel}</td></tr>
	<tr><td>phone_number</td><td>${insert.phone_number}</td></tr>
	<tr><td>email</td><td>${insert.email}</td></tr>
</table>
<script type="text/javascript">clearForm();</script>
</c:if>

<c:if test="${not empty update}">
<h3>Update  Table Success</h3>
<table border="1">

<tr><td>guest_id</td><td>${update.guest_id}</td></tr>
	<tr><td>guest_last_name</td><td>${update.guest_last_name}</td></tr>
	<tr><td>guest_first_name</td><td>${update.guest_first_name}</td></tr>
	<tr><td>gender</td><td>${update.gender}</td></tr>
	<tr><td>id_number</td><td>${update.id_number}</td></tr>
	<tr><td>tel</td><td>${update.tel}</td></tr>
	<tr><td>phone_number</td><td>${update.phone_number}</td></tr>
	<tr><td>email</td><td>${update.email}</td></tr>

</table>
<script type="text/javascript">clearForm();</script>
</c:if>

</body>
</html>