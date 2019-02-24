package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.UserDao;
import model.User;


@WebServlet(urlPatterns={"/DeleteAccountServlet/*"})
public class DeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		
		String email = (String) request.getSession(false).getAttribute("User");
		String currPassword = request.getParameter("currentPassword");
				
		User user = new User();
		user.setEmail(email);
		user.setPassword(currPassword);
		
		UserDao userDao = DAOFactory.getUserDao();
		
		if (userDao.logIn(user))
		{	
			ServerHandler.deleteAll(email);
			userDao.deleteUser(email);
			request.getSession(false).invalidate();
		}
		else 
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().println("Wrong Email or Password");
			response.setContentType("text/plain; charset=UTF-8");
		}
	}

}
