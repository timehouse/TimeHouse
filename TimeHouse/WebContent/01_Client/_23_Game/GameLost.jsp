<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../../css/game.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Game Over</title>
<style>
	.imgdiv {
		text-align:center;
	}
	.demo {
		width: 150px;
		height: 80px;
		font-size: 30px;
		font-family: 微軟正黑體;
		margin-top:20px;
	}

</style>
</head>
<body>
	<%@ include file="../header.jsp"%>
	<audio id="audio" class="Bgmusic"  autoplay="autoplay" loop="loop" >
        <source src="../../music/laugh.mp3" type="audio/mp3"/>
    </audio>
    <div style="height:110px"></div>
	<div class="imgdiv">
		<button type="button" id="Again" class="demo" >重新開始</button>
		</br>
		<img src="../../img/memoryGame/defeat.png">
	</div>
	
	<script>
	var $ = function (obj) { return document.getElementById(obj); }
	
	$("audio").volume = 0.5;
	
	$("Again").onclick = function () {
        window.location.href="<c:url value="/01_Client/_23_Game/Memory.jsp"/>";
 }
	</script>
	<%@ include file="../footer.jsp"%>
</body>
</html>