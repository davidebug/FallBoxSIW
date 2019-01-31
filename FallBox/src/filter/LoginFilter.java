package filter;

import java.io.IOException;
import servlet.SessionListener;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter (filterName = "LoginFilter", urlPatterns = {"/main.jsp"})
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
		//SE NON C'E' UNA SESSIONE ATTIVA E NON C'E' IL MIO COOKIE DI SESSIONE
		//CREI UNA NUOVA SESSIONE, AGGIUNGI IL COOKIE --> QUESTO VA FATTO NELLA SERVLET!!
		if (req.getSession(false) == null && SessionIDChecker.existsSessionCookie(req) == "0") 
		{
			req.getRequestDispatcher("/login.html").forward(request, response);
		}
		//SE C'E' UNA SESSIONE
		else if (req.getSession(false) != null) 
		{
			chain.doFilter(request, response);
		}
		//}
		//O SE C'E' IL MIO COOKIE DI SESSIONE
		else {
			//SE QUESTO STESSO COOKIE SI TROVA NELLA MAPPA CONTENUTA NEL LISTENER VUOL DIRE CHE LA SESSIONE
			//E' ATTIVA
			String id = SessionIDChecker.existsSessionCookie(req);
			if (SessionListener.check(SessionIDChecker.existsSessionCookie(req))) 
			{
				HttpSession session = req.getSession();
				session.setAttribute("User", SessionListener.getEmail(id));
				chain.doFilter(request, response);
			}
			//ALTRIMENTI NON E' PIU' ATTIVA --> RIMUOVIAMO IL COOKIE
			else {
				//RIMUOVI IL COOKIE
			}
		}
	}


	public void init(FilterConfig fConfig) throws ServletException 
	{
		
	}

}
