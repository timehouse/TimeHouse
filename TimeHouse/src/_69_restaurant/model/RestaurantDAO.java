package _69_restaurant.model;

import hibernate.util.HibernateUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _04_facilities.model.FacilitiesVO;

public class RestaurantDAO implements Restaurant_InterfaceDAO {
	
	private SessionFactory sessionFactory;
	public RestaurantDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if(sessionFactory!=null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}

	public static void main(String[] args) {
		HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		RestaurantDAO dao = new RestaurantDAO();
		
		//selectAll_ok
		List<RestaurantVO> beans = dao.selectAll();
		System.out.println("selectAll:" + beans+"/n");
		
		//update_ok
		RestaurantVO up= dao.update(1,"海霸王","全天候","4F","新鮮海鮮供應",null);
		System.out.println("update:" + up);
		
		//insert_ok
		RestaurantVO bean = new RestaurantVO();
		bean.setRest_name("AAA");
		bean.setRest_time("星期五");
		bean.setRest_context("五星級餐廳");
		bean.setRest_address("6F");
		bean.setRest_pic(null);
		RestaurantVO in=dao.insert(bean);
		System.out.println("insert:" + in);
	}

	@Override
	public RestaurantVO insert(RestaurantVO bean) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();  
	    session.save(bean);  
	    return bean;
	}

	@Override
	public RestaurantVO update(Integer rest_id,String rest_name,String rest_time,String rest_address,String rest_context,byte[] rest_pic) {
		RestaurantVO bean = (RestaurantVO) getSession().get(RestaurantVO.class, rest_id);
		if(bean!=null) {
			bean.setRest_id(rest_id);
			bean.setRest_name(rest_name);
			bean.setRest_time(rest_time);
			bean.setRest_address(rest_address);
			bean.setRest_context(rest_context);
			bean.setRest_pic(rest_pic);
			return bean;
		}
		return null;
	}

	@Override
	public List<RestaurantVO> selectAll() {
		return getSession().createQuery("from RestaurantVO").list();
	}

}
