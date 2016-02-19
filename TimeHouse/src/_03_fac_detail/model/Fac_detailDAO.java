package _03_fac_detail.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _04_facilities.model.FacilitiesVO;
import _09_order.model.OrderVO;
import hibernate.util.HibernateUtil;

public class Fac_detailDAO implements Fac_detail_InterfaceDAO {
	
	private SessionFactory sessionFactory;
	public Fac_detailDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if(sessionFactory!=null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	
	OrderVO bean;
	public void setBean(OrderVO bean) {
		this.bean = bean;
	}
	
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

			Fac_detail_InterfaceDAO dao = new Fac_detailDAO();
			
			//selectAll_ok
//			List<Fac_detailVO> beans = dao.selectAll();
//			System.out.println("selectAll:" + beans);
			
			
			//select_ok
//			OrderVO order_id= new OrderVO();
//			order_id.setOrder_id(2);
//			List<Fac_detailVO> beanso = dao.select(order_id);
//			System.out.println("select:" + beanso);
			
			//delete_ok
//			FacilitiesVO f_id= new FacilitiesVO();
//			f_id.setF_id(2);
//			int dc=dao.delete(f_id, order_id);
//			System.out.println("delete:" + dc);
			
//			Integer a=6;

			//insert
//			dao.insert(f_id, order_id, a);
			
			//update
//			Fac_detailVO countt=new Fac_detailVO();
//			countt.setCount(6);
//			int up= dao.update(f_id, order_id, countt.getCount());
//			System.out.println("update:" + up);
			
			
	
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}

	}
	
	@Override
	public int update(FacilitiesVO f_id, OrderVO order_id, Integer count) {
//		Fac_detailVO bean = (Fac_detailVO) getSession().get(Fac_detailVO.class,f_id);
//		if(bean!=null) {
//			bean.setF_id(f_id);
//			bean.setOrder_id(order_id);
//			bean.setCount(count);
//			return 1;
//		}
//		return 0;
//		int b=count;
//		Query query= getSession().createQuery("update Fac_detailVO set count = " + b + "where f_id =" + f_id.getF_id() + "and" + "order_id=" + order_id.getOrder_id());		
//		int a=query.executeUpdate();
//		return a;
		return 0;
	}

	@Override
	public List<Fac_detailVO> select(OrderVO order_id) {
		return getSession().createQuery("from Fac_detailVO WHERE order_id =" + order_id.getOrder_id()).list();
	}

	@Override
	public List<Fac_detailVO> selectAll() {
		return getSession().createQuery("from Fac_detailVO").list();
	}


	@Override
	public int insert(FacilitiesVO f_id, OrderVO order_id, Integer count) {
		Query query= getSession().createQuery("INSERT INTO Fac_detailVO(" + f_id.getF_Id() + "," + order_id.getOrder_id()+"," + count + ")");
		int a=query.executeUpdate();
		return a;
	}
	@Override
	public int delete(FacilitiesVO f_id, OrderVO order_id) {
		
		Query query= getSession().createQuery("DELETE FROM Fac_detailVO WHERE order_id= " + order_id.getOrder_id() + "and f_id=" + f_id.getF_Id());
		int a=query.executeUpdate();
		return a;
		
	}
	
	@Override
	public int deleteByOrderId(OrderVO order_id) {   //用再刪除訂單  Lin
		Query query= getSession().createQuery("DELETE FROM Fac_detailVO WHERE order_id= " + order_id.getOrder_id());
		int a=query.executeUpdate();
		return a;
		
	}

}
