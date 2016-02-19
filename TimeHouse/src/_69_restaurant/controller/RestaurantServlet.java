package _69_restaurant.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _69_restaurant.model.RestaurantService;
import _69_restaurant.model.RestaurantVO;

@WebServlet("/Restaurant.controller")
public class RestaurantServlet extends HttpServlet {
	
	private RestaurantService restaurantService = new RestaurantService();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 設定編碼

		// 接收資料
		String rest_id = request.getParameter("rest_id");
		String rest_name = request.getParameter("rest_name");
		String rest_time = request.getParameter("rest_time");
		String rest_address = request.getParameter("rest_address");
		String rest_context = request.getParameter("rest_context");
		String rest_pic = request.getParameter("rest_pic");
		String prodaction = request.getParameter("prodaction");

		// 存放資料錯誤
		Map<String, String> error = new HashMap<String, String>();
		request.setAttribute("error", error);

		// 轉換資料
		// rest_id
		int nrest_id = 0;
		if (rest_id != null && rest_id.length() != 0) {
			try {
				nrest_id = Integer.parseInt(rest_id);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				error.put("rest_id", "餐廳編號只能是數字");
			}
		}
		// rest_name
		if (rest_name == null && rest_name.length() == 0) {
			error.put("rest_name", "餐廳名稱須輸入");
		}
		// rest_time
		if (rest_time == null && rest_time.length() == 0) {
			error.put("rest_time", "餐廳開放時間須輸入");
		}
		// rest_address
		if (rest_address == null && rest_address.length() == 0) {
			error.put("rest_address", "餐廳位置須輸入");
		}
		// rest_context
		if (rest_context == null && rest_context.length() == 0) {
			error.put("rest_context", "餐廳內容須輸入");
		}
		// rest_pic//只存照片名稱
		byte[] nrest_pic = null;
		if (rest_pic != null && rest_pic.length() != 0) {
			nrest_pic = rest_pic.getBytes();
		} else {
			// error.put("rest_pic", "必須選擇照片");
		}
		// 驗證資料
		if ("Insert".equals(prodaction) || "Update".equals(prodaction)) {
			if ("Update".equals(prodaction)) {//更新需要id(Key)
				if (nrest_id == 0) {
					error.put("rest_id", "請輸入ID才能" + prodaction);
				}
			}else{
				if (rest_pic.length() == 0 || rest_pic == null) {
					error.put("rest_pic", "請輸入照片才能" + prodaction);
				}
				if (rest_name.length() == 0 || rest_name == null) {
					error.put("rest_name", "請輸入餐廳名稱才能" + prodaction);
				}
				if (rest_time.length() == 0 || rest_time == null) {
					error.put("rest_time", "請輸入餐廳開放時間才能" + prodaction);
				}
				if (rest_address.length() == 0 || rest_address == null) {
					error.put("rest_address", "請輸入餐廳位置才能" + prodaction);
				}
				if (rest_context.length() == 0 || rest_context == null) {
					error.put("rest_context", "請輸入餐廳介紹才能" + prodaction);
				}
			}
		}
		if (error != null && !error.isEmpty()) {
			request.getRequestDispatcher("/02_Server/_68_Restaurant/Restaurant.jsp").forward(request, response);
			return;
		}
		// 呼叫model
		RestaurantVO bean=new RestaurantVO();
		bean.setRest_id(nrest_id);
		bean.setRest_name(rest_name);
		bean.setRest_time(rest_time);
		bean.setRest_address(rest_address);
		bean.setRest_context(rest_context);
		bean.setRest_pic(nrest_pic);
		
		// 根據model執行結果顯示view
		if ("Select".equals(prodaction)) {
			List<RestaurantVO> result = restaurantService.selectAll();
			request.setAttribute("select", result);
			System.out.println("00000"+result);
			request.getRequestDispatcher("/02_Server/_68_Restaurant/RestaurantOut.jsp").forward(request, response);
		} else if ("Insert".equals(prodaction)) {
			RestaurantVO result = restaurantService.insert(bean);
			if (result == null) {
				error.put("action", "Insert failed");
			} else {
				request.setAttribute("insert", result);
			}
			request.getRequestDispatcher("/02_Server/_68_Restaurant/Restaurant.jsp").forward(request, response);

		} else if ("Update".equals(prodaction)) {
			RestaurantVO result = restaurantService.update(bean);
			if (result == null) {
				error.put("action", "Update failed");
			} else {
				request.setAttribute("update", result);
			}
			request.getRequestDispatcher("/02_Server/_68_Restaurant/Restaurant.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
