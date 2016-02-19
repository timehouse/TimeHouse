package _02_admin_level.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import _01_admin.model.AdminVO;
import hibernate.util.HibernateUtil;

public class Admin_LevelVO {

	private int level_num;
	private String level_name;
	private Set<AdminVO> bean = new HashSet<AdminVO>();

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Admin_LevelVO bean = (Admin_LevelVO)session.get(Admin_LevelVO.class, 1);
		System.out.println(bean);
		
		session.getTransaction().commit();
	}

	@Override
	public String toString() {
		return "Admin_LevelBean [level_num=" + level_num + ", level_name=" + level_name+"]";
	}


	public int getLevel_num() {
		return level_num;
	}


	public void setLevel_num(int level_num) {
		this.level_num = level_num;
	}


	public String getLevel_name() {
		return level_name;
	}


	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}


	public Set<AdminVO> getBean() {
		return bean;
	}


	public void setBean(Set<AdminVO> bean) {
		this.bean = bean;
	}

}
