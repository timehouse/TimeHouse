<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>InsertMemberForm</title>
<form action="<c:url value="/member/register.controller"/>" method="post">
	<table>
		<tr>
			<td>帳號</td>
			<td><input type="text" name="account" value="${param.account}" /></td>
			<td></td>
		</tr>
		<tr>
			<td>密碼</td>
			<td><input type="password" name="password" value="${param.password}"/></td>
			<td></td>
		</tr>
		<tr>
			<td>姓</td>
			<td><input type="text" name="lastName" value="${param.lastName}"></td>
			<td></td>
		</tr>
		<tr>
			<td>名</td>
			<td><input type="text" name="firstName" value="${param.firstName}"></td>
			<td></td>
		</tr>
		<tr>
			<td>稱呼</td>
			<td>
				<select name="gender">
					<option value="mr">先生</option>
					<option value="mrs">女士</option>
					<option value="ms">小姐</option>
				</select>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>身分證字號</td>
			<td><input type="text" name="idNumber" value="${param.idNumber}"></td>
			<td></td>
		</tr>
		<tr>
			<td>信用卡</td>
			<td><input type="text" name="creditCard" value="${param.creditCard }"></td>
			<td></td>
		</tr>
		<tr>
			<td>生日</td>
			<td><input type="date" name="birthday" value="${param.birthday }"></td>
			<td></td>
		</tr>
		<tr>
			<td>行動電話</td>
			<td><input type="text" name="phone" value="${param.phone}"></td>
			<td></td>
		</tr>
		<tr>
			<td>家用電話</td>
			<td><input type="text" name="tel" value="${param.tel}"></td>
			<td></td>
		</tr>
		<tr>
			<td>地址</td>
			<td><input type="text" name="address" value="${param.address}" ></td>
			<td></td>
		</tr>
		<tr>
			<td>電子郵件</td>
			<td><input type="text" name="email" value="${param.email }"></td>
			<td></td>
		</tr>
	</table>
</form>
</body>
</html>