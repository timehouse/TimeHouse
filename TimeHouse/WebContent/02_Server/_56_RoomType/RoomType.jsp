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
	<form id="form" name="form"
		action='<c:url value="/broomType/broomTypeServlet"/>' method="post"
		enctype="multipart/form-data">
		<input type="submit" name="action" value="uploadAll" /> <br> <input
			type="submit" name="action" value="listRoomType" /> <br> <input
			type="text" name="roomPicid" value="1" /> roomPicid <br> <input
			type="text" name="roomTypeid" value="1" /> roomTypeid <br> <input
			type="file" name="uploadFile" size="40" /> file還沒做驗證,只能用jpg <br>
	</form>

	<table class="table table-border">
		<thead>
			<tr>
				<th>roomType_id</th>
				<th>room_type</th>
				<th>rtCapacity_num</th>
				<th>rtCount</th>
				<th>rtWeekday_rate</th>
				<th>rtWeekend_rate</th>
				<th>重設鈕</th>
				<th>刪除鈕</th>
				<th>送出鈕</th> 待增加圖片刪除,顯示圖片id
				<th>圖片</th>
			</tr>
		</thead>
		<tbody id="tb">

		</tbody>
		<img id="imgLoad" src="<c:url value="/img/ajax-loader.gif"/>"
			style="display: none;">

	</table>

	<c:if test="${not empty errorMap}">
		<c:forEach var="x" items="errorMap">
			<h1>"${errorMap}"</h1>
			<%-- 			<h1>"${x.key}"</h1> --%>
			<%-- 			<h1>"${x.value}"</h1> --%>
		</c:forEach>
	</c:if>

	<!--  height='112' width='200' -->
	<%-- 	<img	src='${pageContext.servletContext.contextPath}/broomType/broomTypeServlet?action=getImg&imgid=1' /> --%>
	<%-- 	<img	src='${pageContext.servletContext.contextPath}/broomType/broomTypeServlet?action=getImg&imgid=2' /> --%>
	<script>
		//抓取req內的roomTypeMap轉為Map物件
		<c:if test="${not empty roomTypes}">
		var roomTypes = new Map();
		var roomTypesBackup = new Map();
		var roomTypePics = [];
		var i = 0;
		<c:forEach  var="roomType" items="${roomTypes}">
		roomTypePics[i] = [];
		var j = 0;
		<c:forEach var="roomPicVo" items="${roomType.roomPics}" >
		roomTypePics[i][j] = "${roomPicVo.rpPicName}".trim();
		j++;
		</c:forEach>
		roomTypes.set("${roomType.roomType_id}", [ "${roomType.room_type}", "${roomType.rtCapacity_num}",
				"${roomType.rtCount}", "${roomType.rtWeekday_rate}", "${roomType.rtWeekend_rate}", roomTypePics[i] ]);
		i++;
		</c:forEach>

		</c:if>

		//初始化(若有MAP則顯示)
		$(function() {
			//顯示全房間
			callListroomType();
		});
		//函數
		function callListroomType() {
			if (typeof (roomTypes) != "undefined") {
				//清空Tbody
				$("#tb").empty();
				roomTypes.forEach(listroomType);
			}
			;
			//綁訂房型選單變化
			$("#tb").on("change", "input:text[name=room_type]", function() {
				valueChange(this, 0);
			});
			$("#tb").on("change", "input:text[name=rtCapacity_num]", function() {
				valueChange(this, 1);
			});
			$("#tb").on("change", "input:text[name=rtCount]", function() {
				valueChange(this, 2);
			});
			$("#tb").on("change", "input:text[name=rtWeekday_rate]", function() {
				valueChange(this, 3);
			});
			$("#tb").on("change", "input:text[name=rtWeekend_rate]", function() {
				valueChange(this, 4);
			});
			//綁定reset鈕
			$("#tb").on("click", "a[name=res]", res);
			//綁定submit鈕
			$("#tb").on("click", "a[name=subOne]", subOne);
			//綁定delete鈕
			$("#tb").on("click", "a[name=delOne]", delOne);

		}
		// /broomType/broomTypeServlet
		// 		roomTypesBackup
		// 				0<th>room_type</th>
		// 				1<th>rtCapacity_num</th>
		// 				2<th>rtCount</th>
		// 				3<th>rtWeekday_rate</th>
		// 				4<th>rtWeekend_rate</th>
		function subOne() {
			var id = $(this).parents("tr").attr("id");
			var x = {};
			x.action = this.name;
			x.id = id;
			x.room_type = roomTypes.get(id)[0];
			x.rtCapacity_num = roomTypes.get(id)[1];
			x.rtCount = roomTypes.get(id)[2];
			x.rtWeekday_rate = roomTypes.get(id)[3];
			x.rtWeekend_rate = roomTypes.get(id)[4];
			document.getElementById("imgLoad").style.display = "inline";
			$.post('<c:url value="/broomType/broomTypeServlet"/>', x, function() {
				alert("新增成功");
			}).fail(function() {
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
			$.post('<c:url value="/broomType/broomTypeServlet"/>', x, function(data) {
				$("#" + id).remove();
			}).fail(function() {
				alert("刪除失敗");
			}).always(function() {
				document.getElementById("imgLoad").style.display = "none";
			});
		}
		function listroomType(value, key, map) {
			var x = new Array();
			roomTypesBackup.set(key.toString(), $.extend(x, value));
			//顯示房間
			//console.log(value);
			var cell1 = $("<td></td").text(key);
			var cell2 = $("<td></td").append($("<input type='text' name='room_type'/> ").val(value[0]));
			var cell3 = $("<td></td").append($("<input type='text' name='rtCapacity_num'/>").val(value[1]));
			var cell4 = $("<td></td").append($("<input type='text' name='rtCount'/>").val(value[2]));
			var cell5 = $("<td></td").append($("<input type='text' name='rtWeekday_rate'/>").val(value[3]));
			var cell6 = $("<td></td").append($("<input type='text' name='rtWeekend_rate'/>").val(value[4]));
			// 			<th>重設鈕</th>
			var cell7 = $("<td></td").html('<a href="#" class="btn btn-danger" name="res">reset</a>');
			// 			<th>刪除鈕</th>
			var cell8 = $("<td></td").html('<a href="#" class="btn btn-danger" name="delOne">delete</a>');
			// 			<th>送出鈕</th>
			var cell9 = $("<td></td").html('<a href="#" class="btn btn-danger" name="subOne">submit</a>');
			//圖片
			var cell10 = $("<td></td");
			value[5].forEach(function(value, index, array) {
				var x = document.createElement("img");
				x.src = '<c:url value="/img/roomPics/"/>' + 'small' + value;
				var a = document.createElement("a");
				a.href = '<c:url value="/img/roomPics/"/>' + value;
				a.target = '_new'
				a.appendChild(x);
				console.log(x);
				cell10.append(a);
			});

			var tr = $("<tr id="+key+" name='roomType'></tr").append(
					[ cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9, cell10 ]);
			$("#tb").append(tr);
		}

		//============預定把綁定區函數丟到這============
		function valueChange(x, i) {
			var id = $(x).parents("tr").attr("id");
			console.log(id);
			roomTypes.get(id)[i] = $(x).val();
			console.log(roomTypes.get(id));
		}
		function res() {
			console.log(this);
			var id = $(this).parents("tr").attr("id");
			$("#" + eval(id) + " input:radio[value=" + roomTypesBackup.get(id)[0] + "]").prop("checked", true);
			$("#" + eval(id) + " input:text[name=room_type]").val(roomTypesBackup.get(id)[0]);
			$("#" + eval(id) + " input:text[name=rtCapacity_num]").val(roomTypesBackup.get(id)[1]);
			$("#" + eval(id) + " input:text[name=rtCount]").val(roomTypesBackup.get(id)[2]);
			$("#" + eval(id) + " input:text[name=rtWeekday_rate]").val(roomTypesBackup.get(id)[3]);
			$("#" + eval(id) + " input:text[name=rtWeekend_rate]").val(roomTypesBackup.get(id)[4]);
		}
	</script>

</body>
</html>