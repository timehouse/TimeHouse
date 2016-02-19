package _01_admin.model;

public interface Admin_InterfaceDAO {
	AdminVO select(int id);

	int delete(int id);

	AdminVO insert(AdminVO bean);

	void update(AdminVO bean);
}
