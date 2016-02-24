<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聊天室</title>
</head>
<body>
	<%@ include file="../header.jsp"%>
	<div style="height:110px"></div>
	這其實是個多人聊天室
	<textarea cols="45" rows="10" id="messagesTextArea" readonly="readonly"></textarea><br/>
	<input type="hidden" id="userName" size="10" value="訪客">
	<input type="text" id="messageText" size="50" onkeydown="enter()">
	<input type="button" value="送出" onClick="sendMessage()">
	<script type="text/javascript">
	
	    var host = window.location.host;
	    var path = window.location.pathname;
	    var webCtx = path.substring(0, path.indexOf('/', 1));
	    
		var websocket = new WebSocket("ws://"+ host + webCtx + "/chat")
		
// 		var userName =  document.getElementById("userName");
		
		websocket.onmessage = function processMessage(message){
			var jsonData = JSON.parse(message.data);
			if(jsonData.message!=null){
				messagesTextArea.value += jsonData.message + "\n";
			}
		}
		
		function sendMessage(){
			if(messageText.value!=""){
				websocket.send(userName.value+":"+messageText.value);
				messageText.value="";
				}
			
		}
// 		-----------判斷是否按下Enter-----------
		function enter(){
			if (event.keyCode == 13) {   // 13 為 enter 的鍵盤碼
				sendMessage()
		     }
		}
	</script>
	<%@ include file="../footer.jsp"%>
</body>
</html>