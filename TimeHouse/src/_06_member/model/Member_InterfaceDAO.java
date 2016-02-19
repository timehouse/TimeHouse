package _06_member.model;

import java.util.List;

public interface Member_InterfaceDAO {	
	int delete(String mc);
	
	MemberVO insert(MemberVO bean);				
	
	boolean update (MemberVO bean);

	MemberVO findByPrimaryKey(String mc);

	List<MemberVO> selectAll();	
	
}