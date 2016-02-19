package _11_room.model;

import java.util.List;

public interface RoomDAO_interface{
	public void insert(RoomVO roomVO);

	public void update(RoomVO roomVO);

	public void delete(Integer room_id);

	public RoomVO findByPrimaryKey(Integer room_id);

	public List<RoomVO> getAll();
}
