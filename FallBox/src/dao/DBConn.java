package dao;

import java.sql.*;


//FARLA COME SINGLETON?
public class DBConn {  //Classe che crea la connessione con il database

	private static Connection con;
	
	private final static String  url = "jdbc:mysql://localhost/Users";
	
	private final static String user = "root";
	
	private final static String password = "PassWordSQL";
	
	public DBConn() {
		
	}
	
	public static Connection getConnection()  {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
}