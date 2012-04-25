package servlet;

import java.io.IOException;
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
 * Servlet implementation class InterventionServlet
 */
//@WebServlet("/intervention")
public class InterventionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InterventionServlet() {
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
		String action = "intervention-ok";
		InterventionDao interventiondao = DaoFactory.getInstance().getInterventionDao();
		CampusDao campusdao = DaoFactory.getInstance().getCampusDao();
		MarkDao markdao = DaoFactory.getInstance().getMarkDao();
		Intervention intervention = null;
		Campus campus = null;
		if(request.getParameter("id") != null){
			Long interventionId = Long.valueOf(request.getParameter("id"));
			intervention = interventiondao.getInterventionById(interventionId);
			campus = campusdao.getCampusById(intervention.getCampus());
			List<Mark> marks = markdao.getMarkByIntervention(interventionId);
			
			Integer markNumber=marks.size();
			Integer oneMarkSlide;
			Integer allMarkSlide = 0;
			Integer oneMarkSpeaker;
			Integer allMarkSpeaker = 0;
			Integer thisMarkAverage;
			Mark thisMark = null;
			Hashtable<Integer,Integer> listForPieChart = new Hashtable<Integer,Integer>();
			listForPieChart.put(1,0);
			listForPieChart.put(2,0);
			listForPieChart.put(3,0);
			listForPieChart.put(4,0);
			listForPieChart.put(5,0);
			for(int i=0;i<markNumber;i++){
				thisMark = marks.get(i);
				//Slide Mark /15
				oneMarkSlide = thisMark.getSpeakerAnswers()+thisMark.getSpeakerKnowledge()+thisMark.getSpeakerTeaching();
				//Speaker Mark /15
				oneMarkSpeaker = thisMark.getSlideContent()+thisMark.getSlideExample()+thisMark.getSlideFormat();
				
				allMarkSlide = allMarkSlide + oneMarkSlide;
				allMarkSpeaker = allMarkSpeaker + oneMarkSpeaker;
				
				thisMarkAverage = (oneMarkSpeaker + oneMarkSlide) / 6;
				listForPieChart.put(thisMarkAverage, listForPieChart.get(thisMarkAverage)+1);
			}
			Float slideAverage = Float.valueOf(allMarkSlide) / Float.valueOf(markNumber*3);
			Float speakerAverage= Float.valueOf(allMarkSpeaker) / Float.valueOf(markNumber*3);
			Float generalAverage = Float.valueOf(allMarkSpeaker+allMarkSlide) / Float.valueOf(markNumber*6);
			request.setAttribute("markNumber", String.valueOf(markNumber));
			request.setAttribute("slideAverage", String.valueOf(slideAverage));
			request.setAttribute("speakerAverage", String.valueOf(speakerAverage));
			request.setAttribute("generalAverage", String.valueOf(generalAverage));
			
			//Creation du la boite a camembert
			String listNombreOccurenceMark="";
			String listMarkValid="";
			for(int i=1;i<6;i++){
				if(listForPieChart.get(i) != 0){
					if(listNombreOccurenceMark != ""){
						listNombreOccurenceMark = listNombreOccurenceMark + "," + String.valueOf(listForPieChart.get(i));
						listMarkValid = listMarkValid + "|" + String.valueOf(i);
					}else{
						listNombreOccurenceMark = String.valueOf(listForPieChart.get(i));
						listMarkValid = String.valueOf(i);
					}
					
				}
			}
			String pieChartUrl = "http://chart.apis.google.com/chart?cht=p3&chd=t:"+listNombreOccurenceMark+"&chs=400x200&chl="+listMarkValid+"&chdl="+listMarkValid;
			request.setAttribute("pieChartUrl", pieChartUrl);
		}
		if(intervention == null){
			action="intervention-error";
		}
		request.setAttribute("intervention", intervention);
		request.setAttribute("campus", campus);
		request.setAttribute("action", action);
		
		request.getRequestDispatcher("/WEB-INF/intervention.jsp").forward(request, response);
	}

}
