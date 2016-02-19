<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<textarea cols="45" rows="10" id="messagesTextArea" readonly="readonly"></textarea><br/>
	<input type="text" id="messageText" size="50">
	<input type="button" value="送出" onClick="sendMessage()">
	<script type="text/javascript">
	
	    var host = window.location.host;
	    var path = window.location.pathname;
	    var webCtx = path.substring(0, path.indexOf('/', 1));
	    
		var websocket = new WebSocket("ws://"+ host + webCtx + "/chatXX")
		
		websocket.onmessage = function processMessage(message){
			var jsonData = JSON.parse(message.data);
			if(jsonData.message!=null){
				messagesTextArea.value += jsonData.message + "\n";
			}
		}
		function sendMessage(){
			websocket.send(messageText.value);
			messageText.value="";
			
		}
	</script>
</body>
</html>