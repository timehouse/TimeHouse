package _03_fac_detail.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import _04_facilities.model.FacilitiesVO;
import _09_order.model.OrderVO;
import hibernate.util.HibernateUtil;

public class Fac_detailVO  implements Serializable{
	private FacilitiesVO f_id;
	private OrderVO order_id;
	private Integer count;
		
	public OrderVO getOrder_id() {
		return order_id;
	}

	@Override
	public String toString() {
		return "Fac_detailVO [f_id=" + f_id + ", order_id=" + order_id + ", count=" + count + "]";
	}

	public void setOrder_id(OrderVO order_id) {
		this.order_id = order_id;
	}
	public FacilitiesVO getF_id() {
		return f_id;
	}
	public void setF_id(FacilitiesVO f_id) {
		this.f_id = f_id;
	}


	public static void main(String[] args) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
//		Fac_detailVO bean = (Fac_detailVO)session.get(Fac_detailVO.class, 1);
		String stmt="from Fac_detailVO";
		Query query= session.createQuery(stmt);
		List list=(List) query.list();
		System.out.println(list);
		session.getTransaction().commit();
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
