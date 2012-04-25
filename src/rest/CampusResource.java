package rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import dao.CampusDao;
import dao.DaoFactory;

import entity.Campus;

@Path("/campus")
public class CampusResource {

	@GET @Path("/one/{id}")
    public Campus handleGetRequest(@PathParam("id") String id) {
		CampusDao campusDao = DaoFactory.getInstance().getCampusDao();
		return campusDao.getCampusById(Long.valueOf(id));		
	}
	
	
	@GET @Path("/all")	
    public List<Campus> handleGetAllRequest() {
		CampusDao campusDao = DaoFactory.getInstance().getCampusDao();
		return campusDao.getAllCampus();		
	}
}
