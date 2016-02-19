package _05_guest.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.util.HibernateUtil;

public class GuestDAO implements Guest_InterfaceDAO{
	
	private SessionFactory sessionFactory;
	public GuestDAO() 
	{
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	public Session getSession() 
	{
		if(sessionFactory!=null) 
		{
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	public static void main(String[] args) 
	{
		
		try
		{
			
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GuestDAO dao = new GuestDAO();
		
//		select皜祈岫
//		System.out.println(dao.select(1));			
		
//		List<GuestVO>皜祈岫
//		List<GuestVO> beans= dao.select();
//		System.out.println(beans);
		
//		皜祈岫insert
//		GuestVO bean = new GuestVO();
//		bean.setGuest_id(9);
//		bean.setGuest_last_name("sakin");
//		bean.setGuest_first_name("li");
//		bean.setGender("�");
//		bean.setId_number("f123456789");
//		bean.setTel("0935600997");
//		bean.setPhone_number("0935600997");
//		bean.setEmail("3345678");
//		dao.insert(bean);	
								
		
//		皜祈岫update
//		
//		GuestVO	bean = new GuestVO();
//		bean.setGuest_id(1);
//		bean.setGuest_last_name("sakin");
//		bean.setGuest_first_name("li");
//		bean.setGender("�");
//		bean.setId_number("f123456789");
//		bean.setTel("0935600997");
//		bean.setPhone_number("0935600997");
//		bean.setEmail("3345678");
//		dao.update(bean);
		
//		皜祈岫delete
//		dao.delete(7);
		session.getTransaction().commit();
		
	}
	
	catch (HibernateException e) 
	{
		e.printStackTrace();
	}

		
	}	

	
	@Override
	public GuestVO select(int guest_id) 
	{
		return (GuestVO) getSession().get(GuestVO.class, guest_id);
	}

	
	@Override
	public List<GuestVO> select() 
	{
		return getSession().createQuery("from GuestVO").list();
	}

	
	@Override
	public void update(GuestVO bean) 
	{
	getSession().update(bean);		
	}

	
	@Override
	public GuestVO insert(GuestVO bean) 
	{
		GuestVO temp = (GuestVO) this.getSession().get(GuestVO.class, bean.getGuest_id());
		
		if (temp==null) 
		{
			this.getSession().save(bean);
			return bean;
		}
		return null;
	}

	@Override
	public int delete(int guest_id) 
	{
		GuestVO bean = (GuestVO) getSession().get(GuestVO.class, guest_id);
		
		if(bean!=null) 
		{
			getSession().delete(bean);
			return 1;
		}
		return 0;
	}

}
