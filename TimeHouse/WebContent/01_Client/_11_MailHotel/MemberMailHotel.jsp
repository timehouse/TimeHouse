<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/justified-nav.css" />
<link rel="stylesheet" href="../css/jPages.css" />
<title>聯絡訊息</title>
<style>
	td{
		font-size:16px;
		padding:5px 10px;
	}
	th{
		padding:5px 10px;
		text-align:center;
		background-color:rgba(0,204,102, 0.6);
		font-size:18px;
	}
	.hovertr:hover {
		background-color:#BBFFEE;
		cursor: crosshair;
	}
	form {
		text-align:center;
	}
	table{
		margin:30px auto;
	}
		.itemContainer{
		font-size:16px;
	}
	
</style>
</head>
<body>
	<%@ include file="../header.jsp"%>
		<div style="height:110px"></div>
		<div style="text-align:center">
			<h1>歷史聯絡訊息</h1>
		</div>
		<c:if test="${ not empty select }" />
		<form>
			<table border="1">
				<thead>
					<tr>
						<th>&nbsp;分類&nbsp;</th>
						<th>&nbsp;訊息內容&nbsp;</th>
						<th>&nbsp;訊息成立時間&nbsp;</th>
						<th>&nbsp;管理員回應&nbsp;</th>
						<th>&nbsp;回應日期&nbsp;</th>
					</tr>
				</thead>

				<tbody id="itemContainer">

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
					<tr class="hovertr">
							<td>&nbsp;${select[num].type}&nbsp;</td>
							<td>&nbsp;${select[num].message_content }&nbsp;</td>
							<td>&nbsp;<fmt:formatDate value="${select[num].message_date }" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;</td>
							<c:if test="${not empty select[num].reply }">
								<td>${select[num].reply }</td>
								<td>&nbsp;<fmt:formatDate value="${select[num].reply_date }" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;</td>
<%-- 								<td>${select[num].reply_date }</td> --%>
							</c:if>
							<c:if test="${ empty select[num].reply }">
								<td>尚未回應</td>
								<td>尚未回應</td>
							</c:if>
						</tr>
					</c:forEach>

				</tbody>

			</table>
			<div class="holder"></div>
		</form>
		<script type="text/javascript" src="../js/jquery-2.2.0.min.js"></script>
		<script type="text/javascript" src="../js/jPages.min.js"></script>
		<script type="text/javascript">
		$("div.holder").jPages({
			containerID: "itemContainer"
			});
		</script>
	<%@ include file="../footer.jsp"%>

</body>
</html>