package _09_order.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import hibernate.util.HibernateUtil;

public class OrderDAO implements Order_InterfaceDAO {
	private SessionFactory sessionFactory;
	public OrderDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	@Override
	public Session getSession() {
		if(sessionFactory!=null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	
	@Override
	public OrderVO findByPrimaryKey(Integer order_id) {
		OrderVO orderVO = null;
		orderVO = (OrderVO) getSession().get(OrderVO.class, order_id);
		return orderVO;
	}
	
	@Override
	public void insert(OrderVO orderVO) {
			getSession().saveOrUpdate(orderVO);
	}

	@Override
	public void update(OrderVO orderVO) {
			getSession().saveOrUpdate(orderVO);;
		
	}

	@Override
	public void delete(Integer order_id) {	
			OrderVO orderVO = new OrderVO();
			orderVO.setOrder_id(order_id);
			getSession().delete(orderVO);		
	}

	private static final String GET_ALL_STMT= "from OrderVO order by order_id desc";
	
	@Override
	public List<OrderVO> selectAll() {
		List<OrderVO> list = null;
			Query query = getSession().createQuery(GET_ALL_STMT);
			list = query.list();
		return list;
	}
	
	private static final String GET_SPECIFIC_STMT= 
			"from OrderVO where order_id like :a or member_account like :b or checkin_date like :c  order by order_id";
//	"from OrderVO where order_id =? or member_account=? or checkin_date=?  order by order_id";	
	  //嘗試全部欄位的模糊查詢，失敗，以後試
	public List<OrderVO> selectSpecific(String member_account) {
//		public List<OrderVO> selectSpecific(OrderVO bean) {
		List<OrderVO> list = null;
//			Query query = getSession().createQuery(GET_SPECIFIC_STMT);
//			query.setParameter(0, bean.getOrder_id());
//			query.setParameter(1, bean.getMember_account());
//			query.setParameter(2, bean.getCheckin_date());
//			query.setParameter(0, "%"+id+"%");
//			query.setParameter(1, "%"+member_account+"%");
//			query.setParameter(2, "%"+checkin_date+"%");
//			query.setParameter("a", "%"+id+"%");
//			query.setParameter("b", "%"+member_account+"%");
//			query.setParameter("c", "%"+checkin_date+"%");
//			list = query.list();
			
			Criteria query = getSession().createCriteria(OrderVO.class);
//			query.add(Restrictions.like("order_id", id));
			query.add(Restrictions.like("member_account", "%"+member_account+"%"));
//			query.add(Restrictions.like("checkin_date", checkin_date));
			list = query.list();		
			;
			
		return list;
	}
	
	
	//查詢特定會員資訊，無法使用like%查詢
	@Override
	public List<OrderVO> selectByMember(OrderVO bean) {
		List<OrderVO> list = null;
			Criteria query = getSession().createCriteria(OrderVO.class);
//			query.add(Restrictions.like("member_account", "%"+bean.getMember_account()+"%"));
			query.add(Restrictions.like("member_account", bean.getMember_account()));
			list = query.list();
		return list;
	}
	
	//查詢特定checkin資訊
	@Override
	public List<OrderVO> selectByCheckin(Date date) {
		List<OrderVO> list = null;
			Criteria query = getSession().createCriteria(OrderVO.class);
			query.add(Restrictions.eq("checkin_date", date));
			list = query.list();
		return list;
	}
	
	//查詢特定checkout資訊，無法使用like%查詢
		@Override
	public List<OrderVO> selectByCheckout(Date date) {
		List<OrderVO> list = null;
			Criteria query = getSession().createCriteria(OrderVO.class);
			query.add(Restrictions.eq("checkout_date", date));
			list = query.list();
		return list;
	}
	
	//嘗試note的模糊查詢，成功
	public List<OrderVO> selectWhereNote(String note) {
		List<OrderVO> list = null;
			Criteria query = getSession().createCriteria(OrderVO.class);
			query.add(Restrictions.like("note", "%"+note+"%"));
			list = query.list();
		return list;
	}
	
	//失敗  無法使用like%查詢    只有varchar欄位才能使用like，只想查2016-01可以嘗試使用季去搜尋
	//如果欄位為int 可以嘗試用 between
	public List<OrderVO> selectWhereCheckin(String date) {
		List<OrderVO> list = null;
			Criteria query = getSession().createCriteria(OrderVO.class);
			query.add(Restrictions.like("checkin_date", date));
			list = query.list();
		return list;
	}

	public static void main(String[] args) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			OrderDAO dao = new OrderDAO();
			
			try {
				session.beginTransaction(); 	

//select	
//			OrderVO bean = dao.findByPrimaryKey(1);
//			System.out.println(bean);
			
//insert  
//			OrderVO orderVO = new OrderVO();
//			MemberVO memberVO = new MemberVO();
//			GuestVO guestVO = new GuestVO();
//			RoomVO roomVO = new RoomVO();
//			memberVO.setMember_account("da");
//			orderVO.setMember_account(memberVO);
//			guestVO.setGuest_id(3);
//			orderVO.setGuest_id(guestVO);
//			orderVO.setRoom_type("Double");
//			roomVO.setRoom_id(301);
//			orderVO.setRoom_id(roomVO);
//			orderVO.setCheckin_date(java.sql.Date.valueOf("2016-1-30"));
//			orderVO.setCheckout_date(java.sql.Date.valueOf("2016-2-6"));
//			orderVO.setAdults(3);
//			orderVO.setTotal_payment(6666);
//			orderVO.setNote("加一床");
//			orderVO.setBook_date(new java.util.Date());			
//			dao.insert(orderVO);
//			System.out.println(orderVO.getNote());
//			System.out.println(orderVO);	//溢位		

//update  
//			OrderVO orderVO = new OrderVO();
//			MemberVO memberVO = new MemberVO();
//			GuestVO guestVO = new GuestVO();
//			RoomVO roomVO = new RoomVO();
//			orderVO.setOrder_id(2);
//			memberVO.setMember_account("da");
//			orderVO.setMember_account(memberVO);
//			guestVO.setGuest_id(3);
//			orderVO.setGuest_id(guestVO);
//			orderVO.setRoom_type("Double");
//			roomVO.setRoom_id(301);
//			orderVO.setRoom_id(roomVO);
//			orderVO.setAdults(3);
//			orderVO.setTotal_payment(6666);
//			orderVO.setNote("加100床");
//			dao.update(orderVO);
//			System.out.println("修改的訂單編號：" + orderVO.getOrder_id());
			
//delete  //fac_detail會同時執行update，當有共同的order_id時，會變成orderid=null，失敗
//			dao.delete(6);
			
//selectAll
//			List<OrderVO> beans = dao.selectAll();
//			System.out.println(beans);
				
				//selectSpecific  失敗

				////				OrderVO orderVO = new OrderVO();
////				MemberVO memberVO=new MemberVO();
////				memberVO.setMember_account("da");
////				orderVO.setOrder_id(1);
////				orderVO.setMember_account(memberVO);
////				orderVO.setCheckin_date(java.sql.Date.valueOf("2016-01-23"));
//				String a = "1";
//				String b = "da";
////				String c = "2016-01-23";
//				List<OrderVO> beans =dao.selectSpecific(b);
//				System.out.println(beans);

//selectWhereNote
//				String note="加";
//				List<OrderVO> beans =dao.selectWhereNote(note);
//				System.out.println(beans);
				
				//selectWhereCheckin
				String date = "2016";
				List<OrderVO> beans =dao.selectWhereCheckin(date);
				System.out.println(beans);
				
				session.getTransaction().commit();
			} catch (RuntimeException ex) {
				session.getTransaction().rollback();
				throw ex;
			}
		}

}
