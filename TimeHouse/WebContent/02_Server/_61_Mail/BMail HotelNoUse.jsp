<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 登入成功才可導向此頁面 -->
<%-- 	<c:if test="${empty LoginOK}"> --%>
<%-- 			<c:set var="target" value="${pageContext.request.servletPath }" scope="session" /> --%>
<%-- 		<c:redirect url="/02_Server/_51_AdminLogin/AdminLogin.jsp" /> --%>
<%-- 	</c:if> --%>
</head>
<body>
	<sql:setDataSource dataSource="jdbc/xxx" var="xxx" scope="application" />

	<sql:query var="rs" dataSource="${xxx}">
     select message_id 編號,type 問題分類,member_account 使用者身分, name 聯絡者姓名,email 聯絡信箱 , 
     message_content 訊息內容,message_date 訊息成立時間, reply 回覆內容, reply_date 回覆時間 from message
  </sql:query>

	<table border="1">
		<tr>
			<c:forEach var="columnName" items="${rs.columnNames}">
				<th>${columnName}</th>
			</c:forEach>
		</tr>

		<c:forEach var="row" items="${rs.rowsByIndex}" varStatus="status">
			<tr>
				<c:forEach var="columnValue" items="${row}">
					<%-- 				<c:choose> --%>
					<%-- 					<c:when test="${empty columnValue}&&${columnName eq '使用者身分'}"> --%>
					<!-- 						<td align="center">訪客</td> -->
					<%-- 					</c:when> --%>
					<%-- 					<c:otherwise> --%>
					<td align="center"><c:out value="${columnValue}" /></td>
					<%-- 					</c:otherwise> --%>
					<%-- 					</c:choose> --%>
				</c:forEach>
			<tr>
		</c:forEach>
	</table>
</body>
</html>