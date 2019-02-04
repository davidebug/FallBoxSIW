package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns={"/CreateFolderServlet/*"})
public class CreateFolderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CreateFolderServlet() 
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
		String path = request.getSession(false).getAttribute("User") + "/" + request.getParameter("folderName");
		
		ServerHandler.createFolder(path);
		
	}

}
