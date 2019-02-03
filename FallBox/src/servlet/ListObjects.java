package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Component;
import model.Container;
import model.File;
import model.Folder;

@WebServlet(urlPatterns={"/ListObjects/*"})
public class ListObjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListObjects() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{		
		//RICEVE MYSHAREDSPACE O SHARED WITH ME
		String kindOfFiles = request.getParameter("SharedSpace");
		
		ArrayList<File> userFiles = new ArrayList<File>();
	
		String user = (String)request.getSession(false).getAttribute("User");
		
		List<Component> fallBox = Container.getContainer().getContent();
		
		for (Component c : fallBox)
		{
			if (c.getName().equals(user + "/"))
			{
				if (kindOfFiles.equals("MySharedSpace"))
				{
					getFiles(userFiles, user, (Folder) c, true);
				}
				else
				{
					getFiles(userFiles, user, (Folder) c, false);
				}
			}
		}
		
		List<String> files = new ArrayList<String>(); 
		
		for (File file : userFiles)
			files.add(file.getName());
		
		
		String json = new Gson().toJson(files);
		
		response.getWriter().println(json);
		response.setContentType("text/plain; charset=UTF-8");
	}
	
	
	private void getFiles(ArrayList<File> files, String user, Folder folder, boolean owner)
	{
		List<Component> content = folder.getContent();
		
		for (Component c : content)
		{
			if (c instanceof File)
			{
				if (owner == true && (((File)c).getOwner()).equals(user))
				{
					files.add((File) c);
				}
				else if (owner == false && !(((File)c).getOwner()).equals(user))
				{
					files.add((File) c);
				}
			}
			else if (c instanceof Folder)
			{
				getFiles(files, user, (Folder)c, owner);
			}
		}
		
	}

}
