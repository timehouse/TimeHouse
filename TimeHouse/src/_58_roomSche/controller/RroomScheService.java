package _58_roomSche.controller;

import java.util.List;

import _11_room.model.RoomDAO;
import _11_room.model.RoomDAO_interface;
import _11_room.model.RoomVO;

public class RroomScheService {
 
    private RoomDAO_interface roomDAO = new RoomDAO();

    public List<RoomVO> listroom() {
	List<RoomVO> rooms = roomDAO.getAll();
	return rooms;
    }
    
    public RoomVO getOneRoom(int room_id){
    	return roomDAO.findByPrimaryKey(room_id);
    }
    
    public void updateOneRoomStatus(Integer id, boolean rStatus,String rContext) {
	RoomVO rVo =roomDAO.findByPrimaryKey(id);
	rVo.setrStatus(rStatus);
	rVo.setrContext(rContext);
	roomDAO.update(rVo);
    }

    public void printJsonRooms(){
	
    }
    
}
