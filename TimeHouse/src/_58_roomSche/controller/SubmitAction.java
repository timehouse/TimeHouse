package _58_roomSche.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import _11_room.model.RoomVO;
import _11_room.model.RoomVOs;
import _58_roomSche.model.RroomScheService;

public class SubmitAction extends ActionSupport implements RequestAware, SessionAware {
    private Map<String, Object> session;
    private Map<String, Object> request;
    private RoomVOs rooms;

    private InputStream inputstream;

    private RroomScheService rService = new RroomScheService();

    @Override
    public String execute() {
	try {
	    System.out.println(rooms.getRoomVOs());
	    List<RoomVO> list = rooms.getRoomVOs();
	    
	    for (int i = 0; i < list.size(); i++) {
		rService.updateOneRoomStatus(list.get(i));
	    }
	    inputstream = new ByteArrayInputStream("上傳成功".getBytes());
	    return Action.SUCCESS;
	} catch (Exception e) {
	    inputstream = new ByteArrayInputStream("上傳失敗".getBytes());
	    return Action.INPUT;
	}
    }

    //驗證錯誤會呼叫此方法
    @Override
    public boolean hasErrors() {
    
	this.getFieldErrors().forEach((k,v)->{
	    ((List<String>) v).forEach((x)->System.out.println(k+":"+x));
	});
	inputstream = new ByteArrayInputStream("上傳失敗".getBytes());
	return super.hasErrors();
    }
 
    @Override
    public void setSession(Map<String, Object> session) {
	this.session = session;
    }

    @Override
    public void setRequest(Map<String, Object> request) {
	this.request = request;
    }

    public InputStream getInputStream() {
	return inputstream;
    }

    public RoomVOs getRooms() {
	return rooms;
    }

    public void setRooms(RoomVOs rooms) {
	this.rooms = rooms;
    }

}
