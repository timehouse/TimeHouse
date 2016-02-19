package _03_fac_detail.model;

import java.util.List;

import _04_facilities.model.FacilitiesVO;
import _09_order.model.OrderVO;

public interface Fac_detail_InterfaceDAO {
	int insert(FacilitiesVO f_id,OrderVO order_id,Integer count);//新增預約設施訂單(前台)
	int update(FacilitiesVO f_id,OrderVO order_id,Integer count);//修改預約設施訂單(前台)
	List<Fac_detailVO> select(OrderVO order_id);//查詢某訂單(會員)預約的設施(前台)
	int delete(FacilitiesVO f_id,OrderVO order_id);//刪除預約設施訂單(前台)
	List<Fac_detailVO> selectAll();//查詢所有預約設施訂單(後台)
	int deleteByOrderId(OrderVO order_id);
}
