package dao;

import java.sql.*;

//FARLA COME SINGLETON?
public class DBConn {  //Classe che crea la connessione con il database

	private static Connection con = null;
	
	private final static String  url = "jdbc:mysql://fallbox.cqlxx6nhtkcr.eu-central-1.rds.amazonaws.com/cadiscatola";
	
	private final static String user = "fallbox";
	
	private final static String password = "cadiscatola";
	
	private DBConn() 
	{	
	}
	
	public static Connection getConnection()  
	{
		
		if (con != null)
		{
			return con;
		}
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection(url, user, password);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
}