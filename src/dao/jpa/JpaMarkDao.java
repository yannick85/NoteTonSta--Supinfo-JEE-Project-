package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import dao.MarkDao;
import entity.Mark;

public class JpaMarkDao  implements MarkDao{

	private EntityManagerFactory emf;

	public JpaMarkDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public Mark addMark(Mark mark) {
		// TODO Auto-generated method stub
		Mark result = null;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(mark);
			em.getTransaction().commit();
			result = mark;
		} finally {
			if(em.getTransaction().isActive()) em.getTransaction().rollback();
			em.close();
		}
		return result;
	}

	@Override
	public Mark getMarkById(Long markId) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT m FROM Mark m WHERE m.id ="+markId+"");
			return (Mark) query.getSingleResult();
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mark> getAllMark() {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT m FROM Mark m");
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mark> getMarkByIntervention(Long interventionId) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT m FROM Mark m WHERE m.interventionId ="+interventionId+"");
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public boolean addMarkFromJson(String jsonStr){
		Mark mark = new Mark();
		try {
			JSONObject jsonMark = new JSONObject(jsonStr);
			mark.setIdBooster(Integer.valueOf(jsonMark.getString("idBooster")));
			mark.setInterventionId(Long.valueOf(jsonMark.getString("interventionId")));
			mark.setComment(jsonMark.getString("comment"));
			mark.setSlideContent(Integer.valueOf(jsonMark.getString("slideContent")));
			mark.setSlideExample(Integer.valueOf(jsonMark.getString("slideExample")));
			mark.setSlideFormat(Integer.valueOf(jsonMark.getString("slideFormat")));
			mark.setSpeakerAnswers(Integer.valueOf(jsonMark.getString("speakerAnswers")));
			mark.setSpeakerKnowledge(Integer.valueOf(jsonMark.getString("speakerKnowledge")));
			mark.setSpeakerTeaching(Integer.valueOf(jsonMark.getString("speakerTeaching")));
			addMark(mark);
		} catch (NumberFormatException e) {
			return false;
		} catch (JSONException e) {
			return false;
		}
		return true;
	}
}
