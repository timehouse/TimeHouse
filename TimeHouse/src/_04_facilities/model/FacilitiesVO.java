package _04_facilities.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import _03_fac_detail.model.Fac_detailVO;
import hibernate.util.HibernateUtil;

public class FacilitiesVO implements Serializable{
	private Integer f_Id;
	private String fName;
	private Date date;
	private Date time;
	private Integer count;
	private byte[] fPic;
	
	private Set<Fac_detailVO> facDetails = new HashSet<Fac_detailVO>();
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		FacilitiesVO bean = (FacilitiesVO) session.get(FacilitiesVO.class, 1);
		System.out.println(bean);
		session.getTransaction().commit();
	}

	@Override
	public String toString() {
		return "FacilitiesVO [f_Id=" + f_Id + ", fName=" + fName + ", date=" + date + ", time=" + time + ", count="
				+ count + ", fPic=" + Arrays.toString(fPic) + "]";
	}
	
	public Integer getF_Id() {
		return f_Id;
	}

	public void setF_Id(Integer f_Id) {
		this.f_Id = f_Id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public byte[] getfPic() {
		return fPic;
	}

	public void setfPic(byte[] fPic) {
		this.fPic = fPic;
	}

	public Set<Fac_detailVO> getFacDetails() {
		return facDetails;
	}


	public void setFacDetails(Set<Fac_detailVO> facDetails) {
		this.facDetails = facDetails;
	}


	

}
