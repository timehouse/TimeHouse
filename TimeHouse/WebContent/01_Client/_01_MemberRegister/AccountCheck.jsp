<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="_06_member.model.*" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="hibernate.util.HibernateUtil" %>
<% 
	String account = request.getParameter("account");
	String password = request.getParameter("password");
	
	MemberDAO dao = new MemberDAO();
	MemberVO bean = dao.findByPrimaryKey(account);
	
	String msg = "此帳號不存在";
	
	if(bean!=null){
		msg=("此帳號已存在");
	}

	out.print(msg);
%>