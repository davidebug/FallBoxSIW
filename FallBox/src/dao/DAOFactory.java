package dao;

public class DAOFactory {

	private static DBConn dbConn;
	
	static 
	{
		dbConn = new DBConn();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	
	public static UserDao getUserDao()
	{
		return new UserDao(dbConn);
	}
	
}
