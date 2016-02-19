package _06_member.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.util.HibernateUtil;

public class MemberDAO implements Member_InterfaceDAO{

	private SessionFactory sessionFactory ;		

	public MemberDAO(){
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
		
		MemberDAO dao = new MemberDAO();
//		System.out.println(dao.findByPrimaryKey("dave"));
//		System.out.println(dao.selectAll());
		
		MemberVO bean = new MemberVO();
//		bean = dao.findByPrimaryKey("John");
//		System.out.println(bean.getLimit());
//		bean.setMember_account("da");
		bean.setMember_account("dave");	
		bean.setPassword("sdfdsf");
		bean.setMember_last_name("last name");
		bean.setMember_first_name("first name");
		bean.setGender("male");
		bean.setId_number("idnumber");
		bean.setCredit_card("132456798");
		bean.setBirthday( new java.util.Date());
		bean.setTel("56456484684498");
		bean.setPhone_number("02-21578742");
		bean.setAddress("Taiwan");
		bean.setEmail("jfudsljeklfjsl");
		bean.setReg_date(new java.util.Date());
		bean.setLimit(0);
//		dao.insert(bean);		
//		dao.update(bean);
//		dao.delete("Bean");		
//		System.out.println(bean);
//		bean.setMember_account("Dave");
//		dao.limit(bean);
//		System.out.println(dao.findByPrimaryKey("da"));
		session.getTransaction().commit();						
	}	
	@Override
	public MemberVO findByPrimaryKey(String mc) {
		return (MemberVO)getSession().get(MemberVO.class, mc);		
	}
	
	public int delete(String mc) {	
		Session session = getSession();		
		MemberVO bean = (MemberVO)session.get(MemberVO.class, mc);		
		if(bean!=null){
			session.delete(bean);
			return 1;
		}
		return 0;
	}

	@Override
	public MemberVO insert(MemberVO bean) {
		Session session = getSession();
		MemberVO vo = (MemberVO)session.get(MemberVO.class, bean.getMember_account());
		if(vo==null){
			session.save(bean);
			return bean;
		}
		return null;
	}

	@Override
	public boolean update(MemberVO bean) {
		Session session = getSession();
		MemberVO vo = (MemberVO)session.get(MemberVO.class, bean.getMember_account());
		if(vo!=null){
			vo.setMember_account(bean.getMember_account());
			vo.setPassword(bean.getPassword());
			vo.setMember_last_name(bean.getMember_last_name());
			vo.setMember_first_name(bean.getMember_first_name());
			vo.setGender(bean.getGender());
			vo.setId_number(bean.getId_number());
			vo.setCredit_card(bean.getCredit_card());
			vo.setBirthday(bean.getBirthday());
			vo.setTel(bean.getTel());
			vo.setPhone_number(bean.getPhone_number());
			vo.setAddress(bean.getAddress());
			vo.setEmail(bean.getEmail());
			return true;
		}				
		return false;
	}

	public void limit(MemberVO bean){
		Session session = getSession();
		MemberVO vo = (MemberVO)session.get(MemberVO.class, bean.getMember_account());
		if (vo.getLimit() == 0) {
			vo.setLimit(1);
			session.update(vo);
		} else if (vo.getLimit() == 1) {
			vo.setLimit(0);
			session.update(vo);
		}
	}
	
	@Override
	public List<MemberVO> selectAll() {		
		return getSession().createQuery("from MemberVO").list();
	}
	
}
