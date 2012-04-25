package dao.jpa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import dao.SpeakerDao;
import entity.Speaker;

public class JpaSpeakerDao implements SpeakerDao{

	private EntityManagerFactory emf;
	
	public JpaSpeakerDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public String MD5(String input){
		byte[] uniqueKey = input.getBytes();
		byte[] hash = null;
		try {
			hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
		} catch (NoSuchAlgorithmException e) {
			throw new Error("no MD5 support in this VM");
		}
		StringBuffer hashString = new StringBuffer();
		for ( int i = 0; i < hash.length; ++i ) {
			String hex = Integer.toHexString(hash[i]);
		if ( hex.length() == 1 ) {
			hashString.append('0');
			hashString.append(hex.charAt(hex.length()-1));
		} else {
			hashString.append(hex.substring(hex.length()-2));
		}
		}
		return hashString.toString(); 
	}

	@Override
	public Speaker addSpeaker(Speaker speaker) {
		// TODO Auto-generated method stub
		Speaker result = null;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(speaker);
			em.getTransaction().commit();
			result = speaker;
		} finally {
			if(em.getTransaction().isActive()) em.getTransaction().rollback();
			em.close();
		}
		return result;
	}

	@Override
	public List<Speaker> getAllSpeaker() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Speaker getSpeakerById(Long speakerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Speaker getSpeakerByMailPassword(String mail, String password) {
		// TODO Auto-generated method stub
				EntityManager em = emf.createEntityManager();
				try {
					Query query = em.createQuery("SELECT s FROM Speaker s WHERE s.email='"+mail+"' AND s.password='"+password+"'");
					return (Speaker) query.getSingleResult();
				} 
				catch(javax.persistence.NoResultException e){
					return null;
				}finally {
					em.close();
				}
	}
}
