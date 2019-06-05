package Models;

import java.util.ArrayList;

public class Users {
	
	static ArrayList<Users> currentUserList = new ArrayList<>();
	int id;
	int userType;
	String username;
	String password;
	String firstname;
	String lastname;
	String email;

	public Users(int id, int userType, String username, String password, String firstname, String lastname, String email) {
		this.id = id;
		this.userType = userType;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String userame) {
		this.username = userame;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public static ArrayList<Users> getCurrentUserList() {
		return currentUserList;
	}

}
