<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form name="myForm" id="myForm"
		action="<c:url value="/broom/broomeServlet"/>" method="post">
		<input type="text" placeholder="roomid" name="roomid" id="roomid">
		<input type="text" placeholder="roomid2" name="roomid2" id="roomid2">
		<input type="submit" name="action" id='b1' value="listroom" /> <input
			type="button" value="AllSubmit" id="AllSubmit" name="AllSubmit" /> <input
			type="button" value="addRoom" id="addRoom" name="addRoom" />
			<input type="button" value="delRoom" id="delRoom" name="delRoom" />
	</form>
	<table class="table table-border">
		<thead>
			<tr>
				<th>room_id</th>
				<th>rStatus</th>
				<th>room_type</th>
				<th>rContext</th>
				<th>重設鈕</th>
				<th>刪除鈕</th>
				<th>送出鈕</th>
			</tr>
		</thead>
		<tbody id="tb">

		</tbody>
	</table>
	<div id="dtest"></div>
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
		//		console.log(rooms.get("101"));

		//抓取el的roomTypeMap(編號&對應字串)轉為Map物件
		<c:if test="${not empty roomTypeMap}">
		var roomType = new Map();
		<c:forEach var="roomType" items="${roomTypeMap}" >
		roomType.set("${roomType.key}", "${roomType.value}");
		</c:forEach>
		</c:if>

		$(function() {
			//顯示全房間
			callListroom();
		});

		function callListroom() {
			if (typeof (rooms) != "undefined") {
				//清空Tbody
				$("#tb").empty();
				rooms.forEach(listroom);
				//val "${room.rStatus}", "${room.room_type}", "${room.rContext}" 
				//綁定房況有無變化
				$("#tb").on("change", "input:radio", rStatus);
				//綁定房況資料變化
				$("#tb").on("change", "input:text[name=rContext]", rContext);
				//綁訂房型選單變化
				$("#tb").on("change", "select[name=room_type]", room_type);
				//綁定reset鈕
				$("#tb").on("click", "a[name=res]", res);
				//綁定submit鈕
				$("#tb").on("click", "a[name=subOne]", subOne);
				//綁定delete鈕
				$("#tb").on("click", "a[name=delOne]", delOne);
				//全部送出
				$("#AllSubmit").bind("click", AllSubmit);
				//增加單筆&批次房間
				$("#addRoom").bind("click", addRoom);
				//刪除單筆&批次房間
				$("#delRoom").bind("click", delRoom);
			}
			;
		}

		function addRoom() {
			document.getElementById("imgLoad").style.display = "inline";
			var x = {};
			x.action = 'addRoom';
			x.room_id = $("#roomid").val();
			x.room_id2 = $("#roomid2").val();
			$.post('<c:url value="/broom/broomeServlet"/>', x, function(data) {
				window.location = '<c:url value="/broom/broomeServlet?action=listroom"/>';
			}).fail(function() {
				alert("新增失敗");
			}).always(function() {
				document.getElementById("imgLoad").style.display = "none";
			});
		}
		function delRoom(){
			document.getElementById("imgLoad").style.display = "inline";
			var x = {};
			x.action = 'delRoom';
			x.room_id = $("#roomid").val();
			x.room_id2 = $("#roomid2").val();
			$.post('<c:url value="/broom/broomeServlet"/>', x, function(data) {
				window.location = '<c:url value="/broom/broomeServlet?action=listroom"/>';
			}).fail(function() {
				alert("刪除失敗,請洽管理員");
			}).always(function() {
				document.getElementById("imgLoad").style.display = "none";
			});	
		}

		//參考老師的jQueryAjax, 動態產生Selector
		function makeRoomTypeSelector(room_type) {
			var cellSelector = $('<select name="room_type"></select>');
			roomType.forEach(function(value, key, map) {
				var cell = $("<option>").val(key).text(value);
				if ($(cell).text() == room_type) {
					$(cell).attr("selected", true);
				}
				cellSelector.append(cell);
			})
			return cellSelector;
		}

		//val : ${room.rStatus}", "${room.room_type}", "${room.rContext}

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

			//將選擇器選擇對應room_type
			var cellSelector = makeRoomTypeSelector(value[1]);
			var cell4 = $("<td></td").append(cellSelector);

			var text5 = $("<input type='text' name='rContext'/>").val(value[2]);
			var cell5 = $("<td></td").append(text5);

			var cell6 = $("<td></td").html('<a href="#" class="btn btn-danger reset" >del</a>');

			var cell6 = $("<td></td").html('<a href="#" class="btn btn-danger" name="res">reset</a>');
			var cell7 = $("<td></td").html('<a href="#" class="btn btn-danger" name="delOne">delete</a>');
			var cell8 = $("<td></td").html('<a href="#" class="btn btn-danger" name="subOne">submit</a>');
			var tr = $("<tr id="+key+" name='room'></tr").append([ cell1, cell3, cell4, cell5, cell6, cell7, cell8 ]);

			$("#tb").append(tr);

			//讀取資料修改房間狀態,1為正常,0為不正常
			if (value[0] == "true") {
				$(status1).prop("checked", true);
			} else {
				$(status2).prop("checked", true);
			}

		}

		//============預定把綁定區函數丟到這============
		function rStatus() {
			var id = $(this).parents("tr").attr("id");
			rooms.get(id)[0] = $(this).val();
			console.log(rooms.get(id));
		}
		function rContext() {
			var id = $(this).parents("tr").attr("id");
			rooms.get(id)[2] = $(this).val();
			console.log(rooms.get(id));
		}
		function room_type() {
			var id = $(this).parents("tr").attr("id");
			rooms.get(id)[1] = $(this).children("option:selected").text();
			//console.log($(this).val());
			console.log(rooms.get(id)[1]);
		}
		function res() {
			var id = $(this).parents("tr").attr("id");
			$("#" + eval(id) + " input:radio[value=" + roomsBackup.get(id)[0] + "]").prop("checked", true);
			$("#" + eval(id) + " input:text[name=rContext]").val(roomsBackup.get(id)[2]);
		}
		function subOne() {
			var id = $(this).parents("tr").attr("id");
			var x = {};
			x.action = this.name;
			x.id = id;
			x.rStatus = rooms.get(id)[0];
			x.room_type = rooms.get(id)[1];
			x.rContext = rooms.get(id)[2];
			document.getElementById("imgLoad").style.display = "inline";
			$.post('<c:url value="/broom/broomeServlet"/>', x).fail(function() {
				alert("新增失敗");
			}).always(function() {
				document.getElementById("imgLoad").style.display = "none";
			});
		}
		function delOne() {
			var id = $(this).parents("tr").attr("id");
			var x = {};
			x.action = this.name;
			x.id = id;
			document.getElementById("imgLoad").style.display = "inline";
			$.post('<c:url value="/broom/broomeServlet"/>', x, function(data) {
				$("#" + id).remove();
			}).always(function() {
				document.getElementById("imgLoad").style.display = "none";
			});
		}
		function AllSubmit() {
			document.getElementById("imgLoad").style.display = "inline";
			var x = {}, i = 0;
			x.action = this.name;
			rooms.forEach(function(value, key, map) {
				x["room_id" + i] = key;
				x["rStatus" + i] = value[0];
				x["room_type" + i] = value[1];
				console.log(value[1]);
				x["rContext" + i] = value[2];
				i++;
			});
			x.number = i;
			$.post('<c:url value="/broom/broomeServlet"/>', x, function(data) {
				document.getElementById("imgLoad").style.display = "none";
			}).always(function() {
				document.getElementById("imgLoad").style.display = "none";
			});
		}
	</script>
</body>
</html>