package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.SpeakerDao;
import entity.Speaker;

/**
 * Servlet implementation class LogServlet
 */
//@WebServlet("/log")
public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogServlet() {
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
		if( (String) request.getParameter("speaker-mail") != null){
			SpeakerDao speakerDao = DaoFactory.getInstance().getSpeakerDao();
			String mail = (String) request.getParameter("speaker-mail");
			String password = speakerDao.MD5((String) request.getParameter("speaker-password"));
			Speaker testSpeaker = speakerDao.getSpeakerByMailPassword(mail, password);
			//verification
			if(testSpeaker != null){
				request.getSession().setAttribute("speaker-id", testSpeaker.getId());
				request.getSession().setAttribute("speaker-lastname", testSpeaker.getLastName());
				request.getSession().setAttribute("speaker-firstname", testSpeaker.getFirstName());
				request.getSession().setAttribute("speaker-mail", testSpeaker.getEmail());
				action = "log-conf";
				request.setAttribute("action", action);
			}else{
				action = "log-error";
				request.setAttribute("action", action);
			}			
		}else{
			action = "log-form";
			request.setAttribute("action", action);
		}
		request.getRequestDispatcher("/WEB-INF/log.jsp").forward(request, response);
	}

}
