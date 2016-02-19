	package _07_message.model;

import org.hibernate.Session;

import _06_member.model.MemberVO;
import hibernate.util.HibernateUtil;

public class MessageVO implements java.io.Serializable {
	private Integer message_id;
	private MemberVO member_account;
	private String name;
	private String type;
	private String email;
	private String message_content;
	private java.util.Date message_date;
	private String reply;
	private java.util.Date reply_date;
	

	public static void main(String[] args) {
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {	
			session.beginTransaction();
			MessageVO bean = (MessageVO) session.get(MessageVO.class, 1);
			System.out.println(bean);
			session.getTransaction().commit();
		}finally{
			HibernateUtil.getSessionFactory().close();
		}

	}
	
	
	@Override
	public String toString() {
		return "MessageVO [message_id=" + message_id + ", member_account=" + member_account + ", name=" + name
				+ ", type=" + type + ", email=" + email + ", message_content=" + message_content + ", message_date="
				+ message_date + ", reply=" + reply + ", reply_date=" + reply_date + "]";
	}

	public Integer getMessage_id() {
		return message_id;
	}


	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}


	public MemberVO getMember_account() {
		return member_account;
	}


	public void setMember_account(MemberVO member_account) {
		this.member_account = member_account;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMessage_content() {
		return message_content;
	}


	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}


	public java.util.Date getMessage_date() {
		return message_date;
	}


	public void setMessage_date(java.util.Date message_date) {
		this.message_date = message_date;
	}


	public String getReply() {
		return reply;
	}


	public void setReply(String reply) {
		this.reply = reply;
	}


	public java.util.Date getReply_date() {
		return reply_date;
	}


	public void setReply_date(java.util.Date reply_date) {
		this.reply_date = reply_date;
	}


	
}
