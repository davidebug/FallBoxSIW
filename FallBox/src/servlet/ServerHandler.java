package servlet;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class ServerHandler {

	final static AmazonS3 s3;

	static
	{
		BasicAWSCredentials creds = new BasicAWSCredentials("AKIAIQ4MJIXDJXQ2YTHA", "zQKT7bggJHZD4vaU9y41mlc7piYC14E/n9XhQclf\n" + 
		 		"");
		s3 = AmazonS3Client.builder().withRegion("eu-central-1").withCredentials(new AWSStaticCredentialsProvider(creds)).build();
	}
	
	
    public static void createFolder(String folderName) 
    {
		
    	// create meta-data for your folder and set content-length to 0
    	ObjectMetadata metadata = new ObjectMetadata();
    	metadata.setContentLength(0);
    	// create empty content
    	InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
    	// create a PutObjectRequest passing the folder name suffixed by /
    	PutObjectRequest putObjectRequest = new PutObjectRequest("fallbox",
    				folderName + "/", emptyContent, metadata);
    	// send request to S3 to create folder
    	s3.putObject(putObjectRequest);
    }
    

    public static void uploadFile(File file, String folderName)
    {
    	String filePath = folderName + file.getName();
    	
    	s3.putObject(new PutObjectRequest("fallbox", filePath, file));
    }
    
    
    public static void copyFile(String filePath, String destinationPath)
    {
    	try {
    	    s3.copyObject("fallbox", filePath, "fallbox", destinationPath);
    	} 
    	catch (AmazonServiceException e) 
    	{
    	    System.err.println(e.getErrorMessage());
    	    System.exit(1);
    	}
    }
}
