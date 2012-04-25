package dao.jpa;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.DaoFactory;
import dao.InterventionDao;
import dao.MarkDao;
import entity.Intervention;
import entity.Mark;

public class JpaInterventionDao  implements InterventionDao{

	@PersistenceContext private EntityManagerFactory emf;
	@Resource private EntityTransaction myTrans; 

	public JpaInterventionDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public Intervention addIntervention(Intervention intervention) {
		// TODO Auto-generated method stub
		Intervention result = null;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(intervention);
			em.getTransaction().commit();
			result = intervention;
		} finally {
			if(em.getTransaction().isActive()) em.getTransaction().rollback();
			em.close();
		}
		return result;
	}

	@Override
	public Intervention getInterventionById(Long interventionId) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT i FROM Intervention i WHERE i.id="+String.valueOf(interventionId)+"");
			return (Intervention) query.getSingleResult();
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Intervention> getAllIntervention() {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT i FROM Intervention i");
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Intervention> getInterventionbyCampus(long campusId) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT i FROM Intervention i WHERE i.campus="+String.valueOf(campusId)+"");
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Intervention> getInterventionbySpeaker(long speakerId) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT i FROM Intervention i WHERE i.speaker="+String.valueOf(speakerId)+"");
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public void deleteIntervention(long id) {
		EntityManager em = emf.createEntityManager();
		try {
			myTrans =  em.getTransaction();
			myTrans.begin();
			Intervention intervention = getInterventionById(id);
			MarkDao markDao = DaoFactory.getInstance().getMarkDao();
			List<Mark> listMark = markDao.getMarkByIntervention(intervention.getId());
			for(int i=0;i<listMark.size();i++){
				em.remove(em.merge(listMark.get(i)));
			}
			em.remove(em.merge(intervention));
			myTrans.commit();
		} finally {
			em.close();
		}
	}
	
	@Override
	public void updateIntervention(Intervention myIntervention) {
		EntityManager em = emf.createEntityManager();
		try {
			myTrans =  em.getTransaction();
			myTrans.begin();
			em.joinTransaction();
			em.persist(em.merge(myIntervention));
			em.flush();
			myTrans.commit();
		} finally {
			em.close();
		}
	}

}
