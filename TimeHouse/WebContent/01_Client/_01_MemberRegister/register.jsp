<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>InsertMemberForm</title>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<style>
	.st1 {
		width: 450px;
		margin: 3px;
		border-bottom: 1px solid #000000;
		padding-bottom: 2px;
		margin:3px auto;
	}
	#but1 {
		width: 300px;
		text-align: center;
		margin:0 auto;
	}
	.lab1{
		float: left;
		text-align: right;	
		padding-right:5px;
	}
	.fis1{
		width: 600px;
		margin:5px auto;
	}
	.em1{
		color: red;
	}
	.addr{
		width:600px;
		margin:3px auto;
	}
	.error{
		color:red;
	}
</style>

</head>
<body>
	<form action="<c:url value="/member/register.controller"/>" method="post" style="margin: 0 auto;">
		<fieldset class="fis1">
			<legend>註冊會員</legend>
			<div class="st1">
				<label class="lab1" for="n1">姓名</label> 
					<input type="text" size="10" id="n1" name="lastName" placeholder="姓" value="${param.lastName}"/>
					<input type="text" size="10" id="n1" name="firstName"placeholder="名" value="${param.firstName }"/>
			</div>
			<div class="st1">
				<label class="lab1" for="p1">*帳號 </label>
				<input type="text" name="account" size="10" id="account" value="${param.account}"/>
				<span class="error">${error.account }</span>
				<span class="error" id="divAccount" ></span>				
			</div>
			<div class="st1">
				<label class="lab1" for="p1">*密碼 </label>
				<input type="password" name="password" size="10" id="p1" value="${param.password}"/>
<!-- 				<span>密碼要包含英文大小寫、數字、@#$%^&+=</span> -->
				<span class="error" >${error.password}</span>
			</div>
			<div class="st1">
				<label class="lab1">性別 </label> 
					<input type="radio" name="gender" value="male" >先生
					<input type="radio" name="gender" value="female">女士
					<input type="radio" name="gender" value="miss">小姐
			</div>
			<div class="st1">
				<label class="lab1">生日 </label> 
				<input type="text" name="birth" value="${param.birth}" placeholder="2000-02-29"/>
				<span class="error" >${error.birth}</span>
			</div>
			<div class="st1">
				<label class="lab1">身分證字號 </label> 
				<input type="text" name="idNumber" value="${param.idNumber}" />
			</div>
			<div class="st1">
				<label class="lab1">*E-Mail </label> 
				<input type="text" name="email" value="${param.email}"/>
				<span class="error" >${error.email}</span>
			</div>
			<div class="st1">
				<label class="lab1">*行動電話:</label> 
				<input type="text" name="phone" value="${param.phone}"/>
				<span class="error" >${error.phone }</span>
			</div>
			<div class="st1">
				<label class="lab1">連絡電話:</label> 
				<input type="text" name="tel" value="${param.tel}"/>
			</div>
			<div class="st1">
				<label class="lab1">信用卡:</label> 
				<input type="text" name="creditCard" value="${param.creditCard }"/>
			</div>
			<div class="st1">
				<label class="lab1">地址:</label> 
				<input type="text" name="addr" size="20" value="${param.addr }">
			</div>		
			<div id="but1">
				<input type="submit" name="send" value="送出"> 
				<input type="button" name="clear" value="清除" onclick="clear">
			</div>
		</fieldset>
	</form>
<script src="js/jquery-2.2.0.min.js"></script>
<script type="text/javascript">
	function clear(){
		alert("fdsfsdf")
// 		$(".error").empty();
// 		$("span").removeClass("error");
// 		$('#account').remove();
	}

</script>
</body>
</html>