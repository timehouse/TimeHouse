package _08_notice.model;

import java.util.Date;
import java.util.List;

public interface Notice_InterfaceDAO {
	public NoticeVO select (Integer noticeId);
	public List<NoticeVO> selectall();
	public NoticeVO insert (NoticeVO bean);
	public NoticeVO update (Integer noticeId, String title, Date startdate, Date enddate, String content);
	
}
