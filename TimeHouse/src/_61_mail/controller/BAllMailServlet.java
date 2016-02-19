package _61_mail.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import _07_message.model.MailHotelService;
import _07_message.model.MessageDAO;
import _07_message.model.MessageVO;
import _07_message.model.Message_InterfaceDAO;
import hibernate.util.HibernateUtil;

@WebServlet(urlPatterns = { "/server/BAllMailServlet.controller" })
public class BAllMailServlet extends HttpServlet {
	private MailHotelService mailHotelService;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		mailHotelService = new MailHotelService();
		//一點進去即看到全部列表，所以無接收、驗證、轉換
		//呼叫model
		MessageVO bean = null;
		//根據model執行結果顯示view
		List<MessageVO> result = mailHotelService.select(bean);
		request.setAttribute("select", result);
		System.out.println("ser  "+result);
		request.getRequestDispatcher(
				"/02_Server/_61_Mail/BAllMailHotel.jsp").forward(request, response);
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}


}
