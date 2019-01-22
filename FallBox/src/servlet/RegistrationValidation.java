package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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

    public RegistrationValidation() 
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
		//CONTROLLO CHE L'INPUT SIA CORRETTO
		if (checkData(request)) {
			
			User user = new User();
			user.setEmail((String) request.getParameter("email"));
			user.setPassword((String) request.getParameter("password"));
						
			int regStatus = RegistrationDAO.register(user);
			
			//SE LA REGISTRAZIONE E' OK MANDO ALLA PAGINA DI SUCCESSO
			if (regStatus > 0) {
				sendEmail(request.getParameter("email"));
				response.sendRedirect("http://localhost:8080/FallBox/registrationSuccess.html");
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
	
	private boolean checkData(HttpServletRequest request) 
	{
		if (!request.getParameter("password").isEmpty() && (request.getParameter("password").equals(request.getParameter("password-repeat"))))  {
			return true;
		}
		return false;
	}
	
	
	private void sendEmail (String email)
	{
		String username = "cadiscatola@virgilio.it";
		String password = "fallboxcadiscatola";
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "out.virgilio.it");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("cadiscatola@virgilio.it"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("Successfully registered!");
			message.setText("Benvenuto su Fallbox!");

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	//SE ABBIAMO UNA PAGINA DI ERRORE QUESTO METODO LA CHIAMERA' --> FARE LA PAGINA DI ERRORE
	private void returnError(HttpServletResponse response) throws IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("Invalid Username or Password");
	}

}
