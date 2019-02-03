package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

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
			if (dir.getName().equals(user) && dir instanceof Folder)	 
			{
				List<Component> userFiles = ((Folder)dir).getContent();
				
				for (Component file : userFiles)
				{	
					//HO TROVATO IL FILE CHE CERCAVO
					if (file.getName().equals(fileName))
					{
						JSONObject jSonFile = new JSONObject();
						jSonFile.put("name", file.getName());
						jSonFile.put("dimension", file.getDimension());
						jSonFile.put("lastChange", file.getLastChange().toString());//cambio la data in stringa?
						jSonFile.put("owner", file.getOwner());
						
						response.setContentType("application/json");
						PrintWriter out = response.getWriter();
						out.print(jSonFile);
						out.flush();
					}
				}
			}
		}
		
	}

}
