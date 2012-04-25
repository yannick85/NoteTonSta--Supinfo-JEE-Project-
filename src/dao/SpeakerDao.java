package dao;

import java.util.List;

import entity.Speaker;

public interface SpeakerDao {
	Speaker addSpeaker(Speaker speaker);
	Speaker getSpeakerById(Long speakerId);
	Speaker getSpeakerByMailPassword(String mail,String password);
	List<Speaker> getAllSpeaker();
	String MD5(String input);
}
