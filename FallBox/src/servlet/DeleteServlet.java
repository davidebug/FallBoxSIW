package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/DeleteServlet/*"})
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteServlet() 
    {
        super();
    }

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String path = request.getParameter("filePath");
		
		boolean done = ServerHandler.deleteFile(path,(String) request.getSession(false).getAttribute("User"));
		if(!done)
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}

}