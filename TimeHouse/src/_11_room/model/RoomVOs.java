package _11_room.model;

import java.io.Serializable;
import java.util.List;

public class RoomVOs implements Serializable{
    private List<RoomVO> roomVOs;

    public List<RoomVO> getRoomVOs() {
	return roomVOs;
    }

    public void setRoomVOs(List<RoomVO> roomVOs) {
	this.roomVOs = roomVOs;
    }
}
