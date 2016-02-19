package _60_border.controller;

import java.awt.Button;
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

import _05_guest.model.GuestDAO;
import _05_guest.model.GuestVO;
import _05_guest.model.Guest_InterfaceDAO;
import _06_member.model.MemberVO;
import _09_order.model.OrderService;
import _09_order.model.OrderVO;
import hibernate.util.HibernateUtil;

@WebServlet(urlPatterns = { "/server/BDeleteOrderServlet.controller" })
public class BDeleteChangeOrderServlet extends HttpServlet {
private OrderService orderService;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		orderService = new OrderService();
		request.setCharacterEncoding("UTF-8");
		
		
		//接收資料
		
		String delete = request.getParameter("delete");
		String temp = request.getParameter("id");
		String select = request.getParameter("select");
		String search = request.getParameter("search");
		System.out.println(search);
		
		
		Map<String, String> error = new HashMap<String, String>();
		request.setAttribute("error", error);
		
		//驗證資料			
		//轉換資料
		int id=0;
		if(temp!=null && temp.length()!=0){
			id = Integer.parseInt(temp);
		}

		
		if(error!=null && !error.isEmpty()){
			request.getRequestDispatcher("/02_Server/_60_Order/BAllOrder.jsp").forward(request, response);
			return;
		}
		//呼叫model //根據model執行結果顯示view
		OrderVO bean = new OrderVO();
		bean.setOrder_id(id);
		if(delete.equals("delete")){
			System.out.println("I GOT U");
			orderService.deleteOrder(bean);
			request.getRequestDispatcher("/server/BAllOrderServlet.controller").forward(request, response);
		}
		if(delete.equals("change")){
			bean = orderService.selectId(bean);
			int tc = bean.getTransaction_condition();
			if(tc==1){
				bean.setTransaction_condition(2);
			}else{
				bean.setTransaction_condition(1);
			}			
			orderService.updateOrder(bean);
			request.getRequestDispatcher("/server/BAllOrderServlet.controller").forward(request, response);
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}


}
