package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CampusDao;
import dao.DaoFactory;
import dao.InterventionDao;
import entity.Campus;
import entity.Intervention;

/**
 * Servlet implementation class ModifyInterventionServlet
 */
//@WebServlet("/auth/updateintervention")
public class ModifyInterventionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyInterventionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action;
		InterventionDao interventionDao = DaoFactory.getInstance().getInterventionDao();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(request.getParameter("id") != null){
			Intervention thisIntervention = interventionDao.getInterventionById(Long.valueOf((String) request.getParameter("id")));
			request.setAttribute("interventionId", thisIntervention.getId());
			if( (String) request.getParameter("modintervention-subject") != null){
				String subject = (String) request.getParameter("modintervention-subject");
				String description = (String) request.getParameter("modintervention-desc");
				String dateToString = (String) request.getParameter("modintervention-to");
				String dateFromString = (String) request.getParameter("modintervention-from");
				String campus = (String) request.getParameter("modintervention-campus");
				
				Boolean valid = true;
				List<String> errorMsg = new ArrayList<String>();
				//Validation
				if(!(subject.matches("^.{3,50}$"))){
					valid=false;
					errorMsg.add("The subject must contains only letters and numbers , and must be between 3 and 50 characters.");
				}
				if(!(dateToString.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$"))){
					valid=false;
					errorMsg.add("The end date is not valid.");
				}
				if(!(dateFromString.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$"))){
					valid=false;
					errorMsg.add("The begin date is not valid.");
				}
				
				Long speakerId = (long) 0;
				if(request.getSession().getAttribute("speaker-id") == null) {
					valid=false;
					errorMsg.add("You are not logged.");
				}else{
					speakerId = (Long) request.getSession().getAttribute("speaker-id");
				}
				
				if(valid){
					Date dateFrom = null;
					Date dateTo=null;
					try {
						dateFrom = sdf.parse(dateFromString);
						dateTo = sdf.parse(dateToString);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					thisIntervention.setSubject(subject);
					thisIntervention.setDescription(description);
					thisIntervention.setBeginDate(dateTo);
					thisIntervention.setEndDate(dateFrom);
					thisIntervention.setCampus(Long.valueOf(campus));
					thisIntervention.setSpeaker(speakerId);
					interventionDao.updateIntervention(thisIntervention);
					action = "modintervention-conf";
					request.setAttribute("action", action);
				}else{
					CampusDao campusdao = DaoFactory.getInstance().getCampusDao();
					List<Campus> listCampus = campusdao.getAllCampus();
					request.setAttribute("listCampus", listCampus);
					request.setAttribute("oldSubject", subject);
					request.setAttribute("oldDescription", description);
					request.setAttribute("oldDateTo", dateToString);
					request.setAttribute("oldDateFrom", dateFromString);
					request.setAttribute("oldCampus", Long.valueOf(campus));
					action = "modintervention-error";
					request.setAttribute("action", action);
					request.setAttribute("errorMsg", errorMsg);
				}
			}else{
				CampusDao campusdao = DaoFactory.getInstance().getCampusDao();
				List<Campus> listCampus = campusdao.getAllCampus();
				request.setAttribute("listCampus", listCampus);
				action = "modintervention-form";
				request.setAttribute("action", action);
				
				request.setAttribute("oldSubject", thisIntervention.getSubject());
				request.setAttribute("oldDescription", thisIntervention.getDescription());
				request.setAttribute("oldDateTo", sdf.format(thisIntervention.getEndDate()));
				request.setAttribute("oldDateFrom", sdf.format(thisIntervention.getBeginDate()));
				request.setAttribute("oldCampus", thisIntervention.getCampus());
			}
		}else{
			action ="noid";
			request.setAttribute("action", action);
		}
		request.getRequestDispatcher("/WEB-INF/modifyintervention.jsp").forward(request, response);
	}

}
