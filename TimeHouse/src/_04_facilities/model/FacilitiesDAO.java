package _04_facilities.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.util.HibernateUtil;

public class FacilitiesDAO implements Facilities_InterfaceDAO {

	private SessionFactory sessionFactory;
	public FacilitiesDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if(sessionFactory!=null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

			Facilities_InterfaceDAO dao = new FacilitiesDAO();
			
			//selectAll_ok
			List<FacilitiesVO> beans = dao.selectAll();
			System.out.println("selectAll:" + beans+"/n");
			
			//update_ok
			FacilitiesVO up= dao.update(1, "basketball",new java.util.Date(),new java.util.Date(),100,null);
			System.out.println("update:" + up);
			
			//insert_ok
			FacilitiesVO bean=new FacilitiesVO();
			bean.setfName("tennis");
			bean.setDate(new java.util.Date());
			bean.setTime(new java.util.Date());
			bean.setCount(200);
			bean.setfPic(null);
//			dao.insert(bean);
			FacilitiesVO in=dao.insert(bean);
			System.out.println("insert:" + in);
			

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}

	}
	
	@Override
	public FacilitiesVO insert(FacilitiesVO bean) {
//		FacilitiesVO temp = (FacilitiesVO) this.getSession().get(FacilitiesVO.class, bean.getfId());
//		if (temp==null) {
//			this.getSession().save(bean);
//			return bean;
//		}
//		return null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();  
	    session.save(bean);  
	    return bean;
		
	}

	@Override
	public FacilitiesVO update(Integer fId, String fName, Date date, Date time, Integer count, byte[] fPic) {
		FacilitiesVO bean = (FacilitiesVO) getSession().get(FacilitiesVO.class, fId);
		if(bean!=null) {
			bean.setF_Id(fId);
			bean.setfName(fName);
			bean.setDate(date);
			bean.setTime(time);
			bean.setCount(count);
			bean.setfPic(fPic);
			return bean;
		}
		return null;
	}

	@Override
	public List<FacilitiesVO> selectAll() {
		return getSession().createQuery("from FacilitiesVO").list();
	}

}
