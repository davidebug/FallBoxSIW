package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;

//SE ARRIVO NELLA SERVLET, VUOL DIRE CHE LE CREDENZIALI VANNO CONTROLLATE PERCHE' NON ESISTE UNA SESSION

@WebServlet(urlPatterns={"/LoginServlet/*"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (LoginDao.logIn(email, password)) {   //SE LE CREDENZIALI SONO OK
			HttpSession session = request.getSession();			//CREO UNA NUOVA SESSIONE;
			Cookie sessionCookie = new Cookie("SessionID", session.getId());
			sessionCookie.setPath("/");
			sessionCookie.setMaxAge(60*60*60);;
			response.addCookie(sessionCookie); //STESSA SCADENZA DELLA SESSION?
			response.sendRedirect(request.getContextPath() + "/main.html");	//MANDO L'UTENTE ALLA PAGINA PRINCIPALE
		}
		else {
			;
			//PAGINA DI ERRORE PER IL LOGIN
		}
		
	}
	
	


}
