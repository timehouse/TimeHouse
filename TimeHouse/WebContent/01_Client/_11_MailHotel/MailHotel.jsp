<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../css/justified-nav.css" />
<!-- 當controller處理時，用下面的連結 -->
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/justified-nav.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聯絡我們</title>
<style>

</style>
</head>
<body>
	<div>
		<div>
			<h2>
				聯絡我們<em>Contact Us</em>
			</h2>
		</div>
		會員登入時，會自動帶入資料
		<div>
			<form method="post" name="mail_form" action="<c:url value="/client/mailHotelServlet.controller"/>"
				id="mail_form">
				<div>
					<label>問題分類</label> <select name="type" id="type">
						<option value="請選擇分類" selected="selected">請選擇分類</option>
						<option value="餐飲服務">餐飲服務</option>
						<option value="訂房服務洽詢">訂房服務洽詢</option>
						<option value="客房服務洽詢">客房服務洽詢</option>
						<option value="人力資源相關">人力資源相關</option>
						<option value="行銷合作/公關媒體">行銷合作/公關媒體</option>
						<option value="其他">其他</option>
					</select><span>${error.type}</span>
				</div>
				<c:if test="${empty LoginOK }">
				<div>
					<label><em>*</em>姓名</label> <input type="text" id="name"
						name="name" value="${param.name}"><span>${error.name}</span>
				</div>
				<div> 
					<input type="hidden" id="member" name="member" value="none" >
				</div>
				<div>
					<label><em>*</em>聯絡信箱</label> <input type="text" id="email"
						name="email" value="${param.email}"><span>${error.email}</span>
				</div>
				</c:if>
				
<!-- 				當會員登入時 -->
				<c:if test="${not empty LoginOK }">
				<div>
					<label><em>*</em>姓名</label> <input type="text" id="name"
						name="name" value="${firstName} ${lastName}"><span>${error.name}</span>
				</div>
					<input type="hidden" id="member" name="member" value="${account }" >
				<div>
					<label><em>*</em>聯絡信箱</label> <input type="text" id="email"
						name="email" value="${email}"><span>${error.email}</span>
				</div>
				</c:if>
				<div>
					<label><em>*</em>需求說明</label>
					<textarea id="content" name="content" >${param.content}</textarea>
					<span>${error.content}</span>
				</div>
					<div>
						<button type="submit" id="submit" class="btn btn-default" >
        					<span class="glyphicon glyphicon-envelope"></span>　送出
						</button>
						<button type="reset" id="reset" class="btn btn-default" >
        					<span class="glyphicon glyphicon-erase"></span>　清除
						</button>
						<input type="button" name="insert" value="一鍵輸入" onClick="insertFalseInfo()">
					</div>
			</form>
		</div>
	</div>
	
	
	<script>
	function insertFalseInfo() {
		document.getElementById("type").options[6].selected= true;
// 		document.getElementById("name").value= "賈伯斯";
// 		document.getElementById("email").value= "zzz@gmail.com";
		document.getElementById("content").value= "白癡";
		
	}
	
	</script>
</body>
</html>