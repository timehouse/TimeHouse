package _01_admin.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.util.HibernateUtil;

public class AdminService {
	AdminDAO adminDAO = new AdminDAO();
	private SessionFactory sessionFactory ;		

	public AdminService(){
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	public Session getSession(){
		if(sessionFactory != null){
			return sessionFactory.getCurrentSession();			
		}
		return null;
	}	

//	public static void main(String[] args) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		
//		AdminService service = new AdminService();
//		AdminVO bean = service.login(1, "qq");
//		System.out.println(bean);
//		
//		session.getTransaction().commit();		
//	}

	//登入系統
	public AdminVO login(int adminId , String password){
		AdminVO bean = adminDAO.select(adminId);
		
		if(bean != null){
			if(password!=null && password.trim().length()!=0){
				if(password.equals(bean.getPassword())){
					return bean;
				}
			}
			return null;
		}
		
		return null;
	}
	
	
	
}
