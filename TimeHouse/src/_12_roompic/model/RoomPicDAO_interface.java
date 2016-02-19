package _12_roompic.model;

import java.util.List;

public interface RoomPicDAO_interface {
	   public void insert(RoomPicVO roomPicVO);
	    public void update(RoomPicVO roomPicVO);
	    public void delete(Integer roomPic_id);
	    public RoomPicVO findByPrimaryKey(Integer roomPic_id);
	    public List<RoomPicVO> getAll();
}
