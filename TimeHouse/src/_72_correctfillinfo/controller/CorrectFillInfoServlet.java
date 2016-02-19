package _72_correctfillinfo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import _05_guest.model.GuestService;
import _05_guest.model.GuestVO;
import hibernate.util.HibernateUtil;

@WebServlet(
		
		urlPatterns={"/CorrectFillInfo.controller"}
)
public class CorrectFillInfoServlet extends HttpServlet {

	private GuestService guestService = new GuestService();
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
	
		//接收資料
				String temp1   = request.getParameter("guest_id");
				String guest_last_name    = request.getParameter("guest_last_name");
				String guest_first_name   = request.getParameter("guest_first_name");
				String id_number   = request.getParameter("id_number");
				String gender   = request.getParameter("gender");			
				String tel = request.getParameter("tel");
				String phone_number = request.getParameter("phone_number");
				String email = request.getParameter("email");
				String prodaction = request.getParameter("prodaction");
		//存放資料錯誤
				Map<String, String> error = new HashMap<String, String>();
				request.setAttribute("error", error);
				
		//轉換資料
				int guest_id = 0;
				if(temp1!=null && temp1.length()!=0) 
				{
					try 
					{
						guest_id = Integer.parseInt(temp1);
					} 
					catch (NumberFormatException e) 
					{
					e.printStackTrace();
					error.put("guest_id", "只能是數字");
					}
				}
					
		//驗證資料
				  if("SelectId".equals(prodaction) || "Update".equals(prodaction) || "Delete".equals(prodaction)) 
				  {
					if(guest_id==0) 
					{
					error.put("guest_id", "請輸入ID才能"+prodaction);
					}
				  }
				
				  if("Insert".equals(prodaction))
				 {
					  
				  //驗證姓
				  if(guest_last_name==null||guest_last_name.length()==0)
				  {
					error.put("guest_last_name", "請輸入最少一個字的姓氏");
				  }
				  //驗證名字
				  if(guest_first_name==null||guest_first_name.length()<2)
				  {
					error.put("guest_first_name", "請輸入最少兩個字的姓名");
				  }	  	  
				  //驗證身分證
				  String id_number_regex = "^[A-Za-z]{1}[1-2]{1}[0-9]{8}$";
				  if(id_number==null || !id_number.matches(id_number_regex)) 
				  {
						error.put("id_number", "不正確的身分證");
				  }
				  //驗證電話一定要輸入一組
				  if(phone_number==null&&tel==null||phone_number.length()==0&&tel.length()==0)
				  {
					  error.put("phone_number", "請輸入最少一組聯絡方法(手機OR市話)");
					  error.put("tel", "請輸入最少一組聯絡方法(手機OR市話)");
				  }
				 }
				

				if(error!=null && !error.isEmpty())
				{
				
					request.getRequestDispatcher(
					"/02_Server/_72_CorrectFillInfo/CorrectFillInfo.jsp").forward(request, response);
					return;
				}
				
				
			
		//呼叫model
				
				GuestVO bean = new GuestVO();
				bean.setGuest_id(guest_id);
				bean.setGuest_last_name(guest_last_name);
				bean.setGuest_first_name(guest_first_name);
				bean.setGender(gender);
				bean.setId_number(id_number);
				bean.setTel(tel);
				bean.setPhone_number(phone_number);
				bean.setEmail(email);
				
				
				
		//根據model執行結果顯示view		
				if("SelectId".equals(prodaction)){
					
					GuestVO	result= guestService.select(guest_id);		
					request.setAttribute("selectid", result);
					request.getRequestDispatcher(
								"/02_Server/_72_CorrectFillInfo/CorrectFillInfoOut.jsp").forward(request, response);
					}
					else if("Select".equals(prodaction)) {
						List<GuestVO> result = guestService.select();
						request.setAttribute("select", result);
						request.getRequestDispatcher(
								"/02_Server/_72_CorrectFillInfo/CorrectFillInfoOut.jsp").forward(request, response);
					} else if("Insert".equals(prodaction)) {
						GuestVO result = guestService.insert(bean);
						if(result==null) {
							error.put("action", "Insert failed");
						} else {
							request.setAttribute("insert", result);
						}
						request.getRequestDispatcher(													
								"/02_Server/_72_CorrectFillInfo/CorrectFillInfo.jsp").forward(request, response);
					} else if("Update".equals(prodaction)) {
						guestService.update(bean);
						
						request.getRequestDispatcher(
								"/02_Server/_72_CorrectFillInfo/CorrectFillInfo.jsp").forward(request, response);
					} else if("Delete".equals(prodaction)) {
						 guestService.delete(guest_id);
						
						request.getRequestDispatcher(
								"/02_Server/_72_CorrectFillInfo/CorrectFillInfo.jsp").forward(request, response);
					} else {
						error.put("action", "Unknown action: "+prodaction);
						request.getRequestDispatcher(
								"/02_Server/_72_CorrectFillInfo/CorrectFillInfo.jsp").forward(request, response);
					}

	}
	@Override
	protected void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
