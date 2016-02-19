package _09_orderList.controller;

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
import _09_order.model.OrderService;
import _09_order.model.OrderVO;
import hibernate.util.HibernateUtil;

@WebServlet(urlPatterns = { "/client/MemberOrderServlet.controller" })
public class MemberOrderServlet extends HttpServlet {
	private OrderService orderService;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		orderService = new OrderService();

		// 接收資料
		MemberVO memberVO = new MemberVO(); // 之後要改成從session中獲得會員帳號名
		memberVO.setMember_account("da");

		// 轉換資料
		// 驗證資料
		// 呼叫model
		OrderVO bean = new OrderVO();
		bean.setMember_account(memberVO);

		List<OrderVO> result = orderService.selectMember(bean);
		System.out.println(result);

		request.setAttribute("select", result);

		// 根據model執行結果顯示view
		request.getRequestDispatcher("/01_Client/_09_OrderList/MemberOrder.jsp").forward(request, response);
	}

	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

}
