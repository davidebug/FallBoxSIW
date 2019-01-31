package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

@WebServlet(urlPatterns={"/UserPageServlet/*"})
public class UserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserPageServlet() 
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
		String newEmail = null;
		String newPassword = null;
				
		if ((newEmail = request.getParameter("email")) != null && newEmail != "")
		{	
				User user = new User();
				user.setEmail((String)request.getSession().getAttribute("User"));
				user.setPassword(request.getParameter("currentPassword"));
				
				int updateStatus = dao.UserDao.updateEmail(user, newEmail);	
				
				if (updateStatus == -1)
				{
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					response.getWriter().println("Email already in use");
					response.setContentType("text/plain; charset=UTF-8");
				}
				
				else if (updateStatus == 0)
				{
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					response.getWriter().println("Wrong password");
					response.setContentType("text/plain; charset=UTF-8");
				}
				
				else			//SE E' ANDATO A BUON FINE
				{
					request.getSession().invalidate();
					HttpSession newSession = request.getSession();
					newSession.setAttribute("User", newEmail);
				}
		}
		
		if ((newPassword = request.getParameter("newPassword")) != null && newPassword != "")
		{
			User user = new User();
			user.setEmail((String)request.getSession().getAttribute("User"));
			user.setPassword(request.getParameter("currentPassword"));

			System.out.println("SONO QUI");
			
			if (dao.UserDao.logIn(user))
			{
				System.out.println("TROVATO UTENTE");
				user.setPassword(newPassword);
				if (!dao.UserDao.updatePassword(user))
				{
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					response.getWriter().println("Wrong password");
					response.setContentType("text/plain; charset=UTF-8");
				}
			}
		}
	}

}
