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
       
	ArrayList<Component> userFiles = new ArrayList<Component>();
    public ListObjects() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Container c = new Container();
		doPost(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{		
		//RICEVE MYSHAREDSPACE O SHARED WITH ME
		String kindOfFiles = request.getParameter("currentFolder");
		System.out.println(kindOfFiles);
		userFiles = new ArrayList<Component>();
	
		String user = (String)request.getSession(false).getAttribute("User");
		
		List<Component> fallBox = Container.getContainer().getContent();
		
		for (Component c : fallBox)
		{
			if (c.getName().equals(user + "/"))
			{
				if (kindOfFiles.equals("mySharedSpace"))
				{
					getFiles(user,c, true);
				}
				else
				{
					getFiles(user, c, false);
				}
			}
		}
		
		 
		List<String> files = new ArrayList<String>();
		for (Component file : userFiles)
			files.add(file.getName());
		
		
		String json = new Gson().toJson(files);
		
		response.getWriter().println(json);
		System.out.println(json);
		response.setContentType("text/plain; charset=UTF-8");
	}
	
	
	private void getFiles(String user, Component folder, boolean owner)
	{
		
		List<Component> content = folder.getContent();
		
		for (Component c : content)
		{
			if (c instanceof File)
			{
				if (owner == true && (c.getOwner()).equals(user))
				{
					userFiles.add( c);
				}
				else if (owner == false && !(c.getOwner().equals(user)))
				{
					userFiles.add( c);
				}
			}
			else if (c instanceof Folder)
			{
				
				getFiles(user, c, owner);
				if (owner == true && (c.getOwner()).equals(user))
				{
					userFiles.add( c);
				}
				else if (owner == false && !(c.getOwner().equals(user)))
				{
					userFiles.add( c);
				}
			}
		}
		
	}

}
