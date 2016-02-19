package _11_mailHotel.controller;

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

@WebServlet(urlPatterns = { "/client/MemberMailServlet.controller" })
public class MemberMailServlet extends HttpServlet {
	private MailHotelService mailHotelService;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		mailHotelService = new MailHotelService();
		//接收資料
		MemberVO memberVO = new MemberVO();  //之後要改成從session中獲得會員帳號名
		memberVO.setMember_account("da");
		
		//轉換資料
		//驗證資料
		//呼叫model
		MessageVO bean = new MessageVO();
		bean.setMember_account(memberVO);
		
		
		Message_InterfaceDAO dao = new MessageDAO();
		List<MessageVO> result = mailHotelService.selectMember(bean);

		
		request.setAttribute("select", result);
		
		//根據model執行結果顯示view
		request.getRequestDispatcher(
				"/01_Client/_11_MailHotel/MemberMailHotel.jsp").forward(request, response);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}


}
