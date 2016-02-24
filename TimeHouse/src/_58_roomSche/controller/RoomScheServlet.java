package _58_roomSche.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _11_room.model.RoomVO;
import _58_roomSche.model.RroomScheService;
import _58_roomSche.model.RroomScheService0;

//開始做驗證和錯誤訊息,全部都用doget
@WebServlet(urlPatterns = { "/roomSche/roomScheServlet" })
public class RoomScheServlet extends HttpServlet {

	private static final long serialVersionUID = 209251350521474080L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RroomScheService0 rService = new RroomScheService0();
		// 抓取服務名稱
		String action = req.getParameter("action");
		System.out.println(action);
		// 準備錯誤訊息
		Map<String, String> errors = new HashMap<String, String>();
		req.getSession().setAttribute("errors", errors);

		// 服務:顯示房間,根據輸入id或無輸入id顯示所有房間
		if ("listroom".equals(action)) {
			listroom(req, resp, errors, rService);
		} else if ("subOne".equals(action)) {
			subOneServ(req, resp, errors, rService);
		} else if ("AllSubmit".equals(action)) {
			AllSubmitServ(req, resp, errors, rService);
		}

	}

	private void AllSubmitServ(HttpServletRequest req, HttpServletResponse resp, Map<String, String> errors,
			RroomScheService0 rService) {
		// 接收&驗證&驗證資料量
		String tempLength = req.getParameter("number");
		Integer length = null;
		if (tempLength == null || tempLength.matches("^\\d{1,5}")) {
			length = Integer.parseInt(tempLength);
		} else {
			errors.put("length", "房間資料數量不正確");
		}

		if (length != null) {
			for (int l = 0; l < length; l++) {
				// 接收&驗證
				String tempRoom_id = req.getParameter(String.format("room_id%d", l));
				String tempRStatus = req.getParameter(String.format("rStatus%d", l));
				String rContext = req.getParameter(String.format("rContext%d", l));
				// 轉換
				if (tempRoom_id == null || !tempRoom_id.matches("^\\d{1,5}$")) {
					errors.put(String.format("roomId%d", l), "請輸入1~5位的正確數字");
				}
				if (tempRStatus == null || (!tempRStatus.matches("true") && !tempRStatus.matches("true"))) {
					errors.put(String.format("rStatus",  l), "房間狀態有誤");
				}
				if (rContext == null) {
					errors.put(String.format("rContext",  l), "房間狀態敘述有誤");
				}
				// 錯誤判斷,無錯則轉換&呼叫model
				if(errors.isEmpty()){
					Integer id = Integer.parseInt(tempRoom_id);
					boolean rStatus = Boolean.valueOf(tempRStatus);
					rService.updateOneRoomStatus(id, rStatus, rContext);
					// 依據回傳決定顯示: AjaxServ不回傳資料,只回傳錯誤訊息json
				}else{
					// 若沒有成功顯示,回傳錯誤
					// 待確認json使用哪個函式庫
					PrintWriter out;
					try {
						out = resp.getWriter();
						for (Map.Entry error : errors.entrySet()) {
							System.out.println(error.getKey() + ":" + error.getValue());
							out.write(error.getKey() + ":" + error.getValue());
						}	
					} catch (IOException e) {
						System.out.println("在取得resp.getWriter時發生錯誤");
						e.printStackTrace();
					}
					return;
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void subOneServ(HttpServletRequest req, HttpServletResponse resp, Map<String, String> errors,
			RroomScheService0 rService) throws IOException {
		// 接收
		String tempId = req.getParameter("id");
		String tempRStatus = req.getParameter("rStatus");
		String rContext = req.getParameter("rContext");
		// 驗證
		Integer id = null;
		Boolean rStatus = null;
		if (tempId == null || !tempId.matches("^\\d{1,5}$")) {
			errors.put("roomId", "請輸入1~5位的正確數字");
		}
		if (tempRStatus == null || (!tempRStatus.matches("true") && !tempRStatus.matches("true"))) {
			errors.put("rStatus", "房間狀態有誤");
		}
		if (rContext == null) {
			errors.put("rContext", "房間狀態敘述有誤");
		}
		// 判斷無錯,無錯則轉換
		id = Integer.parseInt(tempId);
		// rContext=rContext;
		rStatus = Boolean.parseBoolean(tempRStatus);
		// 錯誤判斷,無錯則呼叫model
		System.out.println(errors);
		if (errors.isEmpty()) {
			rService.updateOneRoomStatus(id, rStatus, rContext);
			return;
			// 依據回傳決定顯示: AjaxServ不回傳資料,只回傳錯誤訊息json
		}
		// 若沒有成功顯示,回傳錯誤
		// 待確認json使用哪個函式庫
		PrintWriter out = resp.getWriter();
		for (Map.Entry error : errors.entrySet()) {
			System.out.println(error.getKey() + ":" + error.getValue());
			out.write(error.getKey() + ":" + error.getValue());
		}
		// 不送回
	}

	private void listroom(HttpServletRequest req, HttpServletResponse resp, Map<String, String> errors,
			RroomScheService0 rService) throws ServletException, IOException {
		// 接收
		String roomIdTemp = req.getParameter("roomId");
		List<RoomVO> roomlist = new LinkedList<RoomVO>();
		// 驗證&轉換
		Integer roomId = null;
		System.out.println(roomIdTemp);
		if (roomIdTemp.trim().length() > 0) {
			if (roomIdTemp.matches("\\d{1,}")) {
				roomId = Integer.parseInt(roomIdTemp);
			} else {
				errors.put("roomId", "請輸入1~5位的正確數字");
			}
		}
		// 錯誤判斷
		System.out.println("errors:" + errors);
		if (errors.isEmpty()) {
			// 判斷是列單房還是列全房呼叫model
			if (roomId != null) {
				RoomVO roomVO = rService.getOneRoom(roomId);
				if (roomVO != null) {
					roomlist.add(roomVO);
				}
			} else {
				roomlist = rService.listroom();
			}
			// 去除空白(應改由前端去除降低伺服器負擔?)
			for (RoomVO vo : roomlist) {
				vo.setRoom_type(vo.getRoom_type().trim());
				vo.setrContext(vo.getrContext().trim());
			}
			// 依據回傳決定顯示
			if (roomlist.size() != 0) {
				req.setAttribute("rooms", roomlist);
			}
			req.getRequestDispatcher("/02_Server/_58_RoomSche/RoomSche.jsp").forward(req, resp);
			return;
		}
		// 若沒有成功顯示,回傳錯誤
		errors.put("errorServlet", "RoomScheServlet");
		resp.setCharacterEncoding("UTF-8");
		resp.sendRedirect(getServletContext().getContextPath() + "/02_Server/_58_RoomSche/RoomSche.jsp");
	}
}
