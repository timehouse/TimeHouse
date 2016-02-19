package _05_guest.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import _09_order.model.OrderVO;
import hibernate.util.HibernateUtil;

public class GuestVO {

	private Integer guest_id;
	private String  guest_last_name;
	private String  guest_first_name;
	private String gender;
	private String  id_number;
	private String tel;
	private String phone_number;
	private String  email;
	private Set<OrderVO> orders = new HashSet<OrderVO>();
  		  
	public static void main(String[] args) {
		
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		GuestVO guestBean=(GuestVO)session.get(GuestVO.class,1 );
		System.out.println(guestBean);
		
		session.getTransaction().commit();
		
		
	}

	@Override
	public String toString() {
		return "GuestVO [guest_id=" + guest_id + ", guest_last_name=" + guest_last_name + ", guest_first_name="
				+ guest_first_name + ", gender=" + gender + ", id_number=" + id_number + ", tel=" + tel
				+ ", phone_number=" + phone_number + ", email=" + email + "]";
	}


	public Integer getGuest_id() {
		return guest_id;
	}




	public void setGuest_id(Integer guest_id) {
		this.guest_id = guest_id;
	}




	public String getGuest_last_name() {
		return guest_last_name;
	}




	public void setGuest_last_name(String guest_last_name) {
		this.guest_last_name = guest_last_name;
	}




	public String getGuest_first_name() {
		return guest_first_name;
	}




	public void setGuest_first_name(String guest_first_name) {
		this.guest_first_name = guest_first_name;
	}

	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getId_number() {
		return id_number;
	}




	public void setId_number(String id_number) {
		this.id_number = id_number;
	}




	public String getTel() {
		return tel;
	}




	public void setTel(String tel) {
		this.tel = tel;
	}




	public String getPhone_number() {
		return phone_number;
	}




	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public Set<OrderVO> getOrders() {
		return orders;
	}




	public void setOrders(Set<OrderVO> orders) {
		this.orders = orders;
	}
	
	
	
}
