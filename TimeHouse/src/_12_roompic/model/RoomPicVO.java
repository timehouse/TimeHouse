package _12_roompic.model;
// Generated 2016/1/21 �U�� 01:39:27 by Hibernate Tools 4.3.1.Final

import org.hibernate.Session;

import _13_roomtype.model.RoomTypeVO;
import hibernate.util.HibernateUtil;

/**
 * RoomPic generated by hbm2java
 */
public class RoomPicVO implements java.io.Serializable {
	private Integer roomPic_id;
	private RoomTypeVO roomType;
	private byte[] rpPic;
	private String rpPicName;
	
//	public static void main(String[] args) {
//		
//		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		
//		RoomPicVO guestBean=(RoomPicVO)session.get(RoomPicVO.class,1 );
//		System.out.println(guestBean);
//		
//		session.getTransaction().commit();
//		
//		
//	}

	public Integer getRoomPic_id() {
		return roomPic_id;
	}
	public void setRoomPic_id(Integer roomPic_id) {
		this.roomPic_id = roomPic_id;
	}
	public RoomTypeVO getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomTypeVO roomType) {
		this.roomType = roomType;
	}
	public byte[] getRpPic() {
		return rpPic;
	}
	public void setRpPic(byte[] rpPic) {
		this.rpPic = rpPic;
	}
	public String getRpPicName() {
		return rpPicName;
	}
	public void setRpPicName(String rpPicName) {
		this.rpPicName = rpPicName;
	}

}
