<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聯絡訊息</title>
</head>
<body>
		<c:if test="${ not empty select }" />
		<form>
			<table border="1">
				<thead>
					<tr>
						<th>分類</th>
						<th>訊息內容</th>
						<th>訊息成立日期</th>
						<th>管理員回應</th>
						<th>回應日期</th>
					</tr>
				</thead>

				<tbody>

					<c:forEach var="num" begin="0" end="${fn:length(select)-1}">
<%-- 					<c:url value="/02_Server/_61_Mail/ReplyMail.jsp" var="path"> --%>
<%-- 						<c:param name="id" value="${select[num].message_id }" /> --%>
<%-- 						<c:param name="type" value="${select[num].type }" /> --%>
<%-- 						<c:param name="member_account" --%>
<%-- 							value="${select[num].member_account.member_account }" /> --%>
<%-- 						<c:param name="name" value="${select[num].name }" /> --%>
<%-- 						<c:param name="email" value="${select[num].email }" /> --%>
<%-- 						<c:param name="content" value="${select[num].message_content }" /> --%>
<%-- 						<c:param name="message_date" value="${select[num].message_date }" /> --%>
<%-- 					</c:url> --%>
					<tr>
							<td>${select[num].type}</td>
							<td>${select[num].message_content }</td>
							<td>${select[num].message_date }</td>
							<c:if test="${not empty select[num].reply }">
								<td>${select[num].reply }</td>
								<td>${select[num].reply_date }</td>
							</c:if>
							<c:if test="${ empty select[num].reply }">
								<td>尚未回應</td>
								<td>尚未回應</td>
							</c:if>
						</tr>
					</c:forEach>

				</tbody>

			</table>
		</form>

</body>
</html>