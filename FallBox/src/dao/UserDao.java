package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDao {

	private static Connection connection;
	private static String registrationString = "insert into User values(?,?)";
	private static String logInString = "select * from User where Email = ? and Password = ?";
	private static String updatePasswordString = "update User set Password = ? where Email = ?";
	private static String updateEmailString = "update User set Email = ? where Email = ? and Password = ?";
	
	private static void initConnection()
	{
		connection = DBConn.getConnection();
	}
	
	public static int register(User user) 
	{
		
		initConnection();
		
		if (!checkEmail(user.getEmail())) 
		{   //SE LO USERNAME E' GIA' IN USO
			return -1;
		}

		int status = 0;
		
		try 
		{
			PreparedStatement pStatement = connection.prepareStatement(registrationString);
			pStatement.setString(1, user.getEmail());
			pStatement.setString(2, user.getPassword());
			status = pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}

	
	public static boolean logIn(User user) 
	{
		
		initConnection();
		
		try 
		{
			PreparedStatement pStatement = connection.prepareStatement(logInString);
			pStatement.setString(1, user.getEmail());
			pStatement.setString(2, user.getPassword());
						
			ResultSet result = pStatement.executeQuery();
			if (result.next()) 
			{
				System.out.println("TROVATO");
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return false;
	}
	
	public static boolean updatePassword(User user)
	{
		initConnection();
		
		try 
		{
			PreparedStatement pStatement = connection.prepareStatement(updatePasswordString);
			pStatement.setString(1, user.getPassword());
			pStatement.setString(2, user.getEmail());
						
			int result = pStatement.executeUpdate();
			if (result != 0)
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static int updateEmail(User user, String newEmail)
	{
		initConnection();
					
		if (!checkEmail(newEmail)) 
		{   //SE LO USERNAME E' GIA' IN USO
			return -1;
		}
		
		
		try 
		{
			PreparedStatement pStatement = connection.prepareStatement(updateEmailString);
			pStatement.setString(1, newEmail);
			pStatement.setString(2, user.getEmail());
			pStatement.setString(3,  user.getPassword());
			int result = pStatement.executeUpdate();
			if (result != 0)
			{
				return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	//
	//METODO DI UTILITA'
	//
	public static boolean checkEmail(String email) 
	{
		
		String getStatement = "select * from User where Email = ?";
		
		try 
		{
			PreparedStatement pStatement = connection.prepareStatement(getStatement);
			pStatement.setString(1, email);
			ResultSet result = pStatement.executeQuery();
			if (result.next()) 		//SE IL RESULTSET NON E' VUOTO VUOL DIRE CHE LO USERNAME E' IN USO
			{   
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
}
