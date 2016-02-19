<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedHashMap"%> 
<%@page import="java.util.Map"%>  
<%@page import="java.util.HashMap"%>
<%@page import="java.util.LinkedList"%>  
<%@page import="java.util.List"%>
<%@page import="org.json.simple.JSONValue"%>
<%@ page import="java.sql.*" %>
<%@ page import="_09_order.model.OrderService" %>
<%@ page import="_09_order.model.OrderVO" %>
<%
	response.setHeader("Access-Control-Allow-Origin", "*");
	
	OrderService orderService = new OrderService();
		
	List<OrderVO> result = orderService.selectAll();
	System.out.println(result);
	
	String jsonString = JSONValue.toJSONString(result);                    
	out.println(jsonString);


%>