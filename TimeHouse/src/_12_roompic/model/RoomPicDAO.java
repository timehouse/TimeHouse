package _12_roompic.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class RoomPicDAO implements RoomPicDAO_interface {
	private final String GET_ALL_STMT = "from RoomPicVO order by roomPic_id";

	public Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@Override
	public void insert(RoomPicVO roomPicVO) {
		Session session = this.getSession();
		session.saveOrUpdate(roomPicVO);
		// 測試用
		// session.beginTransaction();
		// session.saveOrUpdate(roomPicVO);
		// session.getTransaction().commit();
	}

	@Override
	public void update(RoomPicVO roomPicVO) {
		Session session = this.getSession();
		session.saveOrUpdate(roomPicVO);
	}

	@Override
	public void delete(Integer roomPic_id) {
		Session session = this.getSession();
		RoomPicVO roomPicVO = this.findByPrimaryKey(roomPic_id);
		session.delete(roomPicVO);
	}

	@Override
	public RoomPicVO findByPrimaryKey(Integer roomPic_id) {
		Session session = this.getSession();
		RoomPicVO roomPicVO = null;
		roomPicVO = (RoomPicVO) session.get(RoomPicVO.class, roomPic_id);
		return roomPicVO;
	}

	@Override
	public List<RoomPicVO> getAll() {
		Session session = this.getSession();
		List<RoomPicVO> list = null;
		Query query = session.createQuery(GET_ALL_STMT);
		list = query.list();
		// 避免session重複做Update
		session.clear();
		return list;
	}
}
