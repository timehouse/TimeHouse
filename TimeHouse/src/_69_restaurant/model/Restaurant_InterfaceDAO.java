package _69_restaurant.model;

import java.util.List;

public interface Restaurant_InterfaceDAO {
	RestaurantVO insert(RestaurantVO bean);//新增餐廳(後台)
	RestaurantVO update(Integer rest_id,String rest_name,String rest_time,String rest_address,String rest_context,byte[] rest_pic);//更新餐廳(後台)
	List<RestaurantVO> selectAll();//查詢所有餐廳
}
