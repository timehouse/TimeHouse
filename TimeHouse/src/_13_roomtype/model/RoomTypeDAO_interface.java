package _13_roomtype.model;

import java.util.List;
import java.util.Set;

import _12_roompic.model.RoomPicVO;

public interface RoomTypeDAO_interface{
	   public void insert(RoomTypeVO roomTypeVO);
	    public void update(RoomTypeVO roomTypeVO);
	    public void delete(Integer roomType_id);
	    public RoomTypeVO findByPrimaryKey(Integer roomType_id);
	    public List<RoomTypeVO> getAll();
	    public Set<RoomPicVO> getRoomPicsByRoomTypeid(Integer roomType_id);
}
