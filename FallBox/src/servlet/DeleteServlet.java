package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

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
		String fileName = path.substring(path.lastIndexOf("/")+1); //CI VA IL +1?
		String bucket = path.substring(0, path.lastIndexOf("/"));
		
		BasicAWSCredentials creds = new BasicAWSCredentials("AKIAIQ4MJIXDJXQ2YTHA", "zQKT7bggJHZD4vaU9y41mlc7piYC14E/n9XhQclf\n" + 
		 		"");
		final AmazonS3 s3 = AmazonS3Client.builder().withRegion("eu-central-1").withCredentials(new AWSStaticCredentialsProvider(creds)).build();
	
		try {
		    s3.deleteObject(bucket, fileName);
		} catch (AmazonServiceException e) {
		    System.err.println(e.getErrorMessage());
		    System.exit(1);
		}
	}

}
