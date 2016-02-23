<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../css/justified-nav.css" />
<link rel="stylesheet" href="../../css/ordercalendar/fullcalendar.min.css" />
<link rel="stylesheet" href="../../css/ordercalendar/fullcalendar.print.css" media='print'/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>

	body {
		margin: 40px 10px;
		padding: 0;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		font-size: 14px;
	}

	#calendar {
		max-width: 900px;
		margin: 0 auto;
	}

</style>
<title>以行事曆顯示訂單</title>
</head>
<body>

	<button type="button"  class="btn btn-default" onClick="listOrder()">
    	<span class="glyphicon glyphicon-list"></span>
    </button>
    
	<div id='calendar'></div>
	
<script type="text/javascript" src="../../js/moment.min.js"></script>
<script type="text/javascript" src="../../js/jquery-2.2.0.min.js"></script>
<script type="text/javascript" src="../../js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="../../js/fullcalendar.min.js"></script>
<script>

	$(document).ready(function() {

		$('#calendar').fullCalendar({
// 			defaultDate: '2016-01-12',
			editable: true,
			eventLimit: true, 
			
			eventSources: [
	                {
	                    url: '<c:url value="/server/BAllCalendarServlet.controller"/>',
	                    type: 'GET',
	                    currentTimezone: 'Asia/Taipei',
	                    data: {

	                    },
	                    error: function() {
	                        alert('there was an error while fetching events!');
	                    },
// 	                    color: 'yellow',   // a non-ajax option
// 	                    textColor: 'black' // a non-ajax option
	                }


	            ],
// 	            eventClick:function(){
// 	                //有空再弄彈跳視窗
// 	            }
		});
	 });
	  
	function listOrder(){
		location.href="<c:url value="/server/BAllOrderServlet.controller"/>";
	}

</script>
</body>
</html>