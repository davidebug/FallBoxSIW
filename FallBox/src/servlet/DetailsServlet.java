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
import model.Folder;

@WebServlet(urlPatterns={"/DetailsServlet/*"})
public class DetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DetailsServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		List<String> foo = new ArrayList<String>();
		System.out.println(request.getParameter("FILE"));
		String fileName = request.getParameter("FILE");
		String user = (String)request.getSession(false).getAttribute("User");
		
		List<Component> mainFolder = Container.getContainer().getContent();
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
						
						foo.add(file.getName());
						foo.add(file.getDimension().toString());
						foo.add(file.getLastChange().toString());
						foo.add(file.getOwner());
						
						System.out.println(file.getName());
						break;
						//out.flush();
					}
					else if(file instanceof Folder) {
						foo = getDetails(fileName,file);
						
					}
					
					
					
				}
			}
			
		}
		String json = new Gson().toJson(foo );
		response.getWriter().println(json);
		response.setContentType("text/plain; charset=UTF-8");
		System.out.println(json);
	}
			
	public 	List<String> getDetails(String fileName,Component c){
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
		
			//HO TROVATO LA CARTELLA DELL'UTENTE ALL'INTERNO DELLA CARTELLONA

}
