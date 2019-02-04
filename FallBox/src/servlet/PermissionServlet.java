package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns={"/PermissionServlet/*"})
public class PermissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public PermissionServlet() {
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
		String user = (String)request.getSession(false).getAttribute("User");
		String otherUser = request.getParameter("otherUser"); //UTENTE CON CUI CONDIVIDERE
		String path = request.getParameter("filePath"); //FILE DA CONDIVIDERE
		String canEdit = request.getParameter("canEdit");
		
		if (canEdit == "true")
			ServerHandler.shareFile(path, otherUser + "/" + otherUser+"_"+user+"/"+"can_edit/");
		else
			ServerHandler.shareFile(path, otherUser + "/" + otherUser+"_"+user+"/");
		
	}

}
