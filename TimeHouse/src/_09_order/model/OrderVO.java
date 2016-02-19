package _09_order.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import _03_fac_detail.model.Fac_detailVO;
import _05_guest.model.GuestVO;
import _06_member.model.MemberVO;
import _11_room.model.RoomVO;
import hibernate.util.HibernateUtil;

public class OrderVO implements java.io.Serializable{
	private Integer order_id;
	private MemberVO member_account;
	private GuestVO guest_id;
	private String room_type;
	private RoomVO room_id;
	private java.util.Date checkin_date;
	private java.util.Date checkout_date;
	private Integer adults;
	private Integer children;
	private Integer total_payment;
	private String note;
	private Integer transaction_condition;
	private java.util.Date book_date;
	
	private Set<Fac_detailVO>bean=new HashSet<Fac_detailVO>();	
	
	public static void main(String[] args) {
	 	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	try {	
		session.beginTransaction();
		OrderVO bean = (OrderVO) session.get(OrderVO.class, 1);
		System.out.println(bean);
		session.getTransaction().commit();
	}finally{
		HibernateUtil.getSessionFactory().close();
	}

	}

	@Override
	public String toString() {
		return "OrderVO [order_id=" + order_id + ", member_account=" + member_account.getMember_account() + ", guest_id=" + guest_id.hashCode()
				+ ", room_type=" + room_type + ", room_id=" + room_id + ", checkin_date=" + checkin_date
				+ ", checkout_date=" + checkout_date + ", adults=" + adults + ", children=" + children
				+ ", total_payment=" + total_payment + ", note=" + note + ", transaction_condition="
				+ transaction_condition + ", book_date=" + book_date + ", bean=" + bean.hashCode() + "]";
	}

	public Integer getOrder_id() {
		return order_id;
	}


	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}


	public MemberVO getMember_account() {
		return member_account;
	}


	public void setMember_account(MemberVO member_account) {
		this.member_account = member_account;
	}


	public GuestVO getGuest_id() {
		return guest_id;
	}


	public void setGuest_id(GuestVO guest_id) {
		this.guest_id = guest_id;
	}


	public String getRoom_type() {
		return room_type;
	}


	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}


	public RoomVO getRoom_id() {
		return room_id;
	}


	public void setRoom_id(RoomVO room_id) {
		this.room_id = room_id;
	}


	public java.util.Date getCheckin_date() {
		return checkin_date;
	}


	public void setCheckin_date(java.util.Date checkin_date) {
		this.checkin_date = checkin_date;
	}


	public java.util.Date getCheckout_date() {
		return checkout_date;
	}


	public void setCheckout_date(java.util.Date checkout_date) {
		this.checkout_date = checkout_date;
	}


	public Integer getAdults() {
		return adults;
	}


	public void setAdults(Integer adults) {
		this.adults = adults;
	}


	public Integer getChildren() {
		return children;
	}


	public void setChildren(Integer children) {
		this.children = children;
	}


	public Integer getTotal_payment() {
		return total_payment;
	}


	public void setTotal_payment(Integer total_payment) {
		this.total_payment = total_payment;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public Integer getTransaction_condition() {
		return transaction_condition;
	}


	public void setTransaction_condition(Integer transaction_condition) {
		this.transaction_condition = transaction_condition;
	}


	public java.util.Date getBook_date() {
		return book_date;
	}


	public void setBook_date(java.util.Date book_date) {
		this.book_date = book_date;
	}


	public Set<Fac_detailVO> getBean() {
		return bean;
	}


	public void setBean(Set<Fac_detailVO> bean) {
		this.bean = bean;
	}
	

}
