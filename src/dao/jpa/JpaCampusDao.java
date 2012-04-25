package dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;


import dao.CampusDao;
import entity.Campus;

public class JpaCampusDao implements CampusDao{

	private EntityManagerFactory emf;

	public JpaCampusDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public Campus addCampus(Campus campus) {
		Campus result = null;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(campus);
			em.getTransaction().commit();
			result = campus;
		} finally {
			if(em.getTransaction().isActive()) em.getTransaction().rollback();
			em.close();
		}
		return result;
	}

	@Override
	public Campus getCampusById(Long categoryId) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT c FROM Campus c WHERE c.id="+categoryId+"");
			return (Campus) query.getSingleResult();
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Campus> getAllCampus() {
		EntityManager em = emf.createEntityManager();
		List<Campus> campusList = new ArrayList<Campus>();
		try {
			Query query = em.createQuery("SELECT c FROM Campus c");
			campusList = query.getResultList();
			if(campusList.size() == 0){
				Campus newCampus1 = new Campus();
				Campus newCampus2 = new Campus();
				newCampus1.setName("Nantes");
				newCampus2.setName("Paris");
				addCampus(newCampus1);
				addCampus(newCampus2);
				return getAllCampus();
			}else{
				return campusList;
			}
		}catch(javax.persistence.PersistenceException e){
			Campus newCampus1 = new Campus();
			Campus newCampus2 = new Campus();
			newCampus1.setName("Nantes");
			newCampus2.setName("Paris");
			addCampus(newCampus1);
			addCampus(newCampus2);
			return getAllCampus();
		} finally {
			em.close();
		}
	}
}
