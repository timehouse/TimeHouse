package _10_package.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import _13_roomtype.model.RoomTypeVO;
import hibernate.util.HibernateUtil;



public class PackageService {
	private PackageDAO PackageDao = new PackageDAO();

	public List<PackageVO> select() 
	{
		List<PackageVO> result = null;
		result = PackageDao.select(); 	
		return result;
	}
	
	public PackageVO select(int package_id) 
	{
		PackageVO result = null;
		result = PackageDao.select(package_id); 
		return result;
	}
	
	public void update(PackageVO bean) 
	{
		if(bean!=null) 
		{
		PackageDao.update(bean);
		}
	}
	
	public PackageVO insert(PackageVO bean) 
	{
		
		if (bean!=null) 
		{
			PackageDao.insert(bean);
			return bean;
		}
		return null;
	}
	
	public int delete(int package_id) 
	{
		PackageDao.delete(package_id);	

		return 1;
	}
	
	public static void main(String[] args) {
		
		try 
		{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			PackageDAO dao = new PackageDAO();
			
//			select
//			System.out.println(dao.select(1));			
			
//			List<PackageVO>
//			List<PackageVO> beans= dao.select();
//			System.out.println(beans);
			
//			insert
//			PackageVO bean = new PackageVO();
//			bean.setPackage_id(9);
//			bean.setName("小朋友");
//			RoomTypeVO roomTypeVO =(RoomTypeVO) session.get(RoomTypeVO.class, 1);
//			bean.setRoomtype(roomTypeVO);
//			bean.setPrice(55);
//			bean.setStart_date(new java.util.Date());
//			bean.setEnd_date(new java.util.Date());			
//			bean.setContext("真可愛");
//			dao.insert(bean);						
			
//			update
//			RoomTypeVO roomTypeVO =(RoomTypeVO) session.get(RoomTypeVO.class, 5);
//			System.out.println(roomTypeVO);			
//			PackageVO	bean = new PackageVO();
//			bean.setPackage_id(1);
//			bean.setName("小朋友");
//			bean.setRoomtype(roomTypeVO);
//			bean.setPrice(5);
//			bean.setStart_date(new java.util.Date());
//			bean.setEnd_date(new java.util.Date());
//			bean.setContext("很討厭");
//			bean.setPic(null);
//			dao.update(bean);
			
//			delete
//			dao.delete(9);
			
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

}
