package dao;

import java.util.List;

import entity.Mark;

public interface MarkDao {
	Mark addMark(Mark mark);
	Mark getMarkById(Long markId);
	List<Mark> getMarkByIntervention(Long interventionId);
	List<Mark> getAllMark();
	boolean addMarkFromJson(String str);
}
