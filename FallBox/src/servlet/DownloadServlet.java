package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

@WebServlet(urlPatterns={"/DownloadServlet/*"})
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public DownloadServlet() 
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
		
		File downloadedFile = new File(fileName);
		
		BasicAWSCredentials creds = new BasicAWSCredentials("AKIAIQ4MJIXDJXQ2YTHA", "zQKT7bggJHZD4vaU9y41mlc7piYC14E/n9XhQclf\n" + 
		 		"");
		final AmazonS3 s3 = AmazonS3Client.builder().withRegion("eu-central-1").withCredentials(new AWSStaticCredentialsProvider(creds)).build();
		
		try {
		    S3Object o = s3.getObject(bucket, fileName);
		    S3ObjectInputStream s3is = o.getObjectContent();
		    FileOutputStream fos = new FileOutputStream(downloadedFile);
		    byte[] read_buf = new byte[1024];
		    int read_len = 0;
		    while ((read_len = s3is.read(read_buf)) > 0) {
		        fos.write(read_buf, 0, read_len);
		    }
		    s3is.close();
		    fos.close(); 
		} catch (AmazonServiceException e) {
		    System.err.println(e.getErrorMessage());
		    System.exit(1);
		} catch (FileNotFoundException e) {
		    System.err.println(e.getMessage());
		    System.exit(1);
		} catch (IOException e) {
		    System.err.println(e.getMessage());
		    System.exit(1);
	}
		
		OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(downloadedFile);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0)
        {
           out.write(buffer, 0, length);
        }
        in.close();
        out.flush();

	}
	
}
