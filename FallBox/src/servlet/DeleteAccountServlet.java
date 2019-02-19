package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;


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
		String user = (String) request.getSession(false).getAttribute("User");
		
		ServerHandler.deleteAll(user);
		
		UserDao.deleteUser(user);
		
		request.getSession(false).invalidate();
	}

}
