<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../../css/game.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Game Over</title>
</head>
<body>
	<audio id="audio" class="Bgmusic"  autoplay="autoplay" loop="loop" >
        <source src="../../music/laugh.mp3" type="audio/mp3"/>
    </audio>

	<img src="../../img/memoryGame/defeat.png">
	<button type="button" id="Again" class="demo" >重新開始</button>
	
	<script>
	var $ = function (obj) { return document.getElementById(obj); }
	
	$("audio").volume = 0.5;
	
	$("Again").onclick = function () {
        window.location.href="<c:url value="/01_Client/_23_Game/Memory.jsp"/>";
 }
	</script>
</body>
</html>