package rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import dao.DaoFactory;
import dao.MarkDao;

import entity.Mark;

@Path("/mark")
public class MarkResource {
	@GET @Path("/one/{id}")
    public Mark handleGetRequest(@PathParam("id") String id) {
		MarkDao markDao = DaoFactory.getInstance().getMarkDao();
		return markDao.getMarkById(Long.valueOf(id));		
	}
	
	@GET @Path("/byintervention/{id}")
    public List<Mark> handleGetByInterventionRequest(@PathParam("id") String id) {
		MarkDao markDao = DaoFactory.getInstance().getMarkDao();
		return markDao.getMarkByIntervention(Long.valueOf(id));		
	}
	
	@GET @Path("/all")
    public List<Mark> handleGetAllRequest() {
		MarkDao markDao = DaoFactory.getInstance().getMarkDao();
		return markDao.getAllMark();		
	}
	@POST @Path("/post")
    public String handlePostRequest(String str) {
		MarkDao markDao = DaoFactory.getInstance().getMarkDao();
		markDao.addMarkFromJson(str);
		return "ok";		
	}
}
