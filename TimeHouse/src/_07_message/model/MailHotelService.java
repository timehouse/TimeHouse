package _07_message.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import _06_member.model.MemberVO;
import hibernate.util.HibernateUtil;

public class MailHotelService {
	private Message_InterfaceDAO msgDao = new MessageDAO();

	// 前台，insert聯絡飯店的資料
	public MessageVO insert(MessageVO bean) {
		MessageVO result = null;
		if (bean != null) {
			msgDao.insert(bean);
			result = bean;
		}
		return result;
	}
	
	//前台，獲取同一會員的訊息
	public List<MessageVO> selectMember(MessageVO memberVO){
		List<MessageVO> result = null;
		if(memberVO!=null){
			result = msgDao.selectByMember(memberVO.getMember_account());
			System.out.println("service "+result);
		}
		return result;
	}

	// 後台，獲取全部聯絡飯店的訊息  //有空再改不需傳入參數
	public List<MessageVO> select(MessageVO bean) {
		List<MessageVO> result = null;
		result = msgDao.selectAll();
		return result;
	}
	
	//後台，獲取單一訊息
	public MessageVO selectId(MessageVO bean) {
		MessageVO result = null;
		if (bean != null && bean.getMessage_id() != 0) {
			result = msgDao.findByPrimaryKey(bean.getMessage_id());
		}
		return result;
	}

	// 後台，回應訊息
	public MessageVO reply(MessageVO bean) {
		MessageVO result = null;
		System.out.println(bean.getMessage_id());
		msgDao.findByPrimaryKey(bean.getMessage_id());
		if (bean != null) {
			msgDao.update(bean);
			result = bean;
		}
		return result;
	}
	
	//後台，根據分類產生資料表
		public List<MessageVO> selectType(String type){
			List<MessageVO> result = null;
			if(type!=null){
				result = msgDao.selectByType(type);
			}
			return result;
		}
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			MailHotelService msgService = new MailHotelService();
			MessageVO bean = new MessageVO();
			
			//測試insert
//			bean.setType("客房服務");
//			bean.setName("Mary");
//			bean.setEmail("aaa@yahoo.com.tw");
//			bean.setMessage_content("你好");
//			bean = msgService.insert(bean);
//			System.out.println(bean);
			
			//測試獲取單一會員
//			MemberVO memberVO = new MemberVO();
//			memberVO.setMember_account("da");
//			bean.setMember_account(memberVO);
//			List<MessageVO> result = msgService.selectMember(bean);
//			System.out.println("test  "+msgService.selectMember(bean));
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

}
