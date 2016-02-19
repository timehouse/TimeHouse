package _01_admin.model;

import java.util.Arrays;

import org.hibernate.Session;

import _02_admin_level.model.Admin_LevelVO;
import hibernate.util.HibernateUtil;

public class AdminVO {

	private int admin_id;
	private String admin_account;
	private String password;
	private String admin_last_name;
	private String admin_first_name;
	private byte[] admin_pic;
	private Admin_LevelVO level_num;

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		AdminVO bean = (AdminVO)session.get(AdminVO.class, 1);
		System.out.println(bean);
		
		session.getTransaction().commit();
		
	}

	@Override
	public String toString() {
		return "AdminBean [admin_id=" + admin_id + ", admin_account=" + admin_account + ", password=" + password
				+ ", admin_last_name=" + admin_last_name + ", admin_first_name=" + admin_first_name + ", admin_pic="
				+ Arrays.toString(admin_pic) + "]";
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_account() {
		return admin_account;
	}

	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdmin_last_name() {
		return admin_last_name;
	}

	public void setAdmin_last_name(String admin_last_name) {
		this.admin_last_name = admin_last_name;
	}

	public String getAdmin_first_name() {
		return admin_first_name;
	}

	public void setAdmin_first_name(String admin_first_name) {
		this.admin_first_name = admin_first_name;
	}

	public byte[] getAdmin_pic() {
		return admin_pic;
	}

	public void setAdmin_pic(byte[] admin_pic) {
		this.admin_pic = admin_pic;
	}

	public Admin_LevelVO getLevel_num() {
		return level_num;
	}

	public void setLevel_num(Admin_LevelVO level_num) {
		this.level_num = level_num;
	}

}
