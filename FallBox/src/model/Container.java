package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class Container {
	 
	//cartella principale di fallbox, da aggiornare ogni volta si carica la schermata
	public static Folder container ;
	
	public Container() {
		container = new Folder("fallbox",0,null,"");
		refreshAll();
	}
	
	public static Folder getContainer() {
		return container;
	}
	public static void setContainer(Folder container) {
		Container.container = container;
	}
	
	public static void refreshAll() {
		refreshContainer(container);
	}
	
	public static void refreshContainer(Component main) {
		
		BasicAWSCredentials creds = new BasicAWSCredentials("AKIAIQ4MJIXDJXQ2YTHA", "zQKT7bggJHZD4vaU9y41mlc7piYC14E/n9XhQclf\n" + "");
		final AmazonS3 s3 = AmazonS3Client.builder().withRegion("eu-central-1").withCredentials(new AWSStaticCredentialsProvider(creds)).build();
		
		String path = "";
		if(main.getName()!="fallbox")
			path = new String(main.getName());
		
		System.out.println("mi sposto su" + main.getName()); 
		ListObjectsRequest listObjectsRequest = 
                new ListObjectsRequest()
                      .withBucketName("fallbox").withPrefix(path);
		
		ObjectListing objects = s3.listObjects(listObjectsRequest);
		List<S3ObjectSummary> summaries = objects.getObjectSummaries();
		for(S3ObjectSummary s : summaries) {
			String name = s.getKey();
			int dimension = (int) s3.getObjectMetadata("fallbox", s.getKey()).getInstanceLength();
			Date lastChange = s3.getObjectMetadata("fallbox", s.getKey()).getLastModified();
			String owner = s.getKey().substring(0,s.getKey().indexOf('/'));
			Component f = null;

			if(s.getKey().endsWith("/") && !name.equals(main.getName())) {
				if(main.getName().equals("fallbox")) {
					if(name.equals(owner +"/")) {
					
						f = new Folder(name,dimension,lastChange,owner);
						System.out.println("aggiungo cartella "+ f.getName()+ " a "+ main.getName());
						main.add(f);
					
						refreshContainer(f);
					}	
				}
				else {
					f = new Folder(name,dimension,lastChange,owner);
					System.out.println("aggiungo cartella "+ f.getName()+ " a "+ main.getName());
					main.add(f);
				
					refreshContainer(f);
				}
			}
			else if(!name.equals(main.getName()) && !main.getName().equals("fallbox")){
				f = new File(name,dimension,lastChange,owner);
				System.out.println(" aggiungo file " + f.getName()+" a "+ main.getName());
				main.add(f);
			}
		}
	}
	public static void createUserFolder(User u) {

		Date date = new Date();

		container.add(new Folder(u.getEmail(),0,date,u.getEmail()));
	}
	
	
}
