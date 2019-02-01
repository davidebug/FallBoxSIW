package servlet;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileCreator 
{
	public static void createFile(String path, List<FileItem> fileItems)
	{
		File file = null;
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		String pathName = path + "/";
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
						
			Iterator i = fileItems.iterator();
	         
	         while (i.hasNext()) 
	         {
	        	 FileItem fi = (FileItem)i.next();
	        	 
	        	 //SE L'ELEMENTO MANDATO E' UN FILE
	        	 if ( !fi.isFormField() ) 
	        	 {
	        		 String fileName = fi.getName();

	        		 if (fileName.lastIndexOf("\\") >= 0) 
	        		 {
	        			 file = new File ( pathName + fileName.substring(fileName.lastIndexOf("/")));
	        			 
	        		 } 
	        		 else 
	        		 {
	                     file = new File( pathName + fileName.substring(fileName.lastIndexOf("/")+1));
	                 }
	        		 fi.write(file);
	        	 }
	         }
		}  
		catch(Exception ex) 
		{
            System.out.println(ex);
         }
	}
	
	
}
