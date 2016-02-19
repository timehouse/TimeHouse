<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回應訊息</title>
<!-- 登入成功才可導向此頁面 -->
<%-- 	<c:if test="${empty LoginOK}"> --%>
<%-- 			<c:set var="target" value="${pageContext.request.servletPath }" scope="session" /> --%>
<%-- 		<c:redirect url="/02_Server/_51_AdminLogin/AdminLogin.jsp" /> --%>
<%-- 	</c:if> --%>
</head>
<body>
	<div>
		<form method="post" name="reply_form"
			action="<c:url value="/server/BMailServlet.controller"/>"
			id="mail_form">
			<div>
				<label>訊息編號：</label> <input type="text" name="id" value="${param.id}"
					readonly="readonly" />
				<div>${error.id}</div>
			</div>
			<div>
				<label>問題分類：</label> <input type="text" name="type"
					value="${param.type}" readonly="readonly" />
				<div>${error.type}</div>
			</div>
			<div>
				<label>客戶姓名：</label> <input type="text" name="name"
					value="${param.name}" readonly="readonly" />
				<div>${error.name}</div>
			</div>
			<div>
				<label>電子信箱：</label> <input type="text" name="email"
					value="${param.email}" readonly="readonly" />
				<div>${error.email}</div>
			</div>
			<div>
				<label>需求說明：</label> <input type="text" name="content"
					value="${param.content}" readonly="readonly" />
				<div>${error.content}</div>
			</div>
			<div>
				<label>回應</label>
				<textarea id="reply" name="reply" value="${param.reply}"></textarea>
				<div>${error.reply}</div>
			</div>
			<div>
				<div>
					<input type="submit" name="send" value="送出"> <input
						type="reset" name="clear" value="清除"> <input
						type="button" name="cancel" value="取消" onclick="window.location.href ='<c:url value="/server/BAllMailServlet.controller"/>'">
				</div>
			</div>
		</form>
	</div>

</body>
</html>