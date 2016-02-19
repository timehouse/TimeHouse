package _01_member_register.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _03_member_password.controller.Email;
import _06_member.model.MemberService;
import _06_member.model.MemberVO;

@WebServlet(urlPatterns="/member/register.controller")
public class RegisterServlet extends HttpServlet {

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		this.doGet(request, response);						
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String,String> error = new HashMap<>();
		request.setAttribute("error", error);		
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String gender = request.getParameter("gender");
		String temp1 = request.getParameter("birth");
		String idNumber = request.getParameter("idNumber");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String tel = request.getParameter("tel");
		String creditCard = request.getParameter("creditCard");
		String addr = request.getParameter("addr");
		
		if(account==null || account.trim().length()==0){
			error.put("account", "帳號為必填");
		}
		
		if(password==null || password.trim().length()==0){
			error.put("password", "密碼為必填");
		}
		
		if(phone==null || phone.trim().length()==0){
			error.put("phone", "行動電話為必填");
		}
//		String regex = "^.*(?=.*{10})(?=.*[^A-Za-z])(?=.*[^@#$%^&+=]).*$";
//		if(phone!=null ){
//			if(!phone.matches(regex)){
//				error.put("phone", "請輸入有效的手機號碼");	
//			}
//		}
		
		if(email==null || email.trim().length()==0){
			error.put("email", "E-Mail為必填");
		}
		
		java.util.Date birth = null;
		if(temp1!=null && temp1.trim().length()!=0){
			try {
				birth = sdf.parse(temp1);
			} catch (ParseException e) {
				error.put("birth", "生日格式為:yyyy-MM-dd");
			}
		}
		
		if(error!=null && !error.isEmpty()){
			request.getRequestDispatcher("/01_Client/_01_MemberRegister/register.jsp").forward(request, response);
			return;
		}
		
		MemberService service = new MemberService();		

		MemberVO input = new MemberVO();
		input.setMember_account(account);
		input.setPassword(password);
		input.setMember_last_name(lastName);
		input.setMember_first_name(firstName);
		input.setGender(gender);
		input.setId_number(idNumber);
		input.setEmail(email);
		input.setBirthday(birth);
		input.setPhone_number(phone);
		input.setTel(tel);
		input.setCredit_card(creditCard);
		input.setAddress(addr);
		
		MemberVO bean = service.register(input);		
		if(bean==null){
			request.getRequestDispatcher("/01_Client/_01_MemberRegister/register.jsp").forward(request, response);
			return;
		}else if(bean!=null){
	    	Email test=new Email();
	    	
			boolean type =test.sendEmail("eeit83.timehouse@gmail.com", "Time House，Email認證~~","請點選網址回到網站首頁已完成認證:"
					+ "http://localhost:8080/ProjectNew/01_Client/index.jsp");
			
	     	if(type){
		    	System.out.println("傳送成功");
		    }else{
		    	System.out.println("傳送失敗");
		    }

			String path = request.getContextPath();
			response.sendRedirect(path+"/01_Client/_01_MemberRegister/CheckEmail.jsp");
		}
	}
	
}
