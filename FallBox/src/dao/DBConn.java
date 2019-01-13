package dao;

import java.sql.*;

//FARLA COME SINGLETON?
public class DBConn {  //Classe che crea la connessione con il database

	private static Connection con;
	
	private final static String  url = "jdbc:mysql://cadiscatola.cki9ctegjpnd.eu-west-3.rds.amazonaws.com/cadiscatola";
	
	private final static String user = "cadiscatola";
	
	private final static String password = "cadiscatola";
	
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