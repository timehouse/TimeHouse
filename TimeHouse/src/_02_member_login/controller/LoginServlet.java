package _02_member_login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _06_member.model.MemberService;
import _06_member.model.MemberVO;

@WebServlet(urlPatterns={"/member/login.controller"})
public class LoginServlet extends HttpServlet{
	MemberService service = new MemberService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String account = request.getParameter("account");
		String password= request.getParameter("password");
		
		Map<String,String> error = new HashMap<>();
		request.setAttribute("error", error);
		
		if (session == null) {
			// 請使用者登入
			response.sendRedirect(response.encodeRedirectURL("/01_Client/_02_MemberLogin/login.jsp"));
			return;
		}
		
		if(account==null || account.trim().length()==0){
			error.put("account", "Please Insert Account");
		}
		if(password==null || account.trim().length()==0){
			error.put("password", "Please Insert Password");
		}
		
		if(error!=null && !error.isEmpty()){
			request.getRequestDispatcher("/01_Client/_02_MemberLogin/login.jsp").forward(request, response);
			return;
		}
		
		String bean = service.login(account, password);		
		
		if(bean == "lock"){
			error.put("notExist", "您的帳號已被封鎖，請洽客服人員");
			request.getRequestDispatcher("/01_Client/_02_MemberLogin/login.jsp").forward(request, response);
		}else if(bean == null){
			error.put("notExist", "您的帳號不存在");
			request.getRequestDispatcher("/01_Client/_02_MemberLogin/login.jsp").forward(request, response);
		}else if(bean == "access"){
			session.setAttribute("LoginOK", bean);
			session.setAttribute("account", account);
		
			MemberVO member = service.sendFile(account);
			
			session.setAttribute("memberAccount",member.getMember_account() );
			session.setAttribute("password", member.getPassword());
			session.setAttribute("lastName", member.getMember_last_name());
			session.setAttribute("firstName", member.getMember_first_name());
			session.setAttribute("gender", member.getGender());
			session.setAttribute("idNumber", member.getId_number());
			session.setAttribute("creditCard", member.getCredit_card());
			session.setAttribute("birth", member.getBirthday());
			session.setAttribute("tel", member.getTel());
			session.setAttribute("phone", member.getPhone_number());
			session.setAttribute("address", member.getAddress());
			session.setAttribute("email", member.getEmail());
			session.setAttribute("regDate", member.getReg_date());
			
			String dest = (String) session.getAttribute("dest");
			if(dest!=null && dest.length()!=0) {
				session.removeAttribute("dest");
				response.sendRedirect(dest);
			} else {
				String path = request.getContextPath();
				response.sendRedirect(path+"/01_Client/index.jsp");
			}
		}

	}
	
}
