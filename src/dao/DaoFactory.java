package dao;

import util.PersistenceManager;
import dao.jpa.JpaCampusDao;
import dao.jpa.JpaInterventionDao;
import dao.jpa.JpaMarkDao;
import dao.jpa.JpaSpeakerDao;

public final class DaoFactory {
	
	private static DaoFactory instance;
	
	private CampusDao campusDao;
	private SpeakerDao speakerDao;
	private InterventionDao interventionDao;
	private MarkDao markDao;
	
	public static DaoFactory getInstance() {
		if(instance == null) {
			instance = new DaoFactory();
		}
		return instance;
	}
	
	public CampusDao getCampusDao() {
		if(campusDao == null) {
			campusDao = new JpaCampusDao(PersistenceManager.getEntityManagerFactory());
		}
		return campusDao;
	}
	
	public SpeakerDao getSpeakerDao() {
		if(speakerDao == null) {
			speakerDao = new JpaSpeakerDao(PersistenceManager.getEntityManagerFactory());
		}
		return speakerDao;
	}
	
	public InterventionDao getInterventionDao() {
		if(interventionDao == null) {
			interventionDao = new JpaInterventionDao(PersistenceManager.getEntityManagerFactory());
		}
		return interventionDao;
	}
	
	public MarkDao getMarkDao() {
		if(markDao == null) {
			markDao = new JpaMarkDao(PersistenceManager.getEntityManagerFactory());
		}
		return markDao;
	}
}