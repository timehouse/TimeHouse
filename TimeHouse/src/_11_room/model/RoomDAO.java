package _11_room.model;

import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class RoomDAO implements RoomDAO_interface {
	private String GET_ALL_STMT = "from RoomVO order by room_id";

//	public static void main(String[] args) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		RoomDAO roomDAO = new RoomDAO();
//		List<RoomVO> list = roomDAO.getAll();
//		System.out.println(list);
//		 RoomVO packageBean=(RoomVO)session.get(RoomVO.class,101 );
//		session.getTransaction().commit();
//	}

	public Session getSession(){
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@Override
	public void insert(RoomVO roomVO) {
		Session session = this.getSession();
		session.saveOrUpdate(roomVO);
	}

	@Override
	public void update(RoomVO roomVO) {
		Session session = this.getSession();
		session.saveOrUpdate(roomVO);
	}

	@Override
	public void delete(Integer room_id) {
		Session session = this.getSession();
		RoomVO roomVO = this.findByPrimaryKey(room_id);
		session.delete(roomVO);
	}

	@Override
	public RoomVO findByPrimaryKey(Integer room_id) {
		Session session = this.getSession();
		RoomVO roomVO = null;
		roomVO = (RoomVO) session.get(RoomVO.class, room_id);
		return roomVO;
	}

	@Override
	public List<RoomVO> getAll() {
		Session session = this.getSession();
		List<RoomVO> list = null;
		Query query = session.createQuery(GET_ALL_STMT);
		list = query.list();
		session.clear();
		//System.out.println(session);
		return list;
	}
	
}
