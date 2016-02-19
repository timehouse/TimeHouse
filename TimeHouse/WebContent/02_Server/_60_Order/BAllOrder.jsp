<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 為什麼這裡的href只要上一層?? -->
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/justified-nav.css" />
<link rel="stylesheet" href="../css/jPages.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理訂單</title>

<style>

td,th {
	text-align: center;
}
/* 分頁的style */
.pagination {
            font-size: 80%;
}
.pagination a {
    text-decoration: none;
 border: solid 1px #AAE;
 color: #15B;
}
.pagination a, .pagination span {
    display: block;
    float: left;
    padding: 0.3em 0.5em;
    margin-right: 5px;
 margin-bottom: 5px;
}
.pagination .current {
    background: #26B;
    color: #fff;
 border: solid 1px #AAE;
}
.pagination .current.prev, .pagination .current.next{
 color:#999;
 border-color:#999;
 background:#fff;
}
</style>
<!-- 登入成功才可導向此頁面 -->
<%-- 	<c:if test="${empty LoginOK}"> --%>
<%-- 			<c:set var="target" value="${pageContext.request.servletPath }" scope="session" /> --%>
<%-- 		<c:redirect url="/02_Server/_51_AdminLogin/AdminLogin.jsp" /> --%>
<%-- 	</c:if> --%>
</head>
<body>
	<c:if test="${ not empty select }" />
	<form>
		<span>目前還無法模糊查詢；只做了用帳號查詢，且切換頁面後select無法固定</span>
		<span>無聊再弄查詢Guest與FacDetail</span>
		<select name="select"
			id="select">
			<option value="0">查詢</option>
			<option value="member_account">會員帳號</option>
			<option value="checkin_date">入住日期</option>
			<option value="checkout_date">退房日期</option>
		</select> <input type="text" name="search" id="search" value="${param.search }">
		<button type="button" id="searchBtn" class="btn btn-default" onClick="selectByMember()">
        	<span class="glyphicon glyphicon-search"></span>
		</button>
		<button type="button"  class="btn btn-default" onClick="selectAll()">
        	<span class="glyphicon glyphicon-refresh"></span>全部訂單
		</button>
		<button type="button"  class="btn btn-default" onClick="calendar()">
        	<span class="glyphicon glyphicon-calendar"></span>
        </button>
	</form>
	<form>
		<table border="1">
			<thead>
				<tr>
					<th>訂單編號</th>
					<th>會員帳號</th>
					<th>住房者姓名</th>
					<th>房型</th>
					<th>房號</th>
					<th>入住日期</th>
					<th>退房日期</th>
					<th>成人人數</th>
					<th>兒童人數</th>
					<th>訂單金額</th>
					<th>備註</th>
					<th>入住情況</th>
					<th>訂單成立日期</th>
					<th>修改訂單</th>
					<th>刪除訂單</th>
				</tr>
			</thead>

			<tbody id="itemContainer">
		
				<c:forEach var="num" begin="0" end="${fn:length(select)-1}">
					<c:url value="/02_Server/_60_Order/BUpdateOrder.jsp" var="path">
						<c:param name="order_id" value="${select[num].order_id }" />
						<c:param name="member_account"
							value="${select[num].member_account.member_account }" />
						<c:param name="guestid" value="${select[num].guest_id.guest_id }" />
						<c:param name="guestfn"
							value="${select[num].guest_id.guest_first_name }" />
						<c:param name="guestln"
							value="${select[num].guest_id.guest_last_name}" />
						<c:param name="room_type" value="${select[num].room_type }" />
						<c:param name="room_id" value="${select[num].room_id.room_id }" />
						<c:param name="checkin_date" value="${select[num].checkin_date }" />
						<c:param name="checkout_date"
							value="${select[num].checkout_date }" />
						<c:param name="adults" value="${select[num].adults }" />
						<c:param name="children" value="${select[num].children }" />
						<c:param name="total_payment"
							value="${select[num].total_payment }" />
						<c:param name="note" value="${select[num].note }" />
						<c:param name="transaction_condition"
							value="${select[num].transaction_condition }" />
						<c:param name="book_date" value="${select[num].book_date }" />
					</c:url>
					<tr>
						<td>${select[num].order_id }</td>
						<%-- 							<td><a href="<c:url value='/server/BSeletctOrderServlet.controller?member_account=${select[num].member_account.member_account }'/>" onclick="clickSelect()">${select[num].member_account.member_account }</a></td> --%>
						<td><a href="#"
							onclick="clickSelect('${select[num].member_account.member_account }')">${select[num].member_account.member_account }</a></td>
						<%-- 							<td onclick="clickSelect('${select[num].member_account.member_account }')">${select[num].member_account.member_account }</td> --%>
						<%-- 							<td><input type="button" onClick="clickSelect('${select[num].member_account.member_account }')"></td> --%>
						<td>${select[num].guest_id.guest_first_name }
							${select[num].guest_id.guest_last_name}</td>
						<td>${select[num].room_type }</td>
						<td>${select[num].room_id.room_id }</td>
						<td><a href="#"
							onclick="clickSelectCI('<fmt:formatDate value="${select[num].checkin_date }"
								pattern="yyyy-MM-dd" />')"><fmt:formatDate value="${select[num].checkin_date }"
								pattern="yyyy-MM-dd" /></a></td>
<%-- 						<td><fmt:formatDate value="${select[num].checkin_date }" --%>
<%-- 								pattern="yyyy-MM-dd" /></td> --%>
						<td><a href="#"
							onclick="clickSelectCO('<fmt:formatDate value="${select[num].checkout_date }"
								pattern="yyyy-MM-dd" />')"><fmt:formatDate value="${select[num].checkout_date }"
								pattern="yyyy-MM-dd" /></a></td>
<%-- 						<td><fmt:formatDate value="${select[num].checkout_date}" --%>
<%-- 								pattern="yyyy-MM-dd" /></td> --%>
						<td>${select[num].adults }</td>
						<td>${select[num].children }</td>
						<td>${select[num].total_payment }</td>
						<td>${select[num].note }</td>
						<c:choose>
							<c:when test="${select[num].transaction_condition eq 1}">
								<td><button type="button" class="btn btn-default" onClick="changeOrder(${select[num].order_id })">
									<span class="glyphicon glyphicon-remove-circle"></span>尚未入住
								</button></td>
							</c:when>
							<c:otherwise>
								<td><button type="button" class="btn btn-default" onClick="changeOrder(${select[num].order_id })">
									<span class="glyphicon glyphicon-ok-circle"></span>已入住　
								</button></td>
							</c:otherwise>
						</c:choose>
						<td><fmt:formatDate value="${select[num].book_date }"
								pattern="yyyy-MM-dd" /></td>
						<td><button type="button" class="btn btn-default" onClick="window.location.href ='${path}'">
							<span class="glyphicon glyphicon-pencil"></span>
							</button></td>
						<td><button type="button" class="btn btn-default" onClick="deleteOrder(${select[num].order_id })">
							<span class="glyphicon glyphicon-trash"></span>
							</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="holder"></div>
	</form>
	
	<script type="text/javascript">
		function deleteOrder(id) {
			if (confirm("確定刪除此筆訂單 ? ") ) {
				document.forms[0].action="<c:url value='/server/BDeleteOrderServlet.controller?delete=delete&id=" + id +"' />" ;
				document.forms[0].method="POST";
				document.forms[0].submit();
			} 
		}
		function changeOrder(id) {
			if (confirm("確定修改入住情況 ? ") ) {
				
				document.forms[0].action="<c:url value='/server/BDeleteOrderServlet.controller?delete=change&id=" + id +"' />" ;
				document.forms[0].method="POST";
				document.forms[0].submit();
			} 
		}
		function selectByMember() {
			document.forms[0].action="<c:url value='/server/BSeletctOrderServlet.controller' />";
			document.forms[0].method="POST";
			document.forms[0].submit();
			
		}
		function clickSelect(mb) {
			document.getElementById("select").value= "member_account";
			document.getElementById("search").value= mb;			
			selectByMember();
			
		}
		function clickSelectCI(checkin) {
			document.getElementById("select").value= "checkin_date";
			document.getElementById("search").value= checkin;			
			selectByMember();
			
		}
		function clickSelectCO(checkout) {
			document.getElementById("select").value= "checkout_date";
			document.getElementById("search").value= checkout;			
			selectByMember();
			
		}
		function selectAll(){
			document.forms[0].action="<c:url value='/server/BAllOrderServlet.controller' />" ;
			document.forms[0].method="POST";
			document.forms[0].submit();
			
		}
		function calendar(){
			location.href="<c:url value="/02_Server/_60_Order/BAllCalendarOrder.jsp"/>"
		}
		
		</script>
		<script type="text/javascript" src="../js/jquery-2.2.0.min.js"></script>
		<script type="text/javascript" src="../js/jPages.min.js"></script>
		<script type="text/javascript">
		$("div.holder").jPages({
			containerID: "itemContainer"
			});
		</script>
</body>
</html>