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
		
		System.out.println("mi sposto su " + main.getName()); 
		ListObjectsRequest listObjectsRequest = 
                new ListObjectsRequest()
                      .withBucketName("fallbox").withPrefix(path);
		
		ObjectListing objects = s3.listObjects(listObjectsRequest);
		List<S3ObjectSummary> summaries = objects.getObjectSummaries();
		
		for(S3ObjectSummary s : summaries) {
			String tmp = s.getKey();
			int dimension = (int) s3.getObjectMetadata("fallbox", s.getKey()).getInstanceLength();
			Date lastChange = s3.getObjectMetadata("fallbox", s.getKey()).getLastModified();
			String owner = "";
			String name = "";
				
				String sharespace = tmp.substring(0,tmp.indexOf('/'));
				owner = sharespace;
				if(tmp.contains(sharespace + "_")) {
					String t = tmp.substring(tmp.indexOf('_')+1);

					owner = t.substring(0,t.indexOf('/'));
				}	
			
			
			if((!tmp.equals(sharespace +"/") && !tmp.equals(sharespace + "/" + sharespace +"_" + owner +"/"))) {
				
				name = tmp.replace( tmp.substring(0,main.getName().lastIndexOf('/')+1),"");
			}
			else name = tmp;
			
			Component f = null;

			
			
			if(tmp.endsWith("/") && !name.equals(main.getName())) {
				if(main.getName().equals("fallbox")) {
					if(name.equals(owner +"/")) {
						f = new Folder(name,dimension,lastChange,owner);
						System.out.println("aggiungo cartella "+ f.getName()+ " a "+ main.getName() + "\n");
						main.add(f);
						
						refreshContainer(f);
					}	
				}
				else {
					f = new Folder(name,dimension,lastChange,owner);
					System.out.println("aggiungo cartella "+ f.getName()+ " a "+ main.getName() + "\n");
					main.add(f);
				
					refreshContainer(f);
				}
			}
			else if(!name.equals(main.getName()) && !main.getName().equals("fallbox")){
				f = new File(name,dimension,lastChange,owner);
				System.out.println(" aggiungo file " + f.getName()+" a "+ main.getName() + "\n");
				main.add(f);
			}
			
			if(main.getName().equals("can_edit")) {
				f.share(sharespace, true);
			}
			else if(main.getName().equals(owner) && !owner.equals(sharespace) && !f.getName().equals("can_edit")){
				f.share(sharespace,false);
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
