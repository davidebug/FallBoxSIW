package servlet;

import java.io.IOException;
import java.io.InputStream;
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
import org.apache.commons.io.FilenameUtils;

@WebServlet(urlPatterns={"/UploadServlet/*"})
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String filePath = new String("");
	private String currDirectory = new String("");
	
    public UploadServlet() 
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
		
		if(request.getParameter("currDirectory")!= null)
			currDirectory = request.getParameter("currDirectory");
		
		else if (currDirectory != null && !currDirectory.equals(""))
		{
			List<FileItem> items = null;
			try {
				items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    	InputStream toLoad = items.get(0).getInputStream();
		
	    	String fileName = items.get(0).getName();
	    	fileName = FilenameUtils.getName(fileName);
	    	
	    	
//			filePath = System.getProperty("user.home") + "/Desktop";
			
//	    	FileCreator file;
			
//			file = new FileCreator();
			
			//se non c'è un file da caricare
			if( !ServletFileUpload.isMultipartContent(request) ) {
		        
				 //SE PER CASO L'UTENTE NON HA SELEZIONATO UN FILE DA CARICARE. FORSE QUESTO CONTROLLO E' INUTILE
				//SE SI PUO' FARE DIRETTAMENTE CON JS
		         return;
		      }
			
//			 file.createFile(filePath, items);
		
			
			 
			 boolean done = false;
			 done = ServerHandler.uploadFile(toLoad, fileName, currDirectory,(String) request.getSession(false).getAttribute("User"));
			 
			 if(!done) {
				 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			 }
			 else {
			 response.sendRedirect(request.getContextPath() + "/main.jsp");
			 }
		}
        
		
		
	}
}


