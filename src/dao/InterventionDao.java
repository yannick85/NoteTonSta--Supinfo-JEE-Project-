package dao;

import java.util.List;

import entity.Intervention;

public interface InterventionDao {
	Intervention addIntervention(Intervention intervention);
	Intervention getInterventionById(Long interventionId);
	List<Intervention> getAllIntervention();
	List<Intervention> getInterventionbyCampus(long campusId);
	List<Intervention> getInterventionbySpeaker(long speakerId);
	void deleteIntervention(long id);
	void updateIntervention(Intervention myIntervention);
}
