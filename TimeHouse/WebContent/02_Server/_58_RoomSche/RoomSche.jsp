<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <script src='<c:url value="/js/jquery-1.12.0.js"/>'></script> --%>
<%-- <link rel="stylesheet" href='<c:url value="/css/bootstrap.min.css"/>'> --%>
<%-- <link rel="stylesheet" href='<c:url value="/css/justified-nav.css"/>'> --%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form name="myForm" id="myForm"
		action="<c:url value="/roomSche/roomScheServlet"/>" method="post">
		<input type="text" placeholder="roomId" name="roomId"> 
		<input
			type="submit" name="action" id='b1' value="listroom" />
			<input type="button" value="AllSubmit" id="AllSubmit" name="AllSubmit" />
	</form>
	<table class="table table-border">
		<thead>
			<tr>
				<th>room_id</th>
				<th>rStatus</th>
				<th>room_type</th>
				<th>rContext</th>
				<th>重設鈕</th>
				<th>送出鈕</th>
			</tr>
		</thead>
		<tbody id="tb">

		</tbody>
		
	</table>
	<img id="imgLoad" src="<c:url value="/images/ajax-loader.gif"/>"
		style="display: none;">

	<script type="text/javascript">
		//抓取el的rooms轉為Map物件
		<c:if test="${not empty rooms}">
		var rooms = new Map();
		var roomsBackup = new Map();
		<c:forEach var="room" items="${rooms}" >
		rooms.set("${room.room_id}", [ "${room.rStatus}", "${room.room_type}", "${room.rContext}" ]);
		</c:forEach>
		</c:if>
		console.log(rooms.get("101"));

		$(function() {
			callListroom();
		});

		function callListroom() {
			if (typeof (rooms) != "undefined") {
				//清空Tbody
				$("#tb").empty();
				rooms.forEach(listroom);
				
				//綁定變化
				$("#tb").on("change", "input:radio", function() {
					var id = $(this).parents("tr").attr("id");
					rooms.get(id)[0] = $(this).val();
					console.log(rooms.get(id));
				});
				$("#tb").on("change", "input:text[name=rContext]", function() {
					var id = $(this).parents("tr").attr("id");
					rooms.get(id)[2] = $(this).val();
					console.log(rooms.get(id));
				});
				//綁定reset鈕
				$("#tb").on("click", "a[name=res]", function() {
					var id = $(this).parents("tr").attr("id");
					$("#" + eval(id) + " input:radio[value=" + roomsBackup.get(id)[0] + "]").prop("checked", true);
					$("#" + eval(id) + " input:text[name=rContext]").val(roomsBackup.get(id)[2]);
				});
				
				//綁定submit鈕
				$("#tb").on("click", "a[name=subOne]", function() {
					var id = $(this).parents("tr").attr("id");
					var x = {};
					x.action=this.name;
					x.id=id;
					x.rStatus=rooms.get(id)[0];
					x.rContext=rooms.get(id)[2];
					document.getElementById("imgLoad").style.display="inline";
					$.post('<c:url value="/roomSche/roomScheServlet"/>',x, function(data) {
						document.getElementById("imgLoad").style.display="none";
					});
				});
				//全部送出
				$("#AllSubmit").bind("click", function(){
					document.getElementById("imgLoad").style.display="inline";
					var x = {}, i=0;
					x.action=this.name;
					rooms.forEach(function(value,key,map){
						x["room_id"+i]=key;
						x["rStatus"+i]=value[0];
						x["rContext"+i]=value[2];
						i++;
					});
					x.number=i;
					$.post('<c:url value="/roomSche/roomScheServlet"/>',
							x, 
							function(data) {
						document.getElementById("imgLoad").style.display="none";
					});
				});
			};
		}
		//${room.rStatus}", "${room.room_type}", "${room.rContext}

		//根據js的Map rooms顯示房間列表
		function listroom(value, key, map) {
			var x = new Array();
			roomsBackup.set(key.toString(), $.extend(x, value));
			//顯示房間
			var cell1 = $("<td></td").text(key);
			//var cell2 = $("<td></td").text(value[0]);

			var status1 = $("<input type=radio name=radio"+key+" value='true' />");
			var status2 = $("<input type=radio name=radio"+key+" value='false' />");
			var cell3 = $("<td name='rStatus'></td").append(status1).append("true").append(status2).append("False");
			var cell4 = $("<td></td").text(value[1]);

			var text5 = $("<input type='text' name='rContext'/>").val(value[2]);
			var cell5 = $("<td></td").append(text5);

			var cell6 = $("<td></td").html('<a href="#" class="btn btn-danger reset" >del</a>');

			var cell6 = $("<td></td").html('<a href="#" class="btn btn-danger" name="res">reset</a>');
			var cell7 = $("<td></td").html('<a href="#" class="btn btn-danger" name="subOne">submit</a>');
			var tr = $("<tr id="+key+" name='room'></tr").append([ cell1, cell3, cell4, cell5, cell6, cell7 ]);

			$("#tb").append(tr);

			//讀取資料修改房間狀態,1為正常,0為不正常
			if (value[0] == "true") {
				$(status1).prop("checked", true);
			} else {
				$(status2).prop("checked", true);
			}

		}
	</script>
</body>

</html>