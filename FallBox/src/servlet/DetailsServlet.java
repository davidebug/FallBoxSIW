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
		String fileName = request.getParameter("FILE");
		String user = (String)request.getSession(false).getAttribute("User");
		
		List<Component> mainFolder = Container.getContainer().getContent();  //CONTENUTO DELLA CARTELLONA
		
		for (Component dir : mainFolder)
		{
			//HO TROVATO LA CARTELLA DELL'UTENTE ALL'INTERNO DELLA CARTELLONA
			if (dir.getName().equals(user + "/") && dir instanceof Folder)	 
			{
				List<Component> userFiles = ((Folder)dir).getContent();
				
				for (Component file : userFiles)
				{	
					//HO TROVATO IL FILE CHE CERCAVO
					if (file.getName().equals(fileName))
					{
						List<String> foo = new ArrayList<String>();
						foo.add(file.getName());
						foo.add(file.getDimension().toString());
						foo.add(file.getLastChange().toString());
						foo.add(file.getOwner());
						
						String json = new Gson().toJson(foo );
						
						response.getWriter().println(json);
						response.setContentType("text/plain; charset=UTF-8");
						
						//out.flush();
					}
				}
			}
		}
		
	}

}
