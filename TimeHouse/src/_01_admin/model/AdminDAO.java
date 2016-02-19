package _01_admin.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _02_admin_level.model.Admin_LevelVO;
import hibernate.util.HibernateUtil;

public class AdminDAO implements Admin_InterfaceDAO{
	private SessionFactory sessionFactory ;		

	public static void main(String[] args) {		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		AdminDAO dao = new AdminDAO();
		System.out.println(dao.select(1));
		
		AdminVO bean = new AdminVO();
		Admin_LevelVO level = new Admin_LevelVO();
		level.setLevel_num(6);				
		bean.setAdmin_id(7);
		bean.setAdmin_account("John");
		
//		dao.update(bean);
		dao.insert(bean);
		
		session.getTransaction().commit();
	}

	public AdminDAO(){
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}

	@Override
	public AdminVO select(int id) {
		return (AdminVO)getSession().get(AdminVO.class, id);
	}

	@Override
	public int delete(int id) {
		Session session = getSession();		
		AdminVO bean = (AdminVO)session.get(AdminVO.class, id);
		if(bean!=null){
			session.delete(bean);
			return 1;
		}		
		return 0;
	}

	@Override
	public AdminVO insert(AdminVO bean) {
		Session session = getSession();
		AdminVO temp = (AdminVO)session.get(AdminVO.class, bean.getAdmin_id());
		if(temp==null){
			session.save(bean);
			return bean;
		}					
		return null;
	}

	@Override
	public void update(AdminVO bean) {
		getSession().update(bean);
	}



}
