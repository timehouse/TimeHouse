package _05_guest.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class GuestService {
	private GuestDAO GuestDao = new GuestDAO();
	
	public List<GuestVO> select() {
		List<GuestVO> result = null;
		
		result = GuestDao.select(); 
		
		return result;
	}
	
	public GuestVO select(int guest_id) 
	{
		GuestVO result = null;
		result = GuestDao.select(guest_id); 
		return result;
	}
	
	public void update(GuestVO bean) 
	{
		
	if(bean!=null) {
		GuestDao.update(bean);
	}
	
	}
	
	public GuestVO insert(GuestVO bean) 
	{
		
		if (bean!=null) 
		{
			GuestDao.insert(bean);
			return bean;
		}
		return null;
	}
	
	public int delete(int guest_id) 
	{
		GuestDao.delete(guest_id);	

		return 1;
	}
	
	
	public static void main(String[] args) {
		try
		{
			
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GuestDAO dao = new GuestDAO();
		
//		select
//		System.out.println(dao.select(1));			
		
//		List<GuestVO> beans= dao.select();
//		System.out.println(beans);
		
//		insert
//		GuestVO bean = new GuestVO();
//		bean.setGuest_id(10);
//		bean.setGuest_last_name("sakin");
//		bean.setGuest_first_name("li");
//		bean.setGender("321");
//		bean.setId_number("f123456789");
//		bean.setTel("0935600997");;
//		bean.setPhone_number("0935600997");
//		bean.setEmail("3345678");
//		dao.insert(bean);	
								
		
//		update	
//		GuestVO	bean = new GuestVO();
//		bean.setGuest_id(1);
//		bean.setGuest_last_name("sakin");
//		bean.setGuest_first_name("li");
//		bean.setGender("哇哈哈");
//		bean.setId_number("f123456789");
//		bean.setTel("0935600997");
//		bean.setPhone_number("0935600997");
//		bean.setEmail("3345678");
//		dao.update(bean);
		
//		delete
//		dao.delete(9);
		session.getTransaction().commit();
		
	}
	
	catch (HibernateException e) 
	{
		e.printStackTrace();
	}


	}

}
