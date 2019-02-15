package servlet;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileCreator 
{
	File file = null;
	
	DiskFileItemFactory factory = new DiskFileItemFactory();
	
	ServletFileUpload upload = new ServletFileUpload(factory);
	
	public void createFile(String path, List<FileItem> fileItems)
	{
		String pathName = path + "/";
		
		
		try {
						
			Iterator<FileItem> i = fileItems.iterator();
	         
	         while (i.hasNext()) 
	         {
	        	 FileItem fi = (FileItem)i.next();
	        	 
	        	 //SE L'ELEMENTO MANDATO E' UN FILE
	        	 if ( !fi.isFormField() ) 
	        	 {
	        		 System.out.println("ITEM-->"+ fi.getName());
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public DiskFileItemFactory getFactory() {
		return factory;
	}

	public void setFactory(DiskFileItemFactory factory) {
		this.factory = factory;
	}

	public ServletFileUpload getUpload() {
		return upload;
	}

	public void setUpload(ServletFileUpload upload) {
		this.upload = upload;
	}
	
	
	
	
}
