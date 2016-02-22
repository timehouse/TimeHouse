package _11_room.model;
// Generated 2016/1/21 �U�� 01:39:27 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import _09_order.model.OrderVO;
import _13_roomtype.model.RoomTypeVO;
import hibernate.util.HibernateUtil;

/**
 * Room generated by hbm2java
 */
public class RoomVO implements java.io.Serializable {

    private RoomTypeVO roomType;
    private Set<OrderVO> orders = new HashSet<OrderVO>(0);
    private Integer room_id;
    private boolean rStatus;
    private String room_type;
    private String rContext;

    // public static void main(String[] args) {
    //
    // Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    // session.beginTransaction();
    //
    // RoomVO packageBean = (RoomVO) session.get(RoomVO.class, 101);
    // System.out.println(packageBean);
    //
    // session.getTransaction().commit();
    //
    // }

    @Override
    public String toString() {
	// return "{room_id:" + room_id + ",roomType:" +
	// roomType.getRoomType_id() + ",rStatus:" + rStatus + ",room_type:"
	// + room_type + ",rContext:" + rContext + "}";
	return "{room_id:" + room_id + ",roomType:" + roomType.getRoomType_id() + ",rStatus:" + rStatus + ",room_type:"
		+ room_type + ",rContext:" + rContext + "}";
    }

    public Integer getRoom_id() {
	return room_id;
    }

    public void setRoom_id(Integer room_id) {
	this.room_id = room_id;
    }

    public RoomTypeVO getRoomType() {
	return roomType;
    }

    public void setRoomType(RoomTypeVO roomType) {
	this.roomType = roomType;
    }

    public boolean isrStatus() {
	return rStatus;
    }

    public void setrStatus(boolean rStatus) {
	this.rStatus = rStatus;
    }

    public String getRoom_type() {
	return room_type;
    }

    public void setRoom_type(String room_type) {
	this.room_type = room_type;
    }

    public String getrContext() {
	return rContext;
    }

    public void setrContext(String rContext) {
	this.rContext = rContext;
    }

    public Set<OrderVO> getOrders() {
	return orders;
    }

    public void setOrders(Set<OrderVO> orders) {
	this.orders = orders;
    }

}
