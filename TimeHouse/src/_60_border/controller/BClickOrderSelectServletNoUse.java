package _60_border.controller;

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

import _06_member.model.MemberVO;
import _09_order.model.OrderService;
import _09_order.model.OrderVO;
import hibernate.util.HibernateUtil;

@WebServlet(urlPatterns = { "/server/BClickOrderSelectServlet.controller" })
public class BClickOrderSelectServletNoUse extends HttpServlet {
private OrderService orderService;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		orderService = new OrderService();
		request.setCharacterEncoding("UTF-8");
		
		
		//接收資料
		
//		String click = request.getParameter("click");
		String member_account = request.getParameter("member_account");
		System.out.println("aaaa "+member_account);
		
		
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
//		if(click.equals("member_account")){
			MemberVO mVO =new MemberVO();
			mVO.setMember_account(member_account);
			bean.setMember_account(mVO);
			result = orderService.selectMember(bean);
			System.out.println(result);
//		}
		
		request.setAttribute("select", result);
		request.getRequestDispatcher(
				"/02_Server/_60_Order/BAllOrder.jsp").forward(request, response);

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}


}


