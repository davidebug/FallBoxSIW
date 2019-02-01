package servlet;

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

@WebServlet(urlPatterns={"/UploadServlet/*"})
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String filePath;
	
	
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
		List<FileItem> items = null;
		try {
			items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	filePath = "/home/gaetano/Scrivania/FallBoxFiles/" + request.getSession(false).getAttribute("User") + "/";
		
		//se non c'è un file da caricare
		if( !ServletFileUpload.isMultipartContent(request) ) {
	        
			 //SE PER CASO L'UTENTE NON HA SELEZIONATO UN FILE DA CARICARE. FORSE QUESTO CONTROLLO E' INUTILE
			//SE SI PUO' FARE DIRETTAMENTE CON JS
	         return;
	      }
		
		 FileCreator.createFile(filePath, items);
		
	}
}


