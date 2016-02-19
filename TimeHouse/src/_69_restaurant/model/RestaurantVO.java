package _69_restaurant.model;

import hibernate.util.HibernateUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

public class RestaurantVO {
	
	private Integer rest_id;
	private String rest_name;
	private String rest_time;
	private String rest_address;
	private String rest_context;
	private byte[] rest_pic;

	private Set<RestaurantVO> restaurantVO = new HashSet<RestaurantVO>();
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		RestaurantVO bean = (RestaurantVO) session.get(RestaurantVO.class,1);
		System.out.println(bean);
		session.getTransaction().commit();
	}
	

	@Override
	public String toString() {
		return "RestaurantVO [rest_id=" + rest_id + ", rest_name=" + rest_name
				+ ", rest_time=" + rest_time + ", rest_address=" + rest_address
				+ ", rest_context=" + rest_context + ", rest_pic="
				+ Arrays.toString(rest_pic) + ", restaurantVO=" + restaurantVO
				+ "]";
	}


	public Integer getRest_id() {
		return rest_id;
	}


	public void setRest_id(Integer rest_id) {
		this.rest_id = rest_id;
	}


	public String getRest_name() {
		return rest_name;
	}


	public void setRest_name(String rest_name) {
		this.rest_name = rest_name;
	}


	public String getRest_time() {
		return rest_time;
	}


	public void setRest_time(String rest_time) {
		this.rest_time = rest_time;
	}


	public String getRest_address() {
		return rest_address;
	}


	public void setRest_address(String rest_address) {
		this.rest_address = rest_address;
	}


	public String getRest_context() {
		return rest_context;
	}


	public void setRest_context(String rest_context) {
		this.rest_context = rest_context;
	}


	public byte[] getRest_pic() {
		return rest_pic;
	}


	public void setRest_pic(byte[] rest_pic) {
		this.rest_pic = rest_pic;
	}


	public Set<RestaurantVO> getRestaurantVO() {
		return restaurantVO;
	}


	public void setRestaurantVO(Set<RestaurantVO> restaurantVO) {
		this.restaurantVO = restaurantVO;
	}
}
