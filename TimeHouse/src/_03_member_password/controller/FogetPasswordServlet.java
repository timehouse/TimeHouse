package _03_member_password.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _06_member.model.MemberService;
import _06_member.model.MemberVO;

public class FogetPasswordServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberService service = new MemberService();
		Map<String,String> error = new HashMap<>();
		request.setAttribute("error", error);
		
		String email = request.getParameter("email");
		String account = request.getParameter("account");
		
		if(account==null || account.length()==0){
			error.put("account", "請輸入帳號");
		}
		
		if(email==null || email.length()==0){
			error.put("email", "請輸入電子信箱讓我們傳送一組亂碼給您當臨時密碼");
		}
		
		if(error!=null && !error.isEmpty()){
			request.getRequestDispatcher("/01_Client/_03_MemberPassword/FogetPassword.jsp").forward(request, response);;
			return;
		}
	
		int i = 0;
		
		i = (int)(Math.random()*10000)+1;
		String newPassword = "AW"+String.valueOf(i);

		MemberVO bean = new MemberVO();
		bean.setMember_account(account);
		bean.setPassword(newPassword);
		
		boolean flag = service.update(bean);
		
		System.out.println(i);
		
		if(flag==false){
			request.getRequestDispatcher("/01_Client/_03_MemberPassword/FogetPassword.jsp").forward(request, response);
			return;
		}else if(flag==true){
			Email test=new Email();
			
			boolean type =test.sendEmail("eeit83.timehouse@gmail.com", "Time House重設密碼","我們已經將密碼設為以下亂數:AW"
					+ String.valueOf(i));
			
	     	if(type){
		    	System.out.println("傳送成功");
		    }else{
		    	System.out.println("傳送失敗");
		    }

	     	String path = request.getContextPath();
	     	response.sendRedirect(path + "/01_Client/_03_MemberPassword/GetPassword.jsp");
			return;

		}
	}

	public int random(){
		 Random ran = new Random();
	     int i = ran.nextInt(42)+1;
	     return i;
	}
	
}
