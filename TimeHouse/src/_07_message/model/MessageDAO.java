package _07_message.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import _06_member.model.MemberVO;
import _09_order.model.OrderVO;
import hibernate.util.HibernateUtil;

public class MessageDAO implements Message_InterfaceDAO {
	private SessionFactory sessionFactory;
	public MessageDAO() {
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
	public MessageVO findByPrimaryKey(Integer message_id) {
		MessageVO messageVO = null;
		messageVO = (MessageVO) getSession().get(MessageVO.class, message_id);
		return messageVO;
	}
	
	@Override
	public void insert(MessageVO messageVO) {
			getSession().saveOrUpdate(messageVO);
	}

	@Override
	public void update(MessageVO messageVO) {
			getSession().saveOrUpdate(messageVO);
	}

	@Override
	public void delete(Integer message_id) {	
			MessageVO messageVO = new MessageVO();
			messageVO.setMessage_id(message_id);;
			getSession().delete(messageVO);		
	}

	private static final String GET_ALL_STMT= "from MessageVO order by message_id desc";
	
	@Override
	public List<MessageVO> selectAll() {
		List<MessageVO> list = null;
			Query query = getSession().createQuery(GET_ALL_STMT);
			list = query.list();
		return list;
	}
	
	private static final String GET_MEMBER_STMT= "from MessageVO where member_account =? order by message_id desc";
	//查詢特定會員資訊，方法二
	@Override
	public List<MessageVO> selectByMember(MemberVO member_account) {
		List<MessageVO> list = null;
			Query query = getSession().createQuery(GET_MEMBER_STMT);
			query.setParameter(0, member_account);
			list = query.list();
		return list;
	}
	
	//查詢特定會員資訊
//	@Override
//	public List<MessageVO> selectByMember(MemberVO bean) {
//		List<MessageVO> list = null;
//			Criteria query = getSession().createCriteria(OrderVO.class);
//			query.add(Restrictions.like("member_account", bean.getMember_account()));
//			list = query.list();
//		return list;
//	}
	
private static final String GET_TYPE_STMT= "from MessageVO where type =? order by message_id ";
	
	@Override
	public List<MessageVO> selectByType(String type) {
		List<MessageVO> list = null;
			Query query = getSession().createQuery(GET_TYPE_STMT);
			query.setParameter(0, type);
			list = query.list();
		return list;
	}

	public static void main(String[] args) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Message_InterfaceDAO dao = new MessageDAO();
			
			try {
				session.beginTransaction(); 	

//select	
//			MessageVO bean = dao.findByPrimaryKey(1);
//			System.out.println(bean);
			
//insert  
//			MessageVO messageVO = new MessageVO();
//			MemberVO memberVO = new MemberVO();
//			memberVO.setMember_account("da");
//			messageVO.setMember_account(memberVO);
//			messageVO.setName("王大明");
//			messageVO.setType("客房服務詢問");
//			messageVO.setEmail("zzz@gmail.com");
//			messageVO.setMessage_content("安安你好");
//			messageVO.setMessage_date(new java.util.Date());
//			messageVO.setReply("我很好");
//			messageVO.setReply_date(new java.util.Date());
//
//			dao.insert(messageVO);
//			System.out.println(messageVO);			
 
//update
//			MessageVO messageVO = new MessageVO();
//			MemberVO memberVO = new MemberVO();	
//			messageVO.setMessage_id(2);
//			memberVO.setMember_account("da");
//			messageVO.setMember_account(memberVO);
//			messageVO.setName("王修改");
//			messageVO.setType("客房服務詢問");
//			messageVO.setEmail("uuu@gmail.com");
//			messageVO.setMessage_content("安安你好");
//			messageVO.setMessage_date(java.sql.Date.valueOf("2000-01-01"));
//			messageVO.setReply("不要亂");
//			messageVO.setReply_date(new java.util.Date());
//			dao.update(messageVO);
//			System.out.println(messageVO);
			
//delete
//			dao.delete(1);
			
//selectAll
//			List<MessageVO> beans = dao.selectAll();
//			System.out.println(beans);
				
//selectByMember
//				MemberVO memberVO = new MemberVO();
//				MessageVO messageVO = new MessageVO();
//				memberVO.setMember_account("da");
//				messageVO.setMember_account(memberVO);
//				List<MessageVO> beans = dao.selectByMember(messageVO.getMember_account());
//				System.out.println(beans);	
				
				session.getTransaction().commit();
			} catch (RuntimeException ex) {
				session.getTransaction().rollback();
				throw ex;
			}
		}

}
