package _61_mail.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

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

@WebServlet(urlPatterns = { "/server/BMailServlet.controller" })
public class BMailServlet extends HttpServlet {
	private MailHotelService mailHotelService;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		mailHotelService = new MailHotelService();
		request.setCharacterEncoding("UTF-8");
		
		//接收資料
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String content = request.getParameter("content");
		String temp = request.getParameter("id");
		String reply = request.getParameter("reply");
		
		Map<String, String> error = new HashMap<String, String>();
		request.setAttribute("error", error);
		//轉換資料
		int id=0;
		if(temp!=null && temp.length()!=0){
			id = Integer.parseInt(temp);
		}
		
		//驗證資料
		if(reply.trim().isEmpty()){
			error.put("reply","尚未回應");
		}
		
		if(error!=null && !error.isEmpty()){
			request.getRequestDispatcher("/02_Server/_61_Mail/ReplyMail.jsp").forward(request, response);
			return;
		}
		//呼叫model
		Message_InterfaceDAO dao = new MessageDAO();
		
		MessageVO bean = new MessageVO();
		bean.setMessage_id(id);
		System.out.println(mailHotelService.select(bean));
		bean = mailHotelService.selectId(bean);
		bean.setReply(reply);
		bean.setReply_date(new java.util.Date());
		mailHotelService.reply(bean);
		//根據model執行結果顯示view
		if(bean != null){
			request.getRequestDispatcher("/server/BAllMailServlet.controller").forward(request, response);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}


}
