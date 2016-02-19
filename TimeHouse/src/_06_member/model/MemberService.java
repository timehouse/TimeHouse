package _06_member.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.util.HibernateUtil;

public class MemberService {
	MemberDAO memberDAO = new MemberDAO();
	private SessionFactory sessionFactory ;		

	public MemberService(){
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	public Session getSession(){
		if(sessionFactory != null){
			return sessionFactory.getCurrentSession();			
		}
		return null;
	}
	
	public static void main(String []args){	
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();		
		
		MemberService service = new MemberService();
		
		MemberVO bean = new MemberVO();
//		bean.setMember_account("Bean");	
//		bean.setPassword("password");
//		bean.setMember_last_name("last name");
//		bean.setMember_first_name("first name");
//		bean.setGender("male");
//		bean.setId_number("idnumber");
//		bean.setCredit_card("132456798");
//		bean.setBirthday( new java.util.Date());
//		bean.setTel("56456484684498");
//		bean.setPhone_number("02-21578742");
//		bean.setAddress("fff");
//		bean.setReg_date(new java.util.Date());
//		MemberVO bean = service.login("da", "ds");
//		System.out.println(bean);				
//		service.limit(bean);		
//		service.update(bean);
		service.register(bean);
//		service.changePassword("da", "password", "ds");
		
		session.getTransaction().commit();
	}
	
	//註冊
	public MemberVO register(MemberVO bean){
		MemberVO temp= null;
		
		if(bean!=null){
			bean.setReg_date(new java.util.Date());
			bean.setLimit(0);
			temp = memberDAO.insert(bean);
			return temp;
		}
		return temp;
	}
	
	public MemberVO sendFile(String account){
		MemberVO bean = memberDAO.findByPrimaryKey(account);
		return bean;
	}
	
	//登入
	public String login(String account,String password){
		MemberVO bean = memberDAO.findByPrimaryKey(account);
		if(bean != null){
			if(bean.getLimit()==0){
				if(password!=null && password.trim().length()!=0){
					String pass = bean.getPassword();
					String temp = password;
					if(pass.equals(temp)){
						return "access";
					}
				}
			}
			if(bean.getLimit()==1){
				return "lock";
			}
		}
		return null;
	}
		
	//更新會員資料
	public boolean update(MemberVO bean){
		if(bean != null){
			memberDAO.update(bean);			
			return true;
		}
		return false;
	}
	
	//修改密碼
	public boolean changePassword(String account,String password,String newPassword,String confirmPassword){
		Session session = getSession();
//		String regex = "^.*(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
//		if(newPassword==null || !newPassword.matches(regex)) {
//			return false;
//		}
		
		if(!newPassword.equals(confirmPassword)){
			System.out.println(newPassword);
			System.out.println(confirmPassword);
			return false;
		}else{
			MemberVO bean = (MemberVO)session.get(MemberVO.class, account);
			if(bean!=null){
				bean.setPassword(newPassword);
				memberDAO.update(bean);
				return true;
			}	
		}
		return false;
	}
	
	//切換黑名單
	public boolean limit(MemberVO temp){
		MemberVO bean = memberDAO.findByPrimaryKey(temp.getMember_account());
		if(bean!=null){
			memberDAO.limit(bean);
			return true;
		}
		return false;
	}
}
