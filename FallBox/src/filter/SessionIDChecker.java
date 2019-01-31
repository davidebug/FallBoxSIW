package filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class SessionIDChecker {

	static String existsSessionCookie(HttpServletRequest request) 
	{
		Cookie[] cookies = request.getCookies();

		if (cookies != null) 
		{
			for (int i = 0; i < cookies.length; i++) 
			{
				if (cookies[i].getName().equals("SessionID")) 
				{
					return cookies[i].getValue();
				}
			}
		}
			return "0";	
	}
	
}
