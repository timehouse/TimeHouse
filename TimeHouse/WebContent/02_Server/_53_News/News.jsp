<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="../css/main.css" />

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

<h3>NEWS</h3>

<form action="<c:url value="/news.controller" />" method="post">
<table>
	<tr>
		<td>公告編號 : </td>
		<td><input type="text" name="noticeId" value="${param.noticeId}"></td>
		<td>${error.noticeId}</td>
	</tr>
	<tr>
		<td>公告標題 : </td>
		<td><input type="text" name="title" value="${param.title}"></td>
		<td>${error.title}</td>
	</tr>
	<tr>
		<td>公告時間(起) : </td>
		<td><input type="text" name="startdate" value="${param.startdate}"></td>
		<td>${error.startdate}</td>
	</tr>
	<tr>
		<td>公告結束時間(迄) : </td>
		<td><input type="text" name="enddate" value="${param.enddate}"></td>
		<td>${error.enddate}</td>
	</tr>
	<tr>
		<td>公告內容 : </td>
		<td><input type="text" name="content" value="${param.content}"></td>
		<td>${error.content}</td>
	</tr>
	<tr>
		<td>
			<input type="submit" name="prodaction" value="Insert">
			<input type="submit" name="prodaction" value="Update">
		</td>
		<td>
			<input type="submit" name="prodaction" value="Select">
			<input type="button" value="Clear" onclick="clearForm()">
		</td>
	</tr>
</table>

</form>

<h3><span class="error">${error.action}</span></h3>

<c:if test="${not empty insert}">
<h3>Insert Product Table Success</h3>
<table border="1">
	<tr><td>noticeId</td><td>${insert.noticeId}</td></tr>
	<tr><td>title</td><td>${insert.title}</td></tr>
	<tr><td>startdate</td><td>${insert.startdate}</td></tr>
	<tr><td>enddate</td><td>${insert.enddate}</td></tr>
	<tr><td>content</td><td>${insert.content}</td></tr>
</table>
<script type="text/javascript">clearForm();</script>
</c:if>

<c:if test="${not empty update}">
<h3>Update Product Table Success</h3>
<table border="1">
	<tr><td>noticeId</td><td>${update.noticeId}</td></tr>
	<tr><td>title</td><td>${update.title}</td></tr>
	<tr><td>startdate</td><td>${update.startdate}</td></tr>
	<tr><td>enddate</td><td>${update.enddate}</td></tr>
	<tr><td>content</td><td>${update.content}</td></tr>
</table>
<script type="text/javascript">clearForm();</script>
</c:if>

</body>
</html>