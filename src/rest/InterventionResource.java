package rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import dao.DaoFactory;
import dao.InterventionDao;

import entity.Intervention;

@Path("/intervention")
public class InterventionResource {
	@GET @Path("/one/{id}")
    public Intervention handleGetRequest(@PathParam("id") String id) {
		InterventionDao interventionDao = DaoFactory.getInstance().getInterventionDao();
		return interventionDao.getInterventionById(Long.valueOf(id));		
	}
	
	@GET @Path("/all")	
    public List<Intervention> handleGetAllRequest() {
		InterventionDao interventionDao = DaoFactory.getInstance().getInterventionDao();
		return interventionDao.getAllIntervention();		
	}
	
	@GET @Path("/bycampus/{campusid}")	
    public List<Intervention> handleGetByCampusRequest(@PathParam("campusid") String campusId) {
		InterventionDao interventionDao = DaoFactory.getInstance().getInterventionDao();
		return interventionDao.getInterventionbyCampus(Long.valueOf(campusId));		
	}
}
