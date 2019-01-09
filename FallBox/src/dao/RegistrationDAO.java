package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.User;


public class RegistrationDAO {

	private static Connection connection;
	private static String insertString = "insert into User values(?,?)";
	
	public static int register(User u) {
		
		connection = DBConn.getConnection();
		
		if (!checkUserName(u.getEmail())) {   //SE LO USERNAME E' GIA' IN USO
			return -1;
		}

		int status = 0;
		
		try {
			PreparedStatement pStatement = connection.prepareStatement(insertString);
			pStatement.setString(1, u.getEmail());
			pStatement.setString(2, u.getPassword());
			status = pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}

	private static boolean checkUserName(String userName) {
		
		String getStatement = "select * from User where Username = ?";
		
		try {
			PreparedStatement pStatement = connection.prepareStatement(getStatement);
			pStatement.setString(1, userName);
			ResultSet result = pStatement.executeQuery();
			if (result.next()) {   //SE IL RESULTSET NON E' VUOTO VUOL DIRE CHE LO USERNAME E' IN USO
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
