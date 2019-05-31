package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {

	private static String user = "root";
	private static String pass = "calvin";
	private static String dbName = "emailapp";
	private static String dbHost = "jdbc:mysql://localhost:3306/";
	
	private static ResultSet rs;
	private static Statement statm;
	private static volatile Connection con = null;
	
	private static Connection Connection() {
		try {
			con = DriverManager.getConnection(dbHost + dbName, user, pass);
			statm = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;	
	}
	
	public static Connection getConnection() {
		return Connection();	
	}
}
