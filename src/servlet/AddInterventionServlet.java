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
 * Servlet implementation class AddInterventionServlet
 */
//@WebServlet("/auth/addintervention")
public class AddInterventionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddInterventionServlet() {
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
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action;
		if( (String) request.getParameter("intervention-subject") != null){
			String subject = (String) request.getParameter("intervention-subject");
			String description = (String) request.getParameter("intervention-desc");
			String dateToString = (String) request.getParameter("intervention-to");
			String dateFromString = (String) request.getParameter("intervention-from");
			
			String campus = (String) request.getParameter("intervention-campus");
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
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dateFrom = null;
				Date dateTo=null;
				try {
					dateFrom = sdf.parse(dateFromString);
					dateTo = sdf.parse(dateToString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intervention newIntervention = new Intervention();
				InterventionDao interventionDao = DaoFactory.getInstance().getInterventionDao();

				newIntervention.setSubject(subject);
				newIntervention.setDescription(description);
				newIntervention.setBeginDate(dateTo);
				newIntervention.setEndDate(dateFrom);
				newIntervention.setCampus(Long.valueOf(campus));
				newIntervention.setSpeaker(speakerId);
				
				interventionDao.addIntervention(newIntervention);
				action = "intervention-conf";
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
				action = "intervention-error";
				request.setAttribute("action", action);
				request.setAttribute("errorMsg", errorMsg);
			}
		}else{
			CampusDao campusdao = DaoFactory.getInstance().getCampusDao();
			List<Campus> listCampus = campusdao.getAllCampus();
			request.setAttribute("listCampus", listCampus);
			action = "intervention-form";
			request.setAttribute("action", action);
		}
		request.getRequestDispatcher("/WEB-INF/addintervention.jsp").forward(request, response);
	}

}
