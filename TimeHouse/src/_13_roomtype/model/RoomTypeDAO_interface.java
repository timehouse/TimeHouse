package _13_roomtype.model;

import java.util.List;

public interface RoomTypeDAO_interface{
	   public void insert(RoomTypeVO roomTypeVO);
	    public void update(RoomTypeVO roomTypeVO);
	    public void delete(Integer roomType_id);
	    public RoomTypeVO findByPrimaryKey(Integer roomType_id);
	    public List<RoomTypeVO> getAll();
}
