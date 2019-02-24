package model;

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
		
		//System.out.println("mi sposto su " + main.getName()); 
		ListObjectsRequest listObjectsRequest = 
                new ListObjectsRequest()
                      .withBucketName("fallbox").withPrefix(path);
		
		ObjectListing objects = s3.listObjects(listObjectsRequest);
		List<S3ObjectSummary> summaries = objects.getObjectSummaries();
		
		for(S3ObjectSummary s : summaries) {
			String name = s.getKey();
			int dimension = (int) s3.getObjectMetadata("fallbox", s.getKey()).getContentLength();
			Date lastChange = s3.getObjectMetadata("fallbox", s.getKey()).getLastModified();
			String owner = "";
			
			
				String sharespace = name.substring(0,name.indexOf('/'));
				owner = sharespace;
//				System.out.println("Owner ->"+ owner);
//				System.out.println("File - >" + name);
				if(sharespace.contains("_")) {

					owner = sharespace.substring(0,sharespace.indexOf('_'));
				}

			
				String superior = name.substring(0,name.lastIndexOf("/")+1);
				if(name.endsWith("/") && !main.getName().equals("fallbox") && !name.equals(sharespace + "/")) {
					String tmp = superior.substring(0,superior.lastIndexOf("/")-1);

					superior = tmp.substring(0,tmp.lastIndexOf("/")+1);
				}
				
			
			if(!name.endsWith("//")) {
			if(main.getName().equals(superior) || (main.getName().equals("fallbox") && name.equals(sharespace + "/"))) {
				
				Component f = null;
				if(name.endsWith("/") && !name.equals(owner + "/") && !name.equals(main.getName())){
					
					f = new Folder(name,dimension,lastChange,owner);
					main.add(f);
			//		System.out.println("aggiungo "+ f.getName() + " a " + main.getName()+"  date: "+ lastChange + "  dimension: "+ dimension);
					refreshContainer(f);
				}
				else if(name.equals(owner + "/") && main.getName().equals("fallbox") && !name.equals(main.getName())){
					
					f = new Folder(name,dimension,lastChange,owner);
					main.add(f);
		//			System.out.println("aggiungo "+ f.getName() + " a " + main.getName()+"  date: "+ lastChange + "  dimension: "+ dimension);
					refreshContainer(f);
					
				}
				else if(!(name.endsWith(owner + "/")) && !name.equals(main.getName()) ){
					f = new File(name,dimension,lastChange,owner);
					main.add(f);
		//			System.out.println("aggiungo "+ f.getName() + " a " + main.getName()+"  date: "+ lastChange + "  dimension: "+ dimension);
				}
				
				if(f!=null && sharespace.contains("_")) {
					if(main.getName().contains("can_edit")) {
						f.share(sharespace, true);
						
					}
					else if(!owner.equals(sharespace)){
						f.share(sharespace,false);
					}
				}
				
		//	System.out.println("\n");
		
				}
			}
		}
		
		
	}
	
	private static String fileName (String path)
	{
		String [] folders = path.split("/");
		
		if (path.lastIndexOf("/") == path.length()-1)
			return folders[folders.length-1] + "/";
		return folders[folders.length-1];
	}
	
	
	public static void createUserFolder(User u) {

		Date date = new Date();

		container.add(new Folder(u.getEmail(),0,date,u.getEmail()));
	}
	
	
}
