package _58_roomSche.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import _11_room.model.RoomDAO;
import _11_room.model.RoomVO;
import _13_roomtype.model.RoomTypeVO;
import _58_roomSche.model.RroomScheService;

public class SubmitAction extends ActionSupport implements RequestAware, SessionAware {
	private Map<String, Object> session;
	private Map<String, Object> request;
	private RroomScheService rService = new RroomScheService();
	private JSONArray rooms;

	@Override
	public String execute() throws Exception {
		System.out.println(rooms);
		List<RoomVO> list = new LinkedList<RoomVO>();
		for (int i = 0; i < rooms.size(); i++) {
			JSONObject JSobj = (JSONObject) rooms.get(i);
			// 接收
			String tempRoom_id = (String) JSobj.get("room_id");
			String tempRStatus = (String) JSobj.get("rStatus");
			String room_type = (String) JSobj.get("room_type");
			String rContext = (String) JSobj.get("rContext");
			String tempRoomType_id = (String) JSobj.get("roomType_id");

			// 驗證&轉換
			if (tempRoom_id == null || !tempRoom_id.matches("^\\d{1,5}$")) {
				this.addFieldError(String.format("roomId%d", i), "請輸入1~5位的正確數字");
			}
			if (tempRStatus == null || (!tempRStatus.matches("true") && !tempRStatus.matches("true"))) {
				this.addFieldError(String.format("rStatus%d", i), "房間狀態有誤");
			}
			System.out.println(room_type);
			if (room_type == null) {
				this.addFieldError(String.format("room_type%d", i), "房型為空");
			}
			if (rContext == null) {
				this.addFieldError(String.format("rContext%d", i), "房間狀態為空");
			}
			if (tempRoomType_id == null || !tempRoomType_id.matches("^\\d{1,2}$")) {
				this.addFieldError(String.format("roomType_id%d", i), "請輸入1~2位的正確數字");
			}
			System.out.println(this.getFieldErrors());
			if (this.getFieldErrors().isEmpty()) {
				Integer id = Integer.parseInt(tempRoom_id);
				boolean rStatus = Boolean.valueOf(tempRStatus);
				Integer roomType_id = Integer.parseInt(tempRoomType_id);
				RoomVO rVo = new RoomVO();
				rVo.setRoom_id(id);
				rVo.setrStatus(rStatus);
				rVo.setrContext(rContext);
				RoomTypeVO rtVo = new RoomTypeVO();
				rtVo.setRoomType_id(roomType_id);
				rVo.setRoomType(rtVo);
				rVo.setRoom_type(room_type);

				rService.updateOneRoomStatus(rVo);
				// String rContext = rContext;
			} else {
				this.addFieldError("沒有成功上傳", "房間狀態有誤");
				return Action.INPUT;
			}
			// 轉換為vo儲存
		}
		return Action.SUCCESS;
	}

	@Override
	public void validate() {
		super.validate();
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public JSONArray getRooms() {
		return rooms;
	}

	public void setRooms(JSONArray rooms) {
		this.rooms = rooms;
	}

}
