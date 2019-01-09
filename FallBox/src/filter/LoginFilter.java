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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@WebFilter (filterName = "LoginFilter", urlPatterns = {"/MainPage.html"})
public class LoginFilter implements Filter {


    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		//SE NON C'E' UNA SESSIONE ATTIVA E NON C'E' IL MIO COOKIE DI SESSIONE
		//CREI UNA NUOVA SESSIONE, AGGIUNGI IL COOKIE --> QUESTO VA FATTO NELLA SERVLET!!
		if (req.getSession(false) == null && existsSessionCookie(req) == "0") {
			req.getRequestDispatcher("/LoginPage.html").forward(request, response);
		}
		//SE C'E' UNA SESSIONE
		else if (req.getSession(false) != null) {
			chain.doFilter(request, response);
		}
		//}
		//O SE C'E' IL MIO COOKIE DI SESSIONE
		else {
			//SE QUESTO STESSO COOKIE SI TROVA NELLA MAPPA CONTENUTA NEL LISTENER VUOL DIRE CHE LA SESSIONE
			//E' ATTIVA
			if (SessionListener.check(existsSessionCookie(req))) {
				chain.doFilter(request, response);
			}
			//ALTRIMENTI NON E' PIU' ATTIVA --> RIMUOVIAMO IL COOKIE
			else {
				//RIMUOVI IL COOKIE
			}
		}
	}
	
	private String existsSessionCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("SessionID")) {
					return cookies[i].getValue();
				}
			}
		}
			return "0";
			
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
