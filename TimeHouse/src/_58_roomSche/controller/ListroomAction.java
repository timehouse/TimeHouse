package _58_roomSche.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import _11_room.model.RoomVO;
import _58_roomSche.model.RroomScheService;

public class ListroomAction extends ActionSupport implements RequestAware, SessionAware {

    private static final long serialVersionUID = 1L;
    // 輸入
    private Map<String, Object> session;
    private Map<String, Object> request;
    Integer roomId;

    // 預備變數
    private RroomScheService rService = new RroomScheService();
   
    @Override
    public String execute() throws Exception {
    
	List<RoomVO> roomlist = new LinkedList<RoomVO>();
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
	    request.put("rooms", roomlist);
	}
	return Action.SUCCESS;
    }
    
    @Override
    public void setSession(Map<String, Object> session) {
	this.session = session;
    }

    @Override
    public void setRequest(Map<String, Object> request) {
	this.request = request;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    
}
