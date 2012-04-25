package ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.InterventionDao;

/**
 * Servlet implementation class DeleteInterventionAjaxServlet
 */
@WebServlet("/auth/deleteintervention")
public class DeleteInterventionAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteInterventionAjaxServlet() {
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
		String result="";
		InterventionDao interventionDao = DaoFactory.getInstance().getInterventionDao();
		if(request.getParameter("id") != null){
			long interventionId = Long.valueOf((String) request.getParameter("id"));
			interventionDao.deleteIntervention(interventionId);
			result="ok";
		}else{
			result = "error - no id";
		}
		response.getWriter().print(result);
	}
}
