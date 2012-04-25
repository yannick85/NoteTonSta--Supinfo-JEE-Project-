package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.MarkDao;
import entity.Mark;

/**
 * Servlet implementation class AddMarkServlet
 */
//@WebServlet("/addmark")
public class AddMarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMarkServlet() {
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
		String action = "";
		if(request.getParameter("id") != null){
			Long interventionId = Long.valueOf(request.getParameter("id"));
			request.setAttribute("interventionId", interventionId);
			if( (String) request.getParameter("mark-idbooster") != null){
				String idBooster = request.getParameter("mark-idbooster");
				String comments = request.getParameter("mark-comment");
				
				String speakerKnowledge =request.getParameter("mark-speaker-knowledge");
				String speakerTeaching = request.getParameter("mark-speaker-abilities");
				String speakerAnswers = request.getParameter("mark-speaker-answers");
				
				String slideContent = request.getParameter("mark-slide-content");
				String slideFormat = request.getParameter("mark-slide-format");
				String slideExample = request.getParameter("mark-slide-example");
				
				Boolean valid = true;
				List<String> errorMsg = new ArrayList<String>();
				//Validation
				if(!(idBooster.matches("^[0-9]{1,10}$"))){
					valid=false;
					errorMsg.add("The Id Booster is not valid.");
				}
				try{
					if(!( speakerKnowledge.matches("^[1-5]{1}$") & speakerTeaching.matches("^[1-5]{1}$") & speakerAnswers.matches("^[1-5]{1}$") & slideContent.matches("^[1-5]{1}$") & slideFormat.matches("^[1-5]{1}$") & slideExample.matches("^[1-5]{1}$"))){
						valid=false;
						errorMsg.add("The marks must be all renseigned.");
					}
				}catch(java.lang.NullPointerException e){
					valid=false;
					errorMsg.add("The marks must be all renseigned.");
				}
				
				
				if(valid){
					Mark newMark = new Mark();
					MarkDao markDao = DaoFactory.getInstance().getMarkDao();
					newMark.setIdBooster(Integer.valueOf(idBooster));
					newMark.setSpeakerKnowledge(Integer.valueOf(speakerKnowledge));
					newMark.setSpeakerTeaching(Integer.valueOf(speakerTeaching));
					newMark.setSpeakerAnswers(Integer.valueOf(speakerAnswers));
					newMark.setSlideContent(Integer.valueOf(slideContent));
					newMark.setSlideFormat(Integer.valueOf(slideFormat));
					newMark.setSlideExample(Integer.valueOf(slideExample));
					newMark.setComment(comments);
					newMark.setInterventionId(interventionId);
					
					markDao.addMark(newMark);
					action = "mark-conf";
				}else{
					request.setAttribute("oldIdBooster", idBooster);
					request.setAttribute("oldComments", comments);
					action = "mark-error";
					request.setAttribute("errorMsg", errorMsg);
				}
			}else{
				action = "mark-form";
			}
		}
		request.setAttribute("action", action);
		request.getRequestDispatcher("/WEB-INF/addmark.jsp").forward(request, response);
	}
}
