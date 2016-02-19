package _63_bfacility.controller;

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

import _04_facilities.model.FacilitiesService;
import _04_facilities.model.FacilitiesVO;

@WebServlet(urlPatterns = { "/Facilities.controller" })

public class FacilitiesServlet extends HttpServlet {

	private static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	private FacilitiesService facilitiesService = new FacilitiesService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //設定編碼
		
		// 接收資料
		String f_Id = request.getParameter("f_Id");
		String fName = request.getParameter("fName");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String count = request.getParameter("count");
		String fPic = request.getParameter("fPic");
		String prodaction = request.getParameter("prodaction");

		// 存放資料錯誤
		Map<String, String> error = new HashMap<String, String>();
		request.setAttribute("error", error);

		// 轉換資料

		// f_Id
		int n_fid = 0;
		if (f_Id != null && f_Id.length() != 0) {
			try {
				n_fid = Integer.parseInt(f_Id);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				error.put("f_Id", "設施編號只能是數字");
			}
		}

		// fName
		if (fName == null && fName.length() == 0) {
			error.put("fName", "設施名稱須輸入");
		}

		// date
		Date n_date = null;
		if (date != null && date.length() != 0) {
			try {
				n_date = sFormat.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
				error.put("date", "日期格式錯誤");
			}
		}

		// time
		Date n_time = null;
		if (time != null && time.length() != 0) {
			try {
				n_time = sFormat.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
				error.put("time", "時間格式錯誤");
			}
		}

		// count
		int n_count = 0;
		if (count != null && count.length() != 0) {
			try {
				n_count = Integer.parseInt(count);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				error.put("count", "容納人數須為數字");
			}
		}

		// fPic
		byte[] n_fPic = null;
		if (fPic != null && fPic.length() != 0) {
			n_fPic = fPic.getBytes();
		} else {
			//error.put("fPic", "必須選擇照片");
		}
		
		//驗證資料
		if ("Insert".equals(prodaction) || "Update".equals(prodaction)) {
			if ("Update".equals(prodaction)) {//更新需要id(Key)
				if (n_fid == 0) {
					error.put("f_Id", "請輸入ID才能" + prodaction);
				}
			}else{
				if (fPic.length() == 0 || fPic == null) {
					error.put("fPic", "請輸入照片才能" + prodaction);
				}
				if (fName.length() == 0 || fName == null) {
					error.put("fName", "請輸入設施名稱才能" + prodaction);
				}
				if (date.length() == 0 || date == null) {
					error.put("date", "請輸入開放日期才能" + prodaction);
				}
				if (time.length() == 0 || time == null) {
					error.put("time", "請輸入開放時間才能" + prodaction);
				}
				if (count.length() == 0 || count == null) {
					error.put("count", "請輸入設施介紹才能" + prodaction);
				}
			}
		}

		if (error != null && !error.isEmpty()) {
			request.getRequestDispatcher("/02_Server/_63_Facility/Facility.jsp").forward(request, response);
			return;
		}

		// 呼叫model

		FacilitiesVO bean = new FacilitiesVO();
		bean.setF_Id(n_fid);
		bean.setfName(fName);
		bean.setDate(n_date);
		bean.setTime(n_time);
		bean.setCount(n_count);
		bean.setfPic(n_fPic);
		
		// 根據model執行結果顯示view
		if ("Select".equals(prodaction)) {
			List<FacilitiesVO> result = facilitiesService.selectAll();
			request.setAttribute("select", result);
			System.out.println("00000"+result);
			request.getRequestDispatcher("/02_Server/_63_Facility/FacilityOut.jsp").forward(request, response);
		} else if ("Insert".equals(prodaction)) {
			FacilitiesVO result = facilitiesService.insert(bean);
			if (result == null) {
				error.put("action", "Insert failed");
			} else {
				request.setAttribute("insert", result);
			}
			request.getRequestDispatcher("/02_Server/_63_Facility/Facility.jsp").forward(request, response);

		} else if ("Update".equals(prodaction)) {
			FacilitiesVO result = facilitiesService.update(bean);
			if (result == null) {
				error.put("action", "Update failed");
			} else {
				request.setAttribute("update", result);
			}
			request.getRequestDispatcher("/02_Server/_63_Facility/Facility.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
