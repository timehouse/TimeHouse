package _08_notice.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class NoticeVO implements Serializable{
	private Integer noticeId;
	private String title;
	private Date startdate;
	private Date enddate;
	private String content;

public static void main(String[] args){
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	NoticeVO bean = (NoticeVO)session.get(NoticeVO.class, 1);
	System.out.println(bean);
	session.getTransaction().commit();
}
	
	@Override
public String toString() {
	return "NoticeVO [noticeId=" + noticeId + ", title=" + title + ", startdate=" + startdate + ", enddate=" + enddate
			+ ", content=" + content + "]";
}

	public Integer getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
