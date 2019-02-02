package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter (filterName = "LoginFilter", urlPatterns = {"/main.html", "/userPage.jsp"})
public class LoginFilter implements Filter {

    public LoginFilter() 
    {
        // TODO Auto-generated constructor stub
    }

	public void destroy() 
	{
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException 
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		//SE NON C'E' UNA SESSIONE ATTIVA E NON C'E' IL MIO COOKIE DI SESSIONE
		//CREI UNA NUOVA SESSIONE, AGGIUNGI IL COOKIE --> QUESTO VA FATTO NELLA SERVLET!!
		if (req.getSession(false) == null) 
		{
			res.sendRedirect("login.html");
		}
		//SE C'E' UNA SESSIONE
		else if (req.getSession(false) != null && req.getSession(false).getAttribute("User") != "") 
		{
			chain.doFilter(request, response);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException 
	{
		
	}

}
