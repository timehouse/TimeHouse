package _04_facilities.model;

import java.util.Date;
import java.util.List;

public interface Facilities_InterfaceDAO {
	FacilitiesVO insert(FacilitiesVO bean);//新增設施(後台)
	FacilitiesVO update(Integer fId,String fName,Date date,Date time,Integer count,byte[] fPic);//更新設施(後台)
	List<FacilitiesVO> selectAll();//查詢所有設施(後台)
}
