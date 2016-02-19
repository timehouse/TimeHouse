package _09_order.model;

import java.util.Date;
import java.util.List;

import _03_fac_detail.model.Fac_detailDAO;
import _03_fac_detail.model.Fac_detail_InterfaceDAO;

public class OrderService {
	private Order_InterfaceDAO orderDao = new OrderDAO();
	
	
	
	
	//後台，獲取全部訂單
	public List<OrderVO> selectAll(){
		List<OrderVO> result = null;
		result = orderDao.selectAll();
		return result;
	}
	
	//後台，獲取單一訂單資料
		public OrderVO selectId(OrderVO bean) {
			OrderVO result = null;
			if (bean != null && bean.getOrder_id() != 0) {
				result = orderDao.findByPrimaryKey(bean.getOrder_id());
			}
			return result;
		}
	
	// 後台，修改訂單
		public OrderVO updateOrder(OrderVO bean) {
			OrderVO result = null;
			orderDao.findByPrimaryKey(bean.getOrder_id());
			if (bean != null) {
				orderDao.update(bean);
				result = bean;
			}
			return result;
		}
	//後台，刪除訂單
		public OrderVO deleteOrder(OrderVO bean) {
			OrderVO result = null;
			Fac_detail_InterfaceDAO fDao = new Fac_detailDAO();
			if (bean != null) {
				OrderVO o= new OrderVO();
				o.setOrder_id(bean.getOrder_id());
				fDao.deleteByOrderId(bean);
				orderDao.delete(bean.getOrder_id());
				result = bean;
			}
			return result;
		}
		
		//查詢單一會員資料
		public List<OrderVO> selectMember(OrderVO bean){
			List<OrderVO> result = null;
			if(bean!=null){
				result = orderDao.selectByMember(bean);
			}
			return result;
		}
		
		//利用checkin_date查詢
		public List<OrderVO> selectCheckin(Date date){
			List<OrderVO> result = null;
			if(date!=null){
				result = orderDao.selectByCheckin(date);
			}
			return result;
		}
		//利用checkout_date查詢
		public List<OrderVO> selectCheckout(Date date){
			List<OrderVO> result = null;
			if(date!=null){
				result = orderDao.selectByCheckout(date);
			}
			return result;
		}
	
	
	public static void main(String[] args) {
		
	}

}
