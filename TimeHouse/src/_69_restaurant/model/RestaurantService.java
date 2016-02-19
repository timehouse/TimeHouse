package _69_restaurant.model;

import hibernate.util.HibernateUtil;

import java.util.List;

import org.hibernate.Session;

public class RestaurantService {
	RestaurantDAO restaurantDAO = new RestaurantDAO();
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		try {
			RestaurantService service = new RestaurantService();
			RestaurantVO bean = new RestaurantVO();
			
//			bean.setRest_name("AAA");
//			bean.setRest_time("星期五");
//			bean.setRest_context("五星級餐廳");
//			bean.setRest_address("6F");
//			bean.setRest_pic(null);
			
			//insert
//			RestaurantVO in = service.insert(bean);
//			System.out.println("insert:" + in);

			//selectAll
			List<RestaurantVO> al = service.selectAll();
			System.out.println("selectAll:" + al);

			//update
//			bean.setRest_id(1);
//			RestaurantVO up = service.update(bean);
//			System.out.println("update:" + up);

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();

		}

	}
	
	// 新增餐廳
	public RestaurantVO insert(RestaurantVO bean) {
		if (bean != null) {
			return restaurantDAO.insert(bean);
		}
		return null;
	}

	// 查看所有餐廳
	public List<RestaurantVO> selectAll() {
		if (restaurantDAO.selectAll() != null) {
			return restaurantDAO.selectAll();
		}
		return null;
	}

	// 更新餐廳狀態
	public RestaurantVO update(RestaurantVO bean) {
		if (bean != null) {
			return restaurantDAO.update(bean.getRest_id(),
										bean.getRest_name(),
										bean.getRest_time(),
										bean.getRest_address(),
										bean.getRest_context(),
										bean.getRest_pic());
		}
		return null;
	}
}
