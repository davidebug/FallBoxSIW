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

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

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
		
    	filePath = "/Users/davide/Desktop/FallBoxFiles/" + request.getSession(false).getAttribute("User") + "/";
		
//		S3uploader s3 = new S3uploader();
//        String result = s3.fileUploader(items);
//        System.out.println(result);
		
		FileCreator file = new FileCreator();
		
        
		//se non c'è un file da caricare
		if( !ServletFileUpload.isMultipartContent(request) ) {
	        
			 //SE PER CASO L'UTENTE NON HA SELEZIONATO UN FILE DA CARICARE. FORSE QUESTO CONTROLLO E' INUTILE
			//SE SI PUO' FARE DIRETTAMENTE CON JS
	         return;
	      }
		
		 file.createFile(filePath, items);
		 
		 BasicAWSCredentials creds = new BasicAWSCredentials("AKIAIQ4MJIXDJXQ2YTHA", "zQKT7bggJHZD4vaU9y41mlc7piYC14E/n9XhQclf\n" + 
		 		"");
		 final AmazonS3 s3 = AmazonS3Client.builder().withRegion("eu-central-1").withCredentials(new AWSStaticCredentialsProvider(creds)).build();
			try {
			    s3.putObject("fallbox/"+ request.getSession(false).getAttribute("User"), file.getFile().getName(), file.getFile());
			} catch (AmazonServiceException e) {
			    System.err.println(e.getErrorMessage());
			    System.exit(1);
			}
		
	}
}


