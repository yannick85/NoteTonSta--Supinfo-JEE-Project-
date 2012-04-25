package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import dao.DaoFactory;
import dao.SpeakerDao;

import entity.Speaker;

@Path("/speaker")
public class SpeakerResource {
	@GET @Path("/one/{id}")
    public Speaker handleGetRequest(@PathParam("id") String id) {
		SpeakerDao speakerDao = DaoFactory.getInstance().getSpeakerDao();
		return speakerDao.getSpeakerById(Long.valueOf(id));		
	}
}
