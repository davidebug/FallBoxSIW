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
    	List<String> details = getListOfDetails(filePath,user);
		String owner = details.get(3);
    		if(permission && !external) {
    			
    			System.out.println("owner " + owner);
    			System.out.println("inserisco in " + filePath);
		    	s3.putObject(new PutObjectRequest("fallbox", filePath, file));
		    	s3.setObjectAcl("fallbox", filePath, CannedAccessControlList.PublicRead);
		    	
		    	for(int i= 4; i<details.size();i++) {
    				String otherUser = details.get(i);
    				System.out.println(otherUser);
    				if(otherUser.contains("_canEdit")) {
    					String other = otherUser.substring(0,otherUser.indexOf('_'));
    					System.out.println("condivido a" + other);
    					shareFile(filePath,other + "/" + other+"_"+owner+"/"+"can_edit/" + file.getName(),other,owner);
    				}
    				else {
    					System.out.println("condivido a " + otherUser);
    					shareFile(filePath,otherUser + "/" + otherUser+"_"+owner+"/" + file.getName(),otherUser,owner);
    				}
    			}
		    	 
		    	
		    	return true;
    		}
    		else if(external) {
    			System.out.println("condivido a " + owner);
    			shareFile(filePath, owner +"/" + file.getName(),owner,user);
    			
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
    			createFolder(otherUser + "/" + otherUser+"_"+ user);
    			createFolder(otherUser + "/" + otherUser+"_"+ user+"/"+"can_edit");
    			System.out.println("CARTELLA CREATA");
    		}
	    	try {
	    	    s3.copyObject("fallbox", filePath, "fallbox", destinationPath);
	    	    s3.setObjectAcl("fallbox", destinationPath, CannedAccessControlList.PublicRead);
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
						for(String s : file.getCan_edit()) {
							details.add(s + "_canEdit");
						}
						details.addAll(file.getCan_view());
						//System.out.println(file.getName());
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
				for(String s : file.getCan_edit()) {
					list.add(s + "_canEdit");
				}
				list.addAll(file.getCan_view());
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
