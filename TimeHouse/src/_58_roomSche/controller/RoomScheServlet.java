package _58_roomSche.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import _11_room.model.RoomVO;
import _13_roomtype.model.RoomTypeVO;
import hibernate.util.HibernateUtil;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

//驗證,錯誤訊息都沒做=w=
@WebServlet(urlPatterns = { "/roomSche/roomScheServlet" })
public class RoomScheServlet extends HttpServlet {
	private RroomScheService rService = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		rService = new RroomScheService();
		String action = req.getParameter("action");
		System.out.println(action);

		if ("listroom".equals(action)) {
			String roomId = req.getParameter("roomId");
			List<RoomVO> roomlist = new LinkedList<RoomVO>();
			System.out.println(roomId.trim());
			// 判斷選一間還是選很多間
			if (roomId.matches("\\d{1,}")) {
				RoomVO roomVO= rService.getOneRoom(Integer.parseInt(roomId)); 
				if (roomVO!= null)	roomlist.add(roomVO);
			} else {
				roomlist = rService.listroom();
				resp.setCharacterEncoding("UTF-8");
				// 去除空白
				for (RoomVO vo : roomlist) {
					vo.setRoom_type(vo.getRoom_type().trim());
					vo.setrContext(vo.getrContext().trim());
				}
			}

			if (roomlist.size() != 0)
				req.setAttribute("rooms", roomlist);
			req.getRequestDispatcher("/02_Server/_58_RoomSche/RoomSche.jsp").forward(req, resp);

		} else if ("subOne".equals(action)) {
			// 接收
			String tempId = req.getParameter("id");
			String tempRStatus = req.getParameter("rStatus");
			String rContext = req.getParameter("rContext");

			// 驗證
			// 轉換
			Integer id = Integer.parseInt(tempId);
			boolean rStatus = Boolean.valueOf(tempRStatus);
			// 呼叫
			rService.updateOneRoomStatus(id, rStatus, rContext);
			// 不送回
		} else if ("AllSubmit".equals(action)) {
			// 接收&驗證&轉換
			String tempI = req.getParameter("number");
			Integer i = Integer.parseInt(tempI);
			for (int l = 0; l < i; l++) {
				String tempRoom_id = req.getParameter(String.format("room_id%d", l));
				String tempRStatus = req.getParameter(String.format("rStatus%d", l));
				Integer id = Integer.parseInt(tempRoom_id);
				boolean rStatus = Boolean.valueOf(tempRStatus);
				String rContext = req.getParameter(String.format("rContext%d", l));
				rService.updateOneRoomStatus(id, rStatus, rContext);
			}

			// 呼叫
			// 不送回
			//req.getParameter("room_id0");
		}
	}
}
