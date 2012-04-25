package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CampusDao;
import dao.DaoFactory;
import dao.InterventionDao;
import dao.MarkDao;
import entity.Campus;
import entity.Intervention;
import entity.Mark;

/**
 * Servlet implementation class MyInterventionServlet
 */
//@WebServlet("/auth/myintervention")
public class MyInterventionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInterventionServlet() {
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
		InterventionDao interventiondao = DaoFactory.getInstance().getInterventionDao();
		MarkDao markdao = DaoFactory.getInstance().getMarkDao();
		CampusDao campusdao = DaoFactory.getInstance().getCampusDao();
		List<Intervention> listIntervention = null;
		Hashtable<Long,String> listCampusName = new Hashtable<Long,String>();
		Hashtable<Long,String> listMark = new Hashtable<Long,String>();
		listIntervention = interventiondao.getInterventionbySpeaker((Long) request.getSession().getAttribute("speaker-id"));
		
		Intervention thisIntervention;
		Campus thisCampus;
		List<Mark> thisMarkList;
		Mark thisMark;
		int oneMark;
		int allMark;
		float thisAverage;
		String thisAverageString;
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
			
			thisCampus = campusdao.getCampusById(thisIntervention.getCampus());
			thisMarkList = markdao.getMarkByIntervention(thisIntervention.getId());
			allMark = 0;
			for(int j=0;j<thisMarkList.size();j++){
				thisMark = thisMarkList.get(j);
				//Mark /30
				oneMark = thisMark.getSpeakerAnswers()+thisMark.getSpeakerKnowledge()+thisMark.getSpeakerTeaching()+thisMark.getSlideContent()+thisMark.getSlideExample()+thisMark.getSlideFormat();
				allMark = allMark + oneMark;
			}
			thisAverage = (float)allMark / (float)(thisMarkList.size()*6);
			if(thisMarkList.size() == 0){
				thisAverageString = "No mark";
			}else{
				thisAverageString = String.valueOf(thisAverage);
			}
			listCampusName.put(thisIntervention.getId(), thisCampus.getName());
			listMark.put(thisIntervention.getId(),thisAverageString);
		}
		
		request.setAttribute("listCampusName", listCampusName);
		request.setAttribute("listMark", listMark);
		request.setAttribute("listIntervention", listIntervention);
		request.getRequestDispatcher("/WEB-INF/myintervention.jsp").forward(request, response);
	}
}
