package _08_notice.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class NoticeService {
	private Notice_InterfaceDAO noticeDao = new NoticeDAO();
	//查看所有公告
		public List<NoticeVO> select() {
			List<NoticeVO> result = null;
//			if(bean!=null && bean.getNoticeId()!=0) {
//			NoticeVO temp = noticeDao.select(bean.getNoticeId());
//			if(temp!=null) {
				result = new ArrayList<NoticeVO>();
//				result.add(temp);
//			}
//			} else {
			result = noticeDao.selectall(); 
//			}
			return result;
			}
	
	//新增公告
			public NoticeVO insert(NoticeVO bean) {
				//NoticeVO result = null;
				if(bean!=null) {
					//result = noticeDao.insert(bean);
					return noticeDao.insert(bean);
				}
				//return result;
				return null;
			}
	
	//修改公告
			public NoticeVO update(NoticeVO bean) {
				if(bean!=null) {
					return noticeDao.update(bean.getNoticeId(), bean.getTitle(), bean.getStartdate(), bean.getEnddate(), bean.getContent());
				}
				return null;
			}

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		try {
			
			NoticeService service = new NoticeService();
			NoticeVO bean = new NoticeVO();
	
			
//			bean.setTitle("春節優惠");
//			bean.setStartdate(new java.util.Date());
//			bean.setEnddate(new java.util.Date());
//			bean.setContent("年菜外帶，每桌贈'開運紅包'一份 (年菜訂單截止日至1/30止)");
			
			
	//insert	
//			NoticeVO in = service.insert(bean);
//			System.out.println("insert:"+in);
	//selectAll		
			List<NoticeVO> beans = service.select();
			System.out.println("selectAll:"+beans);
	//update
//			bean.setNoticeId(5);
//			NoticeVO up = service.update(bean);
//			System.out.println("update:"+up);
//			
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			session.getTransaction().rollback();
			e.printStackTrace();
		}

	}

}
