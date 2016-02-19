// JavaScript Document

$(function(){
	
	$("#goTop").click(function(){

		$("html,body").animate({scrollTop:0},600);

		//$("html,body").animate({scrollTop:0},900,"easeOutBounce");

		return false;

	});

});