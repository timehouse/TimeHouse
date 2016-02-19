package _60_border.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import _06_member.model.MemberVO;
import _09_order.model.OrderService;
import _09_order.model.OrderVO;
import hibernate.util.HibernateUtil;

@WebServlet(urlPatterns = { "/server/BSeletctOrderServlet.controller" })
public class BSeletctOrderServlet extends HttpServlet {
private OrderService orderService;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		orderService = new OrderService();
		
		//接收資料
		
		String select = request.getParameter("select");
		String search = request.getParameter("search");
		System.out.println("select "+select);
		System.out.println("search"+search);
		
		
		Map<String, String> error = new HashMap<String, String>();
		request.setAttribute("error", error);
		
		//驗證資料			
		//轉換資料
		
		if(error!=null && !error.isEmpty()){
			request.getRequestDispatcher("/02_Server/_60_Order/BAllOrder.jsp").forward(request, response);
			return;
		}
		//呼叫model //根據model執行結果顯示view
		OrderVO bean = new OrderVO();

		
		List<OrderVO> result = null;
		if(select.equals("member_account")){
			MemberVO mVO =new MemberVO();
			mVO.setMember_account(search);
			bean.setMember_account(mVO);
			result = orderService.selectMember(bean);
		}
		if(select.equals("checkin_date")){
			result = orderService.selectCheckin(java.sql.Date.valueOf(search.split(" ")[0]));
		}
		if(select.equals("checkout_date")){
			result = orderService.selectCheckout(java.sql.Date.valueOf(search.split(" ")[0]));
		}
		
		if(result.get(0)!=null){
		request.setAttribute("select", result);
		request.getRequestDispatcher(
				"/02_Server/_60_Order/BAllOrder.jsp").forward(request, response);
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}


}


