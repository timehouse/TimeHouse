package _57_broom.model;

import java.util.LinkedList;
import java.util.List;

import _11_room.model.RoomDAO;
import _11_room.model.RoomDAO_interface;
import _11_room.model.RoomVO;
import _13_roomtype.model.RoomTypeDAO;
import _13_roomtype.model.RoomTypeDAO_interface;
import _13_roomtype.model.RoomTypeVO;

public class BroomService {
	private RoomDAO_interface roomDAO = new RoomDAO();
	private RoomTypeDAO_interface roomTypeDAO = new RoomTypeDAO();

	public List<RoomVO> listroom() {
		List<RoomVO> rooms = roomDAO.getAll();
		return rooms;
	}

	public RoomVO getOneRoom(int room_id) {
		return roomDAO.findByPrimaryKey(room_id);
	}

	public void updateOneRoomStatus(Integer id, boolean rStatus, String rContext, String room_type, Integer rTid) {
		RoomVO rVo = roomDAO.findByPrimaryKey(id);
		rVo.setrStatus(rStatus);
		rVo.setrContext(rContext);
		rVo.setRoomType(roomTypeDAO.findByPrimaryKey(rTid));
		rVo.setRoom_type(rVo.getRoomType().getRoom_type());
		roomDAO.update(rVo);
	}

	public List<RoomTypeVO> getAllRoom_Type() {
		return roomTypeDAO.getAll();
	}

	public int addOneRoom(Integer room_id) {
		RoomVO rVo = roomDAO.findByPrimaryKey(room_id);
		if (rVo == null) {
			rVo = new RoomVO();
			RoomTypeDAO_interface rtDAO = new RoomTypeDAO();
			rVo.setRoomType(rtDAO.findByPrimaryKey(1));
			rVo.setRoom_id(room_id);
			rVo.setrStatus(true);
			rVo.setRoom_type(rVo.getRoomType().getRoom_type());
			rVo.setrContext("");
			roomDAO.insert(rVo);
			return 1;
		} else {
			// 添加錯誤訊息
		}
		return 0;
	}

	public int deleteOneRoom(int room_id) {
		RoomDAO_interface rDAO = new RoomDAO();
		if (rDAO.findByPrimaryKey(room_id) != null) {
			rDAO.delete(room_id);
			return 1;
		}
		return 0;
	}
	// 未使用
	// public List<Integer> getAllRoomid() {
	// List<RoomVO> list = roomDAO.getAll();
	// List<Integer> roomids = new LinkedList<Integer>();
	// for (RoomVO rVo : list) {
	// roomids.add(rVo.getRoom_id());
	// }
	// return roomids;
	// }

	// public String getAllUnusedRoomid() {
	// List<Integer> list = getAllRoomid();
	// int min = list.get(0);
	// int max = list.get(list.size() - 1);
	// return null;
	// }
}
