package servlet;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RegistrationDAO;
import logic.User;

@WebServlet(urlPatterns={"/RegistrationValidation/*"})
public class RegistrationValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegistrationValidation() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		System.out.println(request.getParameter("password") + "\n" + request.getParameter("password-repeat"));
		
		//CONTROLLO CHE L'INPUT SIA CORRETTO
		if (checkData(request)) {
			
			User user = new User();
			user.setEmail((String) request.getParameter("email"));
			user.setPassword((String) request.getParameter("password"));
						
			int regStatus = RegistrationDAO.register(user);
			
			//SE LA REGISTRAZIONE E' OK MANDO ALLA PAGINA DI SUCCESSO
			if (regStatus > 0) {
				request.getRequestDispatcher("/registrationSuccess.html").forward(request, response);
			}
			else if (regStatus == -1) {
				//EMAIL GIA' IN USO
			}
			else {
				;//ALTRI ERRORI
			}
		}
		else {
			returnError(response);
		}
	}
	
	private boolean checkData(HttpServletRequest request) {
		if (!request.getParameter("password").isEmpty() && (request.getParameter("password").equals(request.getParameter("password-repeat"))))  {
			return true;
		}
		return false;
	}
	
	//SE ABBIAMO UNA PAGINA DI ERRORE QUESTO METODO LA CHIAMERA' --> FARE LA PAGINA DI ERRORE
	private void returnError(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("Invalid Username or Password");
	}

}
