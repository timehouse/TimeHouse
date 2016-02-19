package _05_guest.model;

import java.util.List;

public interface Guest_InterfaceDAO {

	GuestVO select(int guest_id);

	List<GuestVO> select();

	void update(GuestVO bean);

	GuestVO insert(GuestVO bean);

	int delete(int guest_id);
	
	
	
}
