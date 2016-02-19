package _07_message.model;

import java.util.List;

import org.hibernate.Session;

import _06_member.model.MemberVO;

public interface Message_InterfaceDAO {

	Session getSession();

	MessageVO findByPrimaryKey(Integer message_id);

	void insert(MessageVO messageVO);

	void update(MessageVO messageVO);

	void delete(Integer message_id);

	List<MessageVO> selectAll();

	List<MessageVO> selectByMember(MemberVO memberVO);

	List<MessageVO> selectByType(String type);
}