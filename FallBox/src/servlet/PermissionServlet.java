package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.s3.model.S3Object;


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
		String file = path.substring(path.lastIndexOf("/")+1);
		String owner = request.getParameter("owner");
		
		boolean done = false;
		
		if (canEdit.equals("true"))
			done = ServerHandler.shareFile(path, owner+"_"+otherUser+"/"+"can_edit/" + file, otherUser,owner);
		else
			done = ServerHandler.shareFile(path, owner+"_"+otherUser+"/" + file, otherUser,owner);
		
		if(!done) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		
	}

}
