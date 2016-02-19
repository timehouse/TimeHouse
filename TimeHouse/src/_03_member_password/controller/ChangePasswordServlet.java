package _03_member_password.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _06_member.model.MemberService;

public class ChangePasswordServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	MemberService service = new MemberService();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		
		if (session == null) {
			// 請使用者登入
			response.sendRedirect(response.encodeRedirectURL("/01_Client/_02_MemberLogin/login.jsp"));
			return;
		}
		
		Map<String,String> error = new HashMap<>();
		request.setAttribute("error", error);
		
		if(oldPassword==null || oldPassword.trim().length()==0){
			error.put("oldPassword", "請輸入舊密碼");
		}
		if(newPassword==null || newPassword.trim().length()==0){
			error.put("newPassword", "請輸入新密碼");
		}
		if(confirmPassword==null || confirmPassword.trim().length()==0){
			error.put("confirmPassword", "請輸入確認密碼");
		}
		
		String account = (String) session.getAttribute("account");
		
		if(service.login(account, oldPassword)==null){
			error.put("notFound", "密碼輸入錯誤");
		}
		
		if(error!=null && !error.isEmpty()){
			request.getRequestDispatcher("/01_Client/_03_MemberPassword/ChangePassword.jsp").forward(request, response);;
			return;
		}
		boolean flag = service.changePassword(account, oldPassword, newPassword,confirmPassword);
		
		if(flag==false){
			error.put("wrong", "新密碼與確認密碼不相同");
			request.getRequestDispatcher("/01_Client/_03_MemberPassword/ChangePassword.jsp").forward(request, response);
		}else if(flag==true){
			String path = request.getContextPath();
			response.sendRedirect(path+"/01_Client/_03_MemberPassword/ChangeSuccess.jsp");
		}
		
	}

}
