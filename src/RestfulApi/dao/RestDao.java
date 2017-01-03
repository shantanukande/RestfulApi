package RestfulApi.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class RestDao {

	private static Connection conn = null;
	private static String url = "jdbc:mysql://localhost:3306/";
	private static String dbName = "OneOps";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String userName = "root";
	private static String password = "Shaan@123";
    
	public static Connection MysqlConn() throws Exception{
		
		if(conn != null){
			return null;
		}
		
		try{
			Class.forName(driver).newInstance();
            conn = DriverManager
                    .getConnection(url + dbName, userName, password);
			
		}catch(Exception E) {
			E.printStackTrace();
		}
		return conn;
	}
}
