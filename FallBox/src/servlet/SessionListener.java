package servlet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class SessionListener implements HttpSessionListener {

static Map<String, HttpSession> map = new HashMap<String, HttpSession>();
	
    public SessionListener() {
        // TODO Auto-generated constructor stub
    }


    public void sessionCreated(HttpSessionEvent se)  { 
    	map.put(se.getSession().getId(), se.getSession());
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
         map.remove(se.getSession().getId());
    }
    
    public static boolean check(String cookie) {
    	System.out.println("Sto Controllando");
    	if (map.containsKey(cookie)) {
    		return true;
    	}
    	return false;
    }
}
