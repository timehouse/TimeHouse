package _10_package.model;

import java.util.Arrays;

import org.hibernate.Session;

import _13_roomtype.model.RoomTypeVO;
import hibernate.util.HibernateUtil;

public class PackageVO {

	private Integer package_id;
	private String name;
	private RoomTypeVO roomtype;
	private Integer price;
	private java.util.Date start_date;
	private java.util.Date end_date;
	private String context;
	private byte[] pic;

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		PackageVO packageBean = (PackageVO) session.get(PackageVO.class, 1);
		System.out.println(packageBean);

		session.getTransaction().commit();

	}

	@Override
	public String toString() {
		return "PackageBean [package_id=" + package_id + ", name=" + name + ", room_type=" + roomtype + ", price="
				+ price + ", start_date=" + start_date + ", end_date=" + end_date + ", context=" + context + ", pic="
				+ Arrays.toString(pic) + "]";
	}

	public Integer getPackage_id() {
		return package_id;
	}

	public void setPackage_id(Integer package_id) {
		this.package_id = package_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoomTypeVO getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(RoomTypeVO roomtype) {
		this.roomtype = roomtype;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public java.util.Date getStart_date() {
		return start_date;
	}

	public void setStart_date(java.util.Date start_date) {
		this.start_date = start_date;
	}

	public java.util.Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(java.util.Date end_date) {
		this.end_date = end_date;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

}
