package _10_package.model;

import java.util.List;

public interface Package_InterfaceDAO {
	
	PackageVO select(int package_id);

	List<PackageVO> select();

	void update(PackageVO bean);
	
	PackageVO insert(PackageVO bean);
	
	int delete(int package_id);		
}
