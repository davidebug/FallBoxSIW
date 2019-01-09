package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

	private static Connection connection;
	private static String queryString = "select * from User where Username = ? and Password = ?";
	
	public static boolean logIn(String email, String password) {
		
		connection = DBConn.getConnection();
		try {
			PreparedStatement pStatement = connection.prepareStatement(queryString);
			pStatement.setString(1, email);
			pStatement.setString(2, password);
			ResultSet result = pStatement.executeQuery();
			if (result.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return false;
	}
	
}
