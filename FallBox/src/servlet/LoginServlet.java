package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.UserDao;
import model.User;

//SE ARRIVO NELLA SERVLET, VUOL DIRE CHE LE CREDENZIALI VANNO CONTROLLATE PERCHE' NON ESISTE UNA SESSION

@WebServlet(urlPatterns={"/LoginServlet/*"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public LoginServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		User user = new User();
		user.setEmail((String) request.getParameter("email"));
		user.setPassword((String) request.getParameter("password"));
		if (UserDao.logIn(user)) 					//SE LE CREDENZIALI SONO OK
		{   
			HttpSession session = request.getSession();			//CREO UNA NUOVA SESSIONE;
			session.setAttribute("User", user.getEmail());
			System.out.println("Creata sessione");
		}
		else 
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().println("Wrong Email or Password");
			response.setContentType("text/plain; charset=UTF-8");
		}
		
	}
	
	


}
