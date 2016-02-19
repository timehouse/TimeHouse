<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>	
	<link rel="stylesheet" href="../../css/game.css" />
	<link rel="stylesheet" href="../../css/bounceMonster/reset.css" />
	<link rel="stylesheet" href="../../css/bounceMonster/default.css" />
	<link rel="stylesheet" href="../../css/bounceMonster/styles.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>開始遊戲</title>
<style type="text/css">
	.start {
		width: 200px;
		height: 100px;
		font-size: 40px;
		font-family: 微軟正黑體;
		margin: auto;
		margin-top: 100px;
  		display: block;
        text-align: center;
        background: #80CB1B;
        -moz-box-shadow: 0 0 5px #343434;
        -webkit-box-shadow: 0 0 5px #fff;
        box-shadow: 0 0 5px #343434;
        color: #fff;
        font-weight: bold;
        padding: 5px 10px;
        text-decoration: none;
        text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);
        border-radius: 5px;
        cursor: pointer;
        animation-name: breathingLight;
        animation-duration: 3s;
        animation-iteration-count: infinite;
        -webkit-animation-name: breathingLight;
        -webkit-animation-duration: 3s;
        -webkit-animation-iteration-count: infinite;
        -moz-animation-name: breathingLight;
        -moz-animation-duration: 3s;
        -moz-animation-iteration-count: infinite;
	}
	.container {
		margin-top: 10px;
	}
	.explan {
		margin: auto;
		margin-top: 20px;
		text-align: center;
	}
</style>
</head>
<body>
	<button type="button" id="Start" class="start" >開始遊戲</button>
	<div class="explan">
		<h5>完成遊戲，即可獲得飯店設施優惠券</h5>
	</div>
	<div class="container">
		  <div class="item" id="pink">
		    <div class="chewing">
		      <div class="eye left"><span></span></div>
		      <div class="eye right"><span></span></div>
		      <div class="mounth"></div>
		      <div class="arm left"></div>
		      <div class="arm right"></div>
		    </div>
		    <div class="shadow"></div>
		  </div>

		  <div class="item" id="orange">
		    <div class="chewing">
		      <div class="eye left"><span></span></div>
		      <div class="eye right"><span></span></div>
		      <div class="mounth"></div>
		      <div class="arm left"></div>
		      <div class="arm right"></div>
		    </div>
		    <div class="shadow"></div>
		  </div>

		  <div class="item" id="blue">
		    <div class="chewing">
		      <div class="eye left"><span></span></div>
		      <div class="eye right"><span></span></div>
		      <div class="mounth"></div>
		      <div class="arm left"></div>
		      <div class="arm right"></div>
		    </div>
		    <div class="shadow"></div>
		  </div>
		</div>
		
		
		
	<script type="text/javascript" src="../../js/jquery-2.2.0.min.js"></script>
	<script type="text/javascript">
		var $ = function (obj) { return document.getElementById(obj); }
// 		function start(){
// 			location.href="";
			
// 		}
		$("Start").onclick = function () {
 			
			var port = window.location.port;
			var hostname = window.location.hostname;
			var host = window.location.host;
			var pathname = window.location.pathname;
			var hostname = window.location.hostname;
            window.location.href="<c:url value="/01_Client/_23_Game/Memory.jsp"/>";
//          onresize();
// 			alert(pathname);
     }

	</script>
</body>
</html>