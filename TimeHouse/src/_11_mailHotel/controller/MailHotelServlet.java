package _11_mailHotel.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _06_member.model.MemberVO;
import _07_message.model.MailHotelService;
import _07_message.model.MessageVO;

@WebServlet(urlPatterns = { "/client/mailHotelServlet.controller" })
public class MailHotelServlet extends HttpServlet {
	private MailHotelService mailHotelService;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		mailHotelService = new MailHotelService();
		request.setCharacterEncoding("UTF-8");

		// 接收資料
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String content = request.getParameter("content");
		String member = request.getParameter("member");
		System.out.println("mb  "+member);
		
		Map<String, String> error = new HashMap<String, String>();
		request.setAttribute("error", error);
		// 轉換資料
		// 驗證資料
		if (type.equals("請選擇分類")) {
			error.put("type", "請選擇分類");
		}
		if (name.trim().isEmpty()) {
			error.put("name", "不可為空");
		}
		if (email.trim().isEmpty()) {
			error.put("email", "不可為空");
		}
		if (content.trim().isEmpty()) {
			error.put("content", "不可為空");
		}

		if (error != null && !error.isEmpty()) {
			request.getRequestDispatcher("/01_Client/_11_MailHotel/MailHotel.jsp").forward(request, response);
			return;
		}
		// 呼叫model
		MessageVO bean = new MessageVO();
		MemberVO mb = new MemberVO();
		MessageVO result = new MessageVO();
		if(!member.equals("none")){
			mb.setMember_account(member);
			bean.setMember_account(mb);
		}
		bean.setType(type);
		bean.setName(name);
		bean.setEmail(email);
		bean.setMessage_content(content);
		bean.setMessage_date(new java.util.Date());
		bean = mailHotelService.insert(bean);

		// 根據model執行結果顯示view
		if (bean != null) { // 成功insert則返回上一頁(不確定是否成功)
//			request.getRequestDispatcher(request.getHeader("referer")).forward(request, response);
//			request.getRequestDispatcher("/01_Client/index.jsp").forward(request, response);
			request.getRequestDispatcher("/01_Client/_11_MailHotel/SuccessMail.jsp").forward(request, response);
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

}
