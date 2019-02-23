package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		System.out.println("SONO NELLA DELETE SERVLET");
		
		String email = (String) request.getSession(false).getAttribute("User");
		String currPassword = request.getParameter("currentPassword");
				
		User user = new User();
		user.setEmail(email);
		user.setPassword(currPassword);
		
		if (UserDao.logIn(user))
		{	
			ServerHandler.deleteAll(email);
			
			UserDao.deleteUser(email);
			
			request.getSession(false).invalidate();
		}
	}

}
