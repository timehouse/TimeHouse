package _61_mail.controller;

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

import _06_member.model.MemberService;
import _06_member.model.MemberVO;
import _07_message.model.MailHotelService;
import _07_message.model.MessageDAO;
import _07_message.model.MessageVO;
import _07_message.model.Message_InterfaceDAO;
import hibernate.util.HibernateUtil;

@WebServlet(urlPatterns = { "/server/BLimitMemberMailServlet.controller" })
public class BLimitMemberMailServlet extends HttpServlet {
	private MailHotelService mailHotelService;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		mailHotelService = new MailHotelService();
		//接收資料
		String member = request.getParameter("member");
		System.out.println("mb="+member);
		Map<String, String> error = new HashMap<String, String>();
		request.setAttribute("error", error);
		//轉換資料
		//驗證資料
		
		if(error!=null && !error.isEmpty()){
			request.getRequestDispatcher("/server/BAllMailServlet.controller").forward(request, response);
			return;
		}
		//呼叫model

		MemberVO memberVO = new MemberVO();
		memberVO.setMember_account(member);
		MemberService mbService = new MemberService();
		mbService.limit(memberVO);
		
		
		//根據model執行結果顯示view	
		request.getRequestDispatcher(
				"/server/BAllMailServlet.controller").forward(request, response);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}


}
