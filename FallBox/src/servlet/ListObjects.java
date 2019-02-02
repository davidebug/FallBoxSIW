package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@WebServlet(urlPatterns={"/ListObjects/*"})
public class ListObjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListObjects() 
    {
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
		System.out.println("SONO QUI");
		String user = request.getParameter("User");
		
		String path = "fallbox/" + user + "/" + request.getParameter("path");
		
		AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
		ListObjectsV2Result result = s3.listObjectsV2(path);
		List<S3ObjectSummary> objects = result.getObjectSummaries();
		
		for (S3ObjectSummary os: objects) {
		    System.out.println("* " + os.getKey());
		}
		
		request.getSession(false).setAttribute("Files", objects); //TI METTO LA LISTA DI FILES NELLA SESSIONE
	}

}
