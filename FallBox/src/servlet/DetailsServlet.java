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
		List<String> details = new ArrayList<String>();
		String fileName = request.getParameter("FILE");
		
		String user = (String)request.getSession(false).getAttribute("User");
		
		List<Component> mainFolder = Container.getContainer().getContent(); //AGGIUNTO QUA
		
		ServerHandler.getListOfDetails(details, mainFolder, fileName, user);
		
		String json = new Gson().toJson(details);
		response.getWriter().println(json);
		response.setContentType("text/plain; charset=UTF-8");
		System.out.println(json);
	}
		
}
