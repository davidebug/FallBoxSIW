package servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


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
		
		String utente = null;
		
		List<FileItem> items = null;
		try {
			items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		} 
		catch (FileUploadException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (FileItem item : items) 
		{
		    if (item.getFieldName().equals("Utente")) 
		    {
		        utente = item.getString();
		    }
		}
		
		String owner = (String)request.getSession(false).getAttribute("User");
		
		String ownerPath = "/home/gaetano/Scrivania/FallBoxFiles/" + utente + "/" + owner;
				
		//SE NON ESISTE LA CARTELLA COL NOME DELL'OWNER ALL'INTERNO DEL SUO SPAZIO, LA CREO
		if (!checkDir(utente, owner))
		{

			File userDirectory = new File(ownerPath);
			userDirectory.mkdir();
		}
		
		FileCreator.createFile(ownerPath, items);
	}
	
	
	private boolean checkDir(String collaboratore, String owner)
	{
		return new File("/home/gaetano/Scrivania/FallBoxFiles/" + collaboratore, owner).exists();
	}

}
