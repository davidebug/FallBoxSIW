package servlet;

import java.io.IOException;
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

import org.apache.commons.lang3.RandomStringUtils;

import dao.UserDao;
import model.User;

@WebServlet(urlPatterns={"/ChangePassword/*"})
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ChangePassword() 
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
		String newPassword = generatePassword();
		String email = request.getParameter("email");
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(newPassword);
		
		if (UserDao.updatePassword(user))
		{
			response.sendRedirect("passwordSucess.html");
			sendEmail(email, newPassword);
		}
		else
		{
			//PAGINA DI ERRORE
		}
	}
	
	private String generatePassword()
	{
		return RandomStringUtils.random(32, 0, 20, true, true, 
				"abcdefghilmnopqrstuzABCDEFGHILMNOPQRSTUVZ1234567890!@#?^|!.<>-_".toCharArray());
	}
	
	//
	//PENSARE SE METTERE QUESTO METODO IN UNA CLASSE A PARTE VISTO CHE E' UGUALE A QUELLO DI REGISTRATIONVAL.
	//MAGARI PASSARGLI, OLTRE ALL'EMAIL, IL TESTO E L'OGGETTO DA INVIARE
	//
	private void sendEmail(String email, String newPassword)
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
			message.setSubject("Password Changed");
			message.setText("Your new password is " + newPassword);

			Transport.send(message);
			

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
