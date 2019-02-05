package servlet;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

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
    

    public static boolean uploadFile(File file, String folderName, String user)
    {
    	String filePath = folderName + file.getName();
    	boolean permission = true;
    	
    	if(folderName.contains(user + "_")) {
    		permission = false;
    		if(folderName.contains("can_edit")) {
    			try {
	    				S3Object object = s3.getObject("fallbox", filePath);
	    			}
    			catch(AmazonServiceException e) {
    		    	    System.err.println(e.getErrorMessage());
    		    	    permission = false;
    		    	    return false;
    				
    			}
    			permission = true;
	    	}
    	}
    		if(permission) {		
		    	s3.putObject(new PutObjectRequest("fallbox", filePath, file));
		    	s3.setObjectAcl("fallbox", filePath, CannedAccessControlList.PublicRead);
		    	return true;
    		}
    		else {
    			return false;
    		}
    }
    
    
    public static boolean shareFile(String filePath, String destinationPath, String otherUser)
    {
    	try {    	
    		S3Object object = s3.getObject("fallbox", otherUser + "/");
    	
    	
	    	try {
	    	    s3.copyObject("fallbox", filePath, "fallbox", destinationPath);
	    	    return true;
	    	} 
	    	catch (AmazonServiceException e) 
	    	{
	    	    System.err.println(e.getErrorMessage());
	    	    return false;
	    	}
    	}
    	catch(AmazonServiceException e) {
    	    System.err.println(e.getErrorMessage());
    	    return false;
    	
    	}
    }	
}
