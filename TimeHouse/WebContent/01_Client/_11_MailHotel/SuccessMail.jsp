<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/TimeHouse/css/mail/demo.css" />
<link rel="stylesheet" href="/TimeHouse/css/mail/pfold.css" />
<link rel="stylesheet" href="/TimeHouse/css/mail/custom1.css" />
<title>成功送出</title>
<style>
	.textdiv{
		text-align:center;
	}
</style>
</head>
<body onload="click()">
	<%@ include file="../header.jsp"%>
	<div style="height:110px"></div>
			<section class="main demo-1">
				<div id="uc-container" class="uc-container">
					<div class="uc-initial-content">
						<!-- custom content -->
						<span class="clickme"></span>
					</div>
					<div class="uc-final-content">
						<!-- custom content -->
						<div class="scrollwrap">
							<h3>親愛的 ${param.name},</h3>
							<p>訊息已成功送出，本飯店將會盡快回覆您。</p><p>THANKS for that!</p><p class="signature">Yours, TimeHouse</p>
						</div>
						<span class="close">x</span>
					</div>
				</div>
			</section>
			
	<script type="text/javascript" src="/TimeHouse/js/jquery-2.2.0.min.js"></script>
	<script type="text/javascript" src="/TimeHouse/js/mail/modernizr.custom.79639.js"></script>
	<script type="text/javascript" src="/TimeHouse/js/mail/jquery.pfold.js"></script>
	<script>
	function click() {
		$('span').click();
	}
	
	
	$(function() {

		var $container = $( '#uc-container' ),
			pfold = $( '#uc-container' ).pfold({
				easing : 'ease-in-out',
				folds : 3,
				folddirection : ['left','bottom','right']
			});

		$container.find( 'span.clickme' ).on( 'click', function() {

			pfold.unfold();

		} ).end().find( 'span.close' ).on( 'click', function() {

			pfold.fold();

		} );
		
	});
	</script>
	<%@ include file="../footer.jsp"%>
</body>
</html>