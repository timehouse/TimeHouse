package _60_border.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONValue;

import _09_order.model.OrderService;
import _09_order.model.OrderVO;

@WebServlet(urlPatterns = { "/server/BAllCalendarServlet.controller" })
public class BAllCalendarServlet extends HttpServlet {
	private OrderService orderService;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		orderService = new OrderService();
		PrintWriter out = response.getWriter();
		//之後要根據DEMO資料改
		String a = "Single";
		String b = "Double";
		String c = "Twin";
		String d = "Triple";
		String e = "Quad";
		
		String type = null;
		
		List<OrderVO> result = orderService.selectAll();
		 
		 List  list = new LinkedList();
		 
		 for(int i=0;i<result.size();i++){	
			 Map map = new LinkedHashMap();
			 map.put("id", result.get(i).getOrder_id());
			 map.put("member_account", result.get(i).getMember_account().getMember_account());
			 map.put("title", result.get(i).getGuest_id().getGuest_first_name()+" "
			 + result.get(i).getGuest_id().getGuest_last_name()+" "
			 + result.get(i).getRoom_id().getRoom_id() +"號房 "
			 + result.get(i).getRoom_type()
					 );
//			 map.put("guest_last_name", result.get(i).getGuest_id().getGuest_last_name());
//			 map.put("room_type", result.get(i).getRoom_type());
//			 map.put("room_id", result.get(i).getRoom_id().getRoom_id());
			 
			 SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
			 String start = sdFormat.format(result.get(i).getCheckin_date());
			 map.put("start", start);
			 java.util.Date todate = result.get(i).getCheckout_date();
			 Calendar gc = Calendar.getInstance();
		     gc.setTime(todate);
		     gc.add(Calendar.DATE, 1 );
		     todate = gc.getTime();
		     String end = sdFormat.format(todate);
		     map.put("end", end);
//			 map.put("end", "\""+result.get(i).getCheckout_date()+"+08:00\"");
		     
		     //根據房型不同，事件顏色不同
		     type = result.get(i).getRoom_type().trim();
		     System.out.println("a "+type.equals(a));
		     if(type.equals(a)){
		    	 map.put("color", "gold");
		    	 map.put("textColor", "black");
		     }else if(type.equals(b)){
		    	 map.put("color", "aquamarine");
			     map.put("textColor", "black");
		     }else if(type.equals(c)){
		    	 map.put("color", "aliceblue");
			     map.put("textColor", "black");
		     }else if(type.equals(d)){
		    	 map.put("color", "thistle");
			     map.put("textColor", "black");
		     }else{
		    	 map.put("color", "mistyrose");
			     map.put("textColor", "black");
		     }
		     
		     
		     
		     //以下put不會顯示在網頁上
			 map.put("adults", result.get(i).getAdults());
			 map.put("children", result.get(i).getChildren());
			 map.put("note", result.get(i).getNote());
			 map.put("transaction_condition", result.get(i).getTransaction_condition());
			 list.add(map);
			 			 
		 }
		 System.out.println(list);

		 String jsonString = JSONValue.toJSONString(list);
	        response.getWriter().print(jsonString);  
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}


}
