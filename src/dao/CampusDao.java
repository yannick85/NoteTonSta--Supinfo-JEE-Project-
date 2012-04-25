package dao;

import java.util.List;

import entity.Campus;

public interface CampusDao {
	
	Campus addCampus(Campus campus);
	Campus getCampusById(Long campusId);
	List<Campus> getAllCampus();
}
