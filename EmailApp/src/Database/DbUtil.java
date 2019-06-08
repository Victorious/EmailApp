package Database;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Models.Email;
import Models.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DbUtil {

//	Variables
	private static String user = "root";
	private static String pass = "calvin";
	private static String dbName = "emailapp";
	private static String dbHost = "jdbc:mysql://localhost:3306/";
	
	private static ResultSet rs;
	private static Statement statm;
	private static volatile Connection con = null;
	ArrayList<Users> userList = new ArrayList<>();
	ObservableList<Users> userFXList = FXCollections.observableArrayList();
	ObservableList<Email> emailFXList = FXCollections.observableArrayList();
	
//	Creating connection towards database
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
	
//	Getting emails to FXCollection List
	public ObservableList<Email> GetEmailsToFXCollection(){
		try {
			for (Users currentUser : Users.getCurrentUserList()) {
				//System.out.println(currentUser.getId());
				String sql = "SELECT emails.id, "
						+ "emails.user_id, "
						+ "emails.email_topic, "
						+ "emails.email_content, "
						+ "emails.email_date, "
						+ "users.firstname "
						+ "FROM emails, users WHERE users.id = " + currentUser.getId() + " AND emails.user_id = " + currentUser.getId() + "";	
				rs = statm.executeQuery(sql);
			}
			while (rs.next()) {
				int id = rs.getInt("emails.id");
				String userFirstname = rs.getString("users.firstname");
				String emailTopic = rs.getString("email_topic");
				String emailContent = rs.getString("email_content");
				Date emailDate = rs.getDate("email_date");
				emailFXList.add(new Email(id, userFirstname, emailTopic, emailContent, emailDate));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emailFXList;
	}

//	Update User Password
	public void UpdateUserPassword(int id, String password) {
		String updateUserSql = "UPDATE `users` "
		 		+ "SET `password` = '" + password + "'" 
		 		+ "WHERE (`id` = '" + id + "')";
try {
	statm.executeUpdate(updateUserSql);
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
	
//	Update User
	public void UpdateUser(int id, int usertype, String username ,String firstname, String lastname, String email) {
		String updateUserSql = "UPDATE `users` "
				 		+ "SET `user_type` = '" + usertype + "', "
				 		+ "`username` = '" + username + "', "
				 		+ "`firstname` = '" + firstname + "', "
				 		+ "`lastname` = '" + lastname + "', "
				 		+ "`email` = '" + email + "' "
				 		+ "WHERE (`id` = '" + id + "')";
		try {
			statm.executeUpdate(updateUserSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	Creating User
	public void CreateUser(int usertype,String username, String password ,String firstname, String lastname, String email) {
		String createUserSql = "INSERT INTO `users` (`user_type`, `username`, `password`, `firstname`, `lastname`, `email`)"
				+ "VALUES ('" + usertype + "', '" + username + "', '" + password + "', '" + firstname + "', '" + lastname + "', '"+ email +"')";
		
		try {
			statm.executeUpdate(createUserSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	Getting Users to FXCollection
	public ObservableList<Users> GetUsersFXCollection(){
		try {
			rs = statm.executeQuery("SELECT * FROM users");
			while (rs.next()) {
				int id = rs.getInt("id");
				int userType = rs.getInt("user_type");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String email = rs.getString("email");
				userFXList.add(new Users(id, userType, username, password, firstname, lastname, email));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userFXList;	
	}
	
//	Getting Users
	public ArrayList<Users> GetUsers() {
		try {
			rs = statm.executeQuery("SELECT * FROM users");
			while (rs.next()) {
				int id = rs.getInt("id");
				int userType = rs.getInt("user_type");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String email = rs.getString("email");
				userList.add(new Users(id, userType, username, password, firstname, lastname, email));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

//	Getters and Setters
	public static Connection getConnection() {
		return Connection();	
	}
	
	public ArrayList<Users> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<Users> userList) {
		this.userList = userList;
	}
}
