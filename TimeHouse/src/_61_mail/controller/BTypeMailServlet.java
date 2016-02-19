package _61_mail.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import _06_member.model.MemberVO;
import _07_message.model.MailHotelService;
import _07_message.model.MessageDAO;
import _07_message.model.MessageVO;
import _07_message.model.Message_InterfaceDAO;
import hibernate.util.HibernateUtil;

@WebServlet(urlPatterns = { "/server/BTypeMailServlet.controller" })
public class BTypeMailServlet extends HttpServlet {
	private MailHotelService mailHotelService;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		mailHotelService = new MailHotelService();
		//接收資料
		String type = request.getParameter("type");
		
		//轉換資料
		//驗證資料
		//呼叫model

		List<MessageVO> result =null;
		if(!type.equals("全部類型")){
		result = mailHotelService.selectType(type);
		System.out.println(result);		
		}
		request.setAttribute("select", result);
		
		
		
		//根據model執行結果顯示view	
		request.getRequestDispatcher(
				"/02_Server/_61_Mail/BAllMailHotel.jsp").forward(request, response);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}


}
