<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改訂單</title>
<!-- 登入成功才可導向此頁面 -->
<%-- 	<c:if test="${empty LoginOK}"> --%>
<%-- 			<c:set var="target" value="${pageContext.request.servletPath }" scope="session" /> --%>
<%-- 		<c:redirect url="/02_Server/_51_AdminLogin/AdminLogin.jsp" /> --%>
<%-- 	</c:if> --%>
</head>
<body>
	<div>
		<form method="post" name="updateOrder"
			action="<c:url value="/server/BUpadteOrderServlet.controller"/>">
			<div>
				<label>訂單編號：</label> <input type="text" name="id"
					value="${param.order_id}" readonly="readonly" />
			</div>
			<div>
				<label>會員帳號：</label> <input type="text" name="member_account"
					value="${param.member_account}" readonly="readonly" />
			</div>
				<input type="hidden" name="guestid"
					value="${param.guestid}"/>
			</div>
			<div>
				<label>住房者姓名：</label> <input type="text" name="guest"
					value="${param.guestfn} ${param.guestln}" />
				<span>${error.guest}</span>
			</div>
			<div>
				<label>房型：</label> <input type="text" name="room_type"
					value="${param.room_type}" readonly="readonly" />
			</div>
			<div>
				<label>房號：</label> <input type="text" name="room_id"
					value="${param.room_id}" readonly="readonly" />
			</div>
				<label>入住日期：</label><script type="text/javascript">
				var str='${param.checkin_date}'; 
				document.write( '<input type="text" name="checkin_date" value="'+str.split(" ")[0]+'" readonly="readonly" />');
				</script>
			</div>
			<div>
			<label>退房日期：</label><script type="text/javascript">
				var str='${param.checkout_date}'; 
				document.write( '<input type="text" name="checkout_date" value="'+str.split(" ")[0]+'" readonly="readonly" />');
				</script>
			</div>
			<div>
				<label>成人人數：</label> <input type="text" name="adults"
					value="${param.adults}" />
				<span>${error.adults}</span>
			</div>
			<div>
				<label>兒童人數：</label> <input type="text" name="children"
					value="${param.children}" />
				<span>${error.children}</span>
			</div>
			<div>
				<label>訂單金額：</label> <input type="text" name="total_payment"
					value="${param.total_payment}" readonly="readonly" />
			</div>
			<div>
				<label>備註：</label>
				<textarea name="note">${param.note}</textarea>
				<span>${error.note}</span>
			</div>
			<div>
				<label>訂單狀況：</label> <input type="text" name="transaction_condition"
					value="${param.transaction_condition}"  />
				<span>${error.transaction_condition}</span>
			</div>
			<div>
				<label>訂單成立日期：</label>
				<script type="text/javascript">
				var str='${param.book_date}'; 
				document.write( '<input type="text" name="book_date" value="'+str.split(" ")[0]+'" readonly="readonly" />');
				</script>
			</div>
			<div>
				<div>
					<input type="submit" name="send" value="確認"> <input
						type="reset" name="clear" value="清除"> <input
						type="button" name="cancel" value="取消" onclick="window.location.href ='<c:url value="/server/BAllOrderServlet.controller"/>'">
				</div>
			</div>
		</form>
	</div>

</body>
</html>