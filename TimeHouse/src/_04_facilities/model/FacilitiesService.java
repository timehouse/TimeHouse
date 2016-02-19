package _04_facilities.model;

import java.util.List;

import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class FacilitiesService {
	FacilitiesDAO facilitiesDAO = new FacilitiesDAO();

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		try {
			FacilitiesService service = new FacilitiesService();
			FacilitiesVO bean = new FacilitiesVO();

			bean.setfName("tennis");
			bean.setDate(new java.util.Date());
			bean.setTime(new java.util.Date());
			bean.setCount(200);
			bean.setfPic(null);
			
			//insert
//			FacilitiesVO in = service.insert(bean);
//			System.out.println("insert:" + in);

			//selectAll
			List<FacilitiesVO> al = service.selectAll();
			System.out.println("selectAll:" + al);

			//update
//			bean.setF_id(2);
//			FacilitiesVO up = service.update(bean);
//			System.out.println("update:" + up);

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();

		}

	}

	// 新增設施
	public FacilitiesVO insert(FacilitiesVO bean) {
		if (bean != null) {
			return facilitiesDAO.insert(bean);
		}
		return null;
	}

	// 查看所有設施
	public List<FacilitiesVO> selectAll() {
		if (facilitiesDAO.selectAll() != null) {
			return facilitiesDAO.selectAll();
		}
		return null;
	}

	// 更新設施狀態
	public FacilitiesVO update(FacilitiesVO bean) {
		if (bean != null) {
			return facilitiesDAO.update(bean.getF_Id(), bean.getfName(), bean.getDate(), bean.getTime(),
					bean.getCount(), bean.getfPic());
		}
		return null;
	}
}
