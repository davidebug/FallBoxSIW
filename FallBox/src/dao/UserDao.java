package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDao {

	private DBConn dbConn;
	private static String registrationString = "insert into User values(?,?)";
	private static String logInString = "select * from User where Email = ? and Password = ?";
	private static String updatePasswordString = "update User set Password = ? where Email = ?";
	private static String updateEmailString = "update User set Email = ? where Email = ? and Password = ?";
	private static String deleteUser = "delete from User where Email = ?";
	
	public UserDao(DBConn dbConn)
	{
		this.dbConn = dbConn;
	}
	
	private Connection initConnection()
	{
		Connection connection = dbConn.getConnection();
		return connection;
	}
	
	public int register(User user) 
	{
		
		Connection connection = initConnection();
		
		if (!checkEmail(connection, user.getEmail())) 
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

	
	public boolean logIn(User user) 
	{
		
		Connection connection = initConnection();
		
		try 
		{
			PreparedStatement pStatement = connection.prepareStatement(logInString);
			pStatement.setString(1, user.getEmail());
			pStatement.setString(2, user.getPassword());
			
//			//System.out.println("La query --> " + pStatement.toString());
			
			ResultSet result = pStatement.executeQuery();
			if (result.next()) 
			{
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return false;
	}
	
	public boolean updatePassword(User user)
	{
		Connection connection = initConnection();
		
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
	
	public int updateEmail(User user, String newEmail)
	{
		Connection connection = initConnection();
					
		if (!checkEmail(connection, newEmail)) 
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
	
	public boolean deleteUser(String email)
	{
		Connection connection = initConnection();
		
		if (checkEmail(connection, email))
		{
			return false;
		}
		
		try
		{
			PreparedStatement pStatement = connection.prepareStatement(deleteUser);
			pStatement.setString(1, email);
			int status = pStatement.executeUpdate();
			
			if (status != 0)
			{
				return true;	//ELIMINAZIONE AVVENUTA CON SUCCESSO
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	//
	//METODO DI UTILITA'
	//
	private boolean checkEmail(Connection connection, String email) 
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
