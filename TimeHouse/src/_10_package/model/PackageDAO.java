package _10_package.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.util.HibernateUtil;


public class PackageDAO implements Package_InterfaceDAO{
	private SessionFactory sessionFactory;
	public PackageDAO() 
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
			PackageDAO dao = new PackageDAO();
			
//			select皜祈岫
			System.out.println(dao.select(1));			
			
//			List<PackageVO>皜祈岫
//			List<PackageVO> beans= dao.select();
//			System.out.println(beans);
			
//			皜祈岫insert
//			PackageVO bean = new PackageVO();
//			bean.setPackage_id(6);
//			bean.setName("������");
//			RoomTypeVO roomTypeVO =(RoomTypeVO) session.get(RoomTypeVO.class, 30);
//			bean.setRoomtype(roomTypeVO);
//			bean.setPrice(55);
//			bean.setStart_date(new java.util.Date());
//			bean.setEnd_date(new java.util.Date());			
//			bean.setContext("雿末���");
//			bean.setPic(pic);
//			dao.insert(bean);						
			
//			皜祈岫update
//			RoomTypeVO roomTypeVO =(RoomTypeVO) session.get(RoomTypeVO.class, 5);
//			System.out.println(roomTypeVO);			
//			PackageVO	bean = new PackageVO();
//			bean.setPackage_id(1);
//			bean.setName("��停�����");
//			bean.setRoomtype(roomTypeVO);
//			bean.setPrice(5);
//			bean.setStart_date(new java.util.Date());
//			bean.setEnd_date(new java.util.Date());
//			bean.setContext("雿末���");
//			bean.setPic(null);
//			dao.update(bean);
			
//			皜祈岫delete
//			dao.delete(5);
			
			session.getTransaction().commit();
			
		}
		
		catch (HibernateException e) 
		{
			e.printStackTrace();
		}
		
		finally 
		{
			HibernateUtil.closeSessionFactory();
		}
	}
	
	
	
	
	
	@Override
	public PackageVO select(int package_id) 
	{
		return (PackageVO) getSession().get(PackageVO.class, package_id);
	}
	
	@Override
	public List<PackageVO> select() 
	{
		return getSession().createQuery("from PackageVO").list();
	}
	
	
	
	@Override
	public void update(PackageVO bean) 
	{	
		getSession().update(bean);	
	}
	
	
	
	@Override
	public PackageVO insert(PackageVO bean) 
	{
	
		PackageVO temp = (PackageVO) this.getSession().get(PackageVO.class, bean.getPackage_id());
		if (temp==null) 
		{
			this.getSession().save(bean);
			return bean;
		}
		return null;
	}
	
	
	@Override
	public int delete(int package_id) 
	{
		PackageVO bean = (PackageVO) getSession().get(PackageVO.class, package_id);
		if(bean!=null) 
		{
			getSession().delete(bean);
			return 1;
		}		
		
		return 0;
	}
	
	
}
