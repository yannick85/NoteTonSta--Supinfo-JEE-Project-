package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.SpeakerDao;

import entity.Speaker;

/**
 * Servlet implementation class RegisterSpeaker
 */
//@WebServlet("/register")
public class RegisterSpeaker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterSpeaker() {
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
		if( (String) request.getParameter("speaker-lastname") != null){
			
			String lastName = (String) request.getParameter("speaker-lastname");
			String firstName = (String) request.getParameter("speaker-firstname");
			String email = (String) request.getParameter("speaker-mail");
			String password = (String) request.getParameter("speaker-password");
			String confPassword = (String) request.getParameter("speaker-confpassword");
			Boolean valid = true;
			List<String> errorMsg = new ArrayList<String>();
			//Validation
			if(!(lastName.matches("^[a-zA-Z0-9]{3,14}$"))){
				valid=false;
				errorMsg.add("The lastname must contains only letters and numbers , and must be between 3 and 14 characters.");
			}
			if(!(firstName.matches("^[a-zA-Z0-9]{3,14}$"))){
				valid=false;
				errorMsg.add("The firstname must contains only letters and numbers , and must be between 3 and 14 characters.");
			}
			if(!(email.matches("^[a-z0-9._-]+@[a-z0-9._-]{2,}[.]{1}[a-z]{2,4}$"))){
				valid=false;
				errorMsg.add("The email is not valid.");
			}
			if(!(password.matches("^[a-zA-Z0-9%]{7,14}$"))){
				valid=false;
				errorMsg.add("The password is not valid , he must contains only letters and numbers and % , and must be between 7 and 14 characters.");
			}
			if(!(password.equals(confPassword))){
				valid=false;
				errorMsg.add( "the passwords doesn't match.");
			}
			
			if(valid){
				Speaker newSpeaker = new Speaker();
				SpeakerDao speakerDao = DaoFactory.getInstance().getSpeakerDao();

				newSpeaker.setLastName(lastName);
				newSpeaker.setFirstName(firstName);
				newSpeaker.setEmail(email);
				newSpeaker.setPassword(speakerDao.MD5(confPassword));
				
				speakerDao.addSpeaker(newSpeaker);
				action = "register-conf";
				request.setAttribute("action", action);
			}else{
				action = "register-error";
				request.setAttribute("action", action);
				request.setAttribute("errorMsg", errorMsg);
				request.setAttribute("oldFirstname", firstName);
				request.setAttribute("oldLastname", lastName);
				request.setAttribute("oldEmail", email);
			}
		}else{
			action = "register-form";
			request.setAttribute("action", action);
		}
		request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
	}

}
