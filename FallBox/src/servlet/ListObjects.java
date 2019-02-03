package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import model.Component;
import model.Container;

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
		
		String user =(String) request.getSession().getAttribute("User");
		
		String path = user + "/" + request.getParameter("currentFolder");
		
		Container c = new Container();
	//	Container.refreshAll();
	//	System.out.println( Container.getContainer().getContent().size());
		for(Component comp : Container.getContainer().getContent()) {
			//System.out.println(comp.getName());
		}
//		 BasicAWSCredentials creds = new BasicAWSCredentials("AKIAIQ4MJIXDJXQ2YTHA", "zQKT7bggJHZD4vaU9y41mlc7piYC14E/n9XhQclf\n" + "");
//		 final AmazonS3 s3 = AmazonS3Client.builder().withRegion("eu-central-1").withCredentials(new AWSStaticCredentialsProvider(creds)).build();
//		 
//		 
//		 
//		 
//		 ListObjectsRequest listObjectsRequest = 
//                 new ListObjectsRequest()
//                       .withBucketName("fallbox").withPrefix(path);
//
//		List<String> keys = new ArrayList<>();
//		
//		ObjectListing objects = s3.listObjects(listObjectsRequest);
//		for (;;) {
//			List<S3ObjectSummary> summaries = objects.getObjectSummaries();
//			if (summaries.size() < 1) {
//				break;
//			}
//			summaries.forEach(s -> keys.add(s.getKey()));
//			objects = s3.listNextBatchOfObjects(objects);
//		}
//		for (String file: keys) {
//				System.out.println(file);
//		}
//		request.getSession(false).setAttribute("Files", keys);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{		
		
	}

}
