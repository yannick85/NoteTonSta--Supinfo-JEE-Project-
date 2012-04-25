package servlet;

import java.io.IOException;
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
 * Servlet implementation class ListInterventionServlet
 */
//@WebServlet("/campus")
public class ListInterventionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListInterventionServlet() {
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
		InterventionDao interventiondao = DaoFactory.getInstance().getInterventionDao();
		CampusDao campusdao = DaoFactory.getInstance().getCampusDao();
		List<Intervention> listIntervention = null;
		if(request.getParameter("cid") != null){
			Long campusId = Long.valueOf(request.getParameter("cid"));
			action="listintervention-ok";
			Campus campus = campusdao.getCampusById(campusId);
			request.setAttribute("campus", campus);
			listIntervention = interventiondao.getInterventionbyCampus(campusId);
		}else{
			action="listintervention-all";
			listIntervention = interventiondao.getAllIntervention();
		}
		Intervention thisIntervention;
		for(int i=0;i<listIntervention.size();i++){
			thisIntervention = listIntervention.get(i);
			
			Date today = new java.sql.Date(System.currentTimeMillis());
			if(thisIntervention.getBeginDate().after(today)){
				thisIntervention.setStatus(1); //Not started
			}else if(thisIntervention.getEndDate().before(today)){
				thisIntervention.setStatus(3); //Ended
			}else{
				thisIntervention.setStatus(2); //In progress
			}
		}
		request.setAttribute("listIntervention", listIntervention);
		request.setAttribute("action", action);
		
		request.getRequestDispatcher("/WEB-INF/listintervention.jsp").forward(request, response);
	}

}
