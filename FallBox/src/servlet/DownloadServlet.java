package servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns={"/DownloadServlet/*"})
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public DownloadServlet() 
    {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String path = request.getParameter("filePath");		
		
		ServerHandler.downloadFile(path);
		
		OutputStream out = response.getOutputStream();

        out.flush();
	}

}
