package _60_border.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import _09_order.model.OrderDAO;
import _09_order.model.OrderService;
import _09_order.model.OrderVO;
import _09_order.model.Order_InterfaceDAO;
import hibernate.util.HibernateUtil;

@WebServlet(urlPatterns = { "/server/BAllOrderServlet.controller" })
public class BAllOrderServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OrderService orderService = new OrderService();
		//一點進去即看到全部列表，所以無接收、驗證、轉換
		//呼叫model
		OrderVO bean = null;
		//根據model執行結果顯示view 
		List<OrderVO> result = orderService.selectAll();
		System.out.println(result);
		request.setAttribute("select", result);
		request.getRequestDispatcher(
				"/02_Server/_60_Order/BAllOrder.jsp").forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}


}
