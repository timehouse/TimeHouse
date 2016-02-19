<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/justified-nav.css" />
<link rel="stylesheet" href="../css/jPages.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聯絡訊息</title>
<style>
	td,th{
		text-align:center;
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
			<button type="button"  class="btn btn-default" onClick="selectAll()">
			       	<span class="glyphicon glyphicon-refresh"></span>全部訂單
			</button>
			<table border="1">
				<thead>
					<tr>
						<th>訊息編號</th>
						<th> <select name="type" id="type" onChange="typeSelect()">
						<option value="全部類型" selected="selected">全部類型</option>
						<option value="餐飲服務">餐飲服務</option>
						<option value="訂房服務洽詢">訂房服務洽詢</option>
						<option value="客房服務洽詢">客房服務洽詢</option>
						<option value="人力資源相關">人力資源相關</option>
						<option value="行銷合作/公關媒體">行銷合作/公關媒體</option>
						<option value="其他">其他</option>
						</select></th>
						<th>會員帳號</th>
						<th>聯絡者姓名</th>
						<th>聯絡信箱</th>
						<th>訊息內容</th>
						<th>訊息成立日期</th>
						<th>管理員回應</th>
						<th>回應日期</th>
						<th>回應</th>
						<th>黑名單</th>
					</tr>
				</thead>

				<tbody id="itemContainer">
					<c:if test="${not empty select[0] }">
					<c:forEach var="num" begin="0" end="${fn:length(select)-1}">
							<c:url value="/02_Server/_61_Mail/ReplyMail.jsp" var="path">
								<c:param name="id" value="${select[num].message_id }" />
								<c:param name="type" value="${select[num].type }" />
								<c:param name="member_account" value="${select[num].member_account.member_account }" />
								<c:param name="name" value="${select[num].name }" />
								<c:param name="email" value="${select[num].email }" />
								<c:param name="content" value="${select[num].message_content }" />
								<c:param name="message_date" value="${select[num].message_date }" />
							</c:url>
						<tr>
							<td>${select[num].message_id }</td>
							<td><a href="#"
							onclick="clickSelectType('${select[num].type }')">${select[num].type }</a></td>
							<c:if test="${ empty select[num].member_account.member_account }">
								<td>訪客</td>
							</c:if>
							<c:if test="${ not empty select[num].member_account.member_account }">
								<td><a href="#"
							onclick="clickSelectMb('${select[num].member_account.member_account }')">${select[num].member_account.member_account }</a></td>
							</c:if>
							<td>${select[num].name }</td>
							<td>${select[num].email }</td>
							<td>${select[num].message_content }</td>
							<td>${select[num].message_date }</td>
							<c:if test="${not empty select[num].reply }">
								<td>${select[num].reply }</td>
								<td><fmt:formatDate value="${select[num].reply_date }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td></td>
							</c:if>
							<c:if test="${ empty select[num].reply }">
								<td>尚未回應</td>
								<td>尚未回應</td>
								<td><button type="button" class="btn btn-success" onClick="window.location.href ='${path}'">
									<span class="glyphicon glyphicon-pencil"></span>回應　
								</button></td>
							</c:if>
							<c:choose>
								<c:when test="${ not empty select[num].member_account.member_account }">
									<c:choose>
										<c:when test="${ select[num].member_account.limit eq 0}">
											<td><button type="button" class="btn btn-success" onClick="limitMember('${select[num].member_account.member_account}')">
												<span class="glyphicon glyphicon-thumbs-down"></span>　黑名單　
											</button></td>
<%-- 											<td><input type="button" value="黑名單" onClick="limitMember('${select[num].member_account.member_account}')"></td> --%>
										</c:when>
										<c:otherwise>
											<td><button type="button" class="btn btn-success" onClick="limitMember('${select[num].member_account.member_account}')">
												<span class="glyphicon glyphicon-thumbs-up"></span>取消黑名單
											</button></td>
<%-- 											<td><input type="button" value="取消黑名單" onClick="limitMember('${select[num].member_account.member_account}')"></td> --%>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
					</c:if>
				</tbody>

			</table>
			<div class="holder"></div>
		</form>
	<script>
	function selectAll(){
		document.forms[0].action="<c:url value='/server/BAllMailServlet.controller' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
		
	}
	function typeSelect() {
		var type;
		type = document.getElementById("type").value;
		document.forms[0].action="<c:url value='/server/BTypeMailServlet.controller?type=" + type +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
		
	}
	function limitMember(mb){
		if (confirm("確定修改此會員狀況? ") ) {
			document.forms[0].action="<c:url value='/server/BLimitMemberMailServlet.controller?member=" + mb +"' />" ;
			document.forms[0].method="POST";
			document.forms[0].submit();
		}
	}
	function clickSelectMb(mb) {
		document.forms[0].action="<c:url value='/server/BSelectMailServlet.controller?select="+mb+"' />";
		document.forms[0].method="POST";
		document.forms[0].submit();
			
	}
	function clickSelectType(type){
		document.forms[0].action="<c:url value='/server/BTypeMailServlet.controller?type=" + type +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();		
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