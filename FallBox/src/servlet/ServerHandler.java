package servlet;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.transfer.MultipleFileDownload;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;

import model.Component;
import model.Container;
import model.Folder;

public class ServerHandler {

	final static AmazonS3 s3;
	static BasicAWSCredentials creds;

	static
	{
		creds = new BasicAWSCredentials("AKIAIQ4MJIXDJXQ2YTHA", "zQKT7bggJHZD4vaU9y41mlc7piYC14E/n9XhQclf\n" + 
		 		"");
		s3 = AmazonS3Client.builder().withRegion("eu-central-1").withCredentials(new AWSStaticCredentialsProvider(creds)).build();
	}
	
	
    public static void createFolder(String folderName) 
    {		
    	ObjectMetadata metadata = new ObjectMetadata();
    	metadata.setContentLength(0);
    	InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
    	PutObjectRequest putObjectRequest = new PutObjectRequest("fallbox",
    				folderName + "/", emptyContent, metadata);
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
    
    public static void downloadFile(String path)
    {
    	TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds)).withRegion("eu-central-1").build()).build();

		File dir = new File(System.getProperty("user.home"), "Desktop");
		
		MultipleFileDownload download =  transferManager.downloadDirectory("fallbox", path, dir);
		try {
			download.waitForCompletion();
		} catch (AmazonClientException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void deleteFile(String path)
    {
    	try {
		    s3.deleteObject("fallbox", path);
		} catch (AmazonServiceException e) {
		    System.err.println(e.getErrorMessage());

		    System.exit(1);
		}
    }
    
    public static List<String> getListOfDetails (String fileName, String user)
    {
		List<Component> mainFolder = Container.getContainer().getContent();
		List<String> details = new ArrayList<String>();
    	
    	for (Component dir : mainFolder)
		{
			if (dir.getName().equals(user + "/") && dir instanceof Folder)	 
			{
				List<Component> userFiles = (dir.getContent());
				
				for (Component file : userFiles)
				{	
					//HO TROVATO IL FILE CHE CERCAVO
					if (file.getName().equals(fileName))
					{
						
						details.add(file.getName());
						details.add(file.getDimension().toString());
						details.add(file.getLastChange().toString());
						details.add(file.getOwner());
						details.addAll(file.getCan_edit());
						details.addAll(file.getCan_view());
						System.out.println(file.getName());
						break;
						//out.flush();
					}
					else if(file instanceof Folder) {
						details = getDetails(fileName,file);
						
					}
					
					//aggiungere condizione per l'uscita dal for
					
				}
			}
			
		}
    	return details;
    }
    
    public static List<String> getDetails(String fileName,Component c)
    {
		List<String> list = new ArrayList<String>();
		for (Component file : c.getContent())
		{	
			//HO TROVATO IL FILE CHE CERCAVO
			if (file.getName().equals(fileName))
			{
				
				list.add(file.getName());
				list.add(file.getDimension().toString());
				list.add(file.getLastChange().toString());
				list.add(file.getOwner());
				System.out.println(file.getName());
				break;
			}
			else if(file instanceof Folder) {
				list = getDetails(fileName,file);
				
			}
		}
		return list;
	}
}