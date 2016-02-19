package _06_member.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import _07_message.model.MessageVO;
import hibernate.util.HibernateUtil;

public class MemberVO implements java.io.Serializable{
	
	private String member_account;
	private String password;
	private String member_last_name;
	private String member_first_name;
	private String gender;
	private String id_number;
	private String credit_card;
	private java.util.Date birthday; 
	private String tel;
	private String phone_number;
	private String address;
	private String email;
	private java.util.Date reg_date;	
	private Integer limit;
	private Set<MessageVO> bean = new HashSet<MessageVO>();

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		MemberVO bean = (MemberVO)session.get(MemberVO.class, "da");
		System.out.println(bean.getMember_account());
		System.out.println(bean.getPassword());
		System.out.println(bean.getMember_last_name());
		System.out.println(bean.getMember_first_name());
		System.out.println(bean.getGender());
		System.out.println(bean.getId_number());
		System.out.println(bean.getCredit_card());
		System.out.println(bean.getBirthday());
		System.out.println(bean.getTel());
		System.out.println(bean.getPhone_number());
		System.out.println(bean.getAddress());
		System.out.println(bean.getEmail());
		System.out.println(bean.getReg_date());
		System.out.println(bean.getLimit());
		
		session.getTransaction().commit();
	}



	@Override
	public String toString() {
		return "MemberVO [member_account=" + member_account+ ", password=" + password
				+ ", member_last_name=" + member_last_name + ", member_first_name=" + member_first_name + ", gender="
				+ gender + ", id_number=" + id_number + ", credit_card=" + credit_card + ", birthday=" + birthday
				+ ", tel=" + tel + ", phone_number=" + phone_number + ", address=" + address + ", email=" + email
				+ ", reg_date=" + reg_date +","+"limit="+limit + "]";
	}

	

	public Set<MessageVO> getBean() {
		return bean;
	}


	public void setBean(Set<MessageVO> bean) {
		this.bean = bean;
	}
	public String getMember_account() {
		return member_account;
	}

	public void setMember_account(String member_account) {
		this.member_account = member_account;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMember_last_name() {
		return member_last_name;
	}

	public void setMember_last_name(String member_last_name) {
		this.member_last_name = member_last_name;
	}

	public String getMember_first_name() {
		return member_first_name;
	}

	public void setMember_first_name(String member_first_name) {
		this.member_first_name = member_first_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public String getCredit_card() {
		return credit_card;
	}

	public void setCredit_card(String credit_card) {
		this.credit_card = credit_card;
	}

	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public java.util.Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(java.util.Date reg_date) {
		this.reg_date = reg_date;
	}



	public Integer getLimit() {
		return limit;
	}



	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
