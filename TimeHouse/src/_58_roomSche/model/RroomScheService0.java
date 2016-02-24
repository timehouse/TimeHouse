/* 未使用struts,方法多為變數傳入而非ORM*/
package _58_roomSche.model;

import java.util.List;

import org.hibernate.Session;

import _11_room.model.RoomDAO;
import _11_room.model.RoomDAO_interface;
import _11_room.model.RoomVO;
import hibernate.util.HibernateUtil;

public class RroomScheService0 {
 
    private RoomDAO_interface roomDAO = new RoomDAO();

    public List<RoomVO> listroom() {
	List<RoomVO> rooms = roomDAO.getAll();
	return rooms;
    }
    
    public RoomVO getOneRoom(int room_id){
    	return roomDAO.findByPrimaryKey(room_id);
    }
    
//    public void updateOneRoomStatus(RoomVO rVo) {
//	//必定有id 
//	roomDAO.update(rVo);
//    }
    public void updateOneRoomStatus(Integer id, boolean rStatus,String rContext) {
	//必定有id
    RoomVO rVo =roomDAO.findByPrimaryKey(id);
	rVo.setrStatus(rStatus);
	rVo.setrContext(rContext);
	roomDAO.update(rVo);
    }
   
}
