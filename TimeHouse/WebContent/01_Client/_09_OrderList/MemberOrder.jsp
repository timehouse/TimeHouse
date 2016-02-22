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
<title>管理訂單</title>
<style>
	td,th{
		text-align:center;
	}
</style>
</head>
<body>
<%-- <%@ include file="../header.jsp"%> --%>
<c:if test="${ not empty select }" />
		<form>
			<table border="1">
				<thead>
					<tr>
						<th>&nbsp;訂單編號&nbsp;</th>
						<th>&nbsp;住房者姓名&nbsp;</th>
						<th>&nbsp;房型&nbsp;</th>
						<th>&nbsp;入住日期&nbsp;</th>
						<th>&nbsp;退房日期&nbsp;</th>
						<th>&nbsp;成人人數&nbsp;</th>
						<th>&nbsp;兒童人數&nbsp;</th>
						<th>&nbsp;訂單金額&nbsp;</th>
						<th>&nbsp;備註&nbsp;</th>
						<th>&nbsp;入住情況&nbsp;</th>
						<th>&nbsp;訂單成立日期&nbsp;</th>
					</tr>
				</thead>

				<tbody id="itemContainer">

					<c:forEach var="num" begin="0" end="${fn:length(select)-1}">
							<c:url value="/02_Server/_60_Order/BUpdateOrder.jsp" var="path">
								<c:param name="order_id" value="${select[num].order_id }" />
								<c:param name="member_account" value="${select[num].member_account.member_account }" />
								<c:param name="guestid" value="${select[num].guest_id.guest_id }" />
								<c:param name="guestfn" value="${select[num].guest_id.guest_first_name }" />
								<c:param name="guestln" value="${select[num].guest_id.guest_last_name}" />
								<c:param name="room_type" value="${select[num].room_type }" />
								<c:param name="room_id" value="${select[num].room_id.room_id }" />
								<c:param name="checkin_date" value="${select[num].checkin_date }" />
								<c:param name="checkout_date" value="${select[num].checkout_date }" />
								<c:param name="adults" value="${select[num].adults }" />
								<c:param name="children" value="${select[num].children }" />
								<c:param name="total_payment" value="${select[num].total_payment }" />
								<c:param name="note" value="${select[num].note }" />
								<c:param name="transaction_condition" value="${select[num].transaction_condition }" />
								<c:param name="book_date" value="${select[num].book_date }" />
							</c:url>
						<tr>
							<td>${select[num].order_id }</td>
							<td>${select[num].guest_id.guest_first_name } ${select[num].guest_id.guest_last_name}</td>
							<td>&nbsp;${select[num].room_type }&nbsp;</td>
							<td>&nbsp;<fmt:formatDate value="${select[num].checkin_date }" pattern="yyyy-MM-dd" />&nbsp;</td>
							<td>&nbsp;<fmt:formatDate value="${select[num].checkout_date}" pattern="yyyy-MM-dd" />&nbsp;</td>
							<td>${select[num].adults }</td>
							<td>${select[num].children }</td>
							<td>${select[num].total_payment }</td>
							<td>${select[num].note }</td>
							<c:choose>
							<c:when test="${select[num].transaction_condition eq 1}">
								<td>尚未入住</td>
							</c:when>
							<c:otherwise >
								<td>已入住</td>
							</c:otherwise>
							</c:choose>
							<td><fmt:formatDate value="${select[num].book_date }" pattern="yyyy-MM-dd" /></td>
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
<%-- 		<%@ include file="../footer.jsp"%> --%>
</body>
</html>