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
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
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
    	boolean external = false;
    	if(folderName.contains(user + "_")) {
    		permission = false;
    		if(folderName.contains("can_edit")) {
    			try {
    					external = true;
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
		    	 
    		return false;
    }
    
    
    public static boolean shareFile(String filePath, String destinationPath, String otherUser,String user)
    {
    	try {    	
    		S3Object object = s3.getObject("fallbox", otherUser + "/");
    		
    		try {
    			S3Object objectDes = s3.getObject("fallbox", otherUser + "/" + otherUser+"_"+ user+"/");
    		}
    		catch(AmazonServiceException e){
    			createFolder(user+"_"+ otherUser);
    			createFolder(user+"_"+ otherUser+"/"+"can_edit");
    			System.out.println("CARTELLA CREATA");
    		}
	    	try {
	    		
	    		if(filePath.endsWith("/")) {
	    			s3.copyObject("fallbox", filePath, "fallbox", destinationPath);
		    	    deleteFile(filePath);
		    	    s3.setObjectAcl("fallbox", destinationPath, CannedAccessControlList.PublicRead);
		    	    ListObjectsRequest listObjectsRequest = 
	    	                new ListObjectsRequest()
	    	                      .withBucketName("fallbox").withPrefix(filePath);
	    			
	    			ObjectListing objects = s3.listObjects(listObjectsRequest);
	    			List<S3ObjectSummary> summaries = objects.getObjectSummaries();
	    			
	    			for(S3ObjectSummary s : summaries) {
	    				String name = s.getKey();
	    				s3.copyObject("fallbox", name, "fallbox", destinationPath);
	    	    	    deleteFile(name);
	    	    	    s3.setObjectAcl("fallbox", destinationPath, CannedAccessControlList.PublicRead);
	    			}	
	    		}
	    		else {
	    	    s3.copyObject("fallbox", filePath, "fallbox", destinationPath);
	    	    deleteFile(filePath);
	    	    s3.setObjectAcl("fallbox", destinationPath, CannedAccessControlList.PublicRead);
	    		}
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
    
    public static void getListOfDetails (List<String> list, List<Component> mainFolder, String fileName, String user)
    {
    	List<String> dettagli = new ArrayList<String>();
    	
    	System.out.println("DATROVARE --> " + fileName);
    	
    	
		for (Component c : mainFolder)
		{	
			if (c.getName().contains(user))
			{	
				if (c.getName().equals(fileName))
				{
					System.out.println("SONO IN --> " + c.getName());
					System.out.println("TROVATO -->" + c.getName());
					dettagli.add("TROVATO");
					list.add(c.getName());
					list.add(c.getDimension().toString());
					list.add(c.getLastChange().toString());
					list.add(c.getOwner());
					for (String s : c.getCan_edit())
					{
						list.add(s + " - Can Edit -");
					}
				    for (String s : c.getCan_view())
				    {
				    	list.add(s + " - Can View -");
				    }
				}
				else if (c instanceof model.Folder)
				{
					getListOfDetails(list, c.getContent(), fileName, user);
				}
			}
		}
    }
    
}
