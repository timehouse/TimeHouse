<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>

	<form id="form" name="form" action='<c:url value="/123"/>'
		method="post" enctype="multipart/form-data">
		<input type="submit" name="action" value="uploadAll"> <br>
		<input type="text" name="roomPicid" value="1"> roomPicid <br>
		<input type="text" name="roomTypeid" value="1"> roomTypeid <br>
		<input type="file" name="uploadFile" size="40"> <input
			type="submit" name="action" value="getImg">
	</form>

	<img height='120' width='96' src='${pageContext.servletContext.contextPath}/123?action=getImg' />


</body>
</html>