package _60_border.controller;

import java.io.IOException;
import java.util.HashMap;
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
import _09_order.model.OrderService;
import _09_order.model.OrderVO;
import hibernate.util.HibernateUtil;

@WebServlet(urlPatterns = { "/server/BUpadteOrderServlet.controller" })
public class BUpadteOrderServlet extends HttpServlet {
private OrderService orderService;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		orderService = new OrderService();
		request.setCharacterEncoding("UTF-8");
		
		
		//接收資料
		String temp = request.getParameter("id");
		String guest = request.getParameter("guest");
		String temp2 = request.getParameter("adults");
		String temp3 = request.getParameter("children");
		String note = request.getParameter("note");
		String temp4 = request.getParameter("transaction_condition");
		String temp5 = request.getParameter("guestid");
		
		Map<String, String> error = new HashMap<String, String>();
		request.setAttribute("error", error);
		
		//驗證資料
		if(temp2.isEmpty()){
			error.put("adults","未輸入成人人數");
		}
		if(temp3.isEmpty()){
			error.put("children","未輸入兒童人數");
		}
		if(temp4.trim().isEmpty()){
			error.put("transaction_condition","未填寫交易狀況");
		}
		
		//轉換資料
		int id=0;
		if(temp!=null && temp.length()!=0){
			id = Integer.parseInt(temp);
		}
		
		int adults=0;
		if(temp2!=null && temp2.length()!=0){
			adults = Integer.parseInt(temp2);
		}
		
		int children=0;
		if(temp3!=null && temp3.length()!=0){
			children = Integer.parseInt(temp3);
		}
		
		int tc=0;
		if(temp4!=null && temp4.length()!=0){
			tc = Integer.parseInt(temp4);
		}
		
		int gusetId=0;
		if(temp5!=null && temp5.length()!=0){
			gusetId = Integer.parseInt(temp5);
		}
		
		if(error!=null && !error.isEmpty()){
			request.getRequestDispatcher("/02_Server/_60_Order/BUpdateOrder.jsp").forward(request, response);
			return;
		}
		//呼叫model
		Guest_InterfaceDAO dao = new GuestDAO();		
		OrderVO bean = new OrderVO();
		GuestVO guestVO = new GuestVO();
		guestVO.setGuest_id(gusetId);
		guestVO = dao.select(gusetId);  //直接對DAO操作，不良寫法
		String str = " ";
		if(guest.indexOf(str)<=0){  //判斷中英文姓名
			guestVO.setGuest_first_name(guest.substring(0,1));
			guestVO.setGuest_last_name(guest.substring(1));	
		}else{
			guestVO.setGuest_first_name(guest.split(" ")[0]);
			guestVO.setGuest_last_name(guest.split(" ")[1]);
		}
		dao.update(guestVO);
		bean.setOrder_id(id);
		bean = orderService.selectId(bean);
		bean.setAdults(adults);
		bean.setChildren(children);
		bean.setNote(note);
		bean.setTransaction_condition(tc);
		bean = orderService.updateOrder(bean);
		
		//根據model執行結果顯示view
		if(bean != null){
			request.getRequestDispatcher("/server/BAllOrderServlet.controller").forward(request, response);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}


}
