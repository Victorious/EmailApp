package Controllers;

import java.io.IOException;

import Database.DbUtil;
import Models.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {	
	
//	FXML Variables
	@FXML TextField passwordTextField;
	@FXML TextField usernameTextField;
	@FXML Button loginBtn;
	
//	Variables
	String username = "username";
	String password = "password";
	DbUtil dbUtil = new DbUtil();
	public void Login() {
//		Getting database connection on login button press
		DbUtil.getConnection();
		
		dbUtil.GetUsers();
		Boolean userNotFound = true;
		
//		Looping through user list
		for (Users users : dbUtil.getUserList()) {		
			//		Checking if username and password is correct
			if (usernameTextField.getText().equals(users.getUsername()) && passwordTextField.getText().equals(users.getPassword())) {
				Users.getCurrentUserList().add(new Users(users.getId(), users.getUserType(), users.getUsername(), users.getPassword(), users.getFirstname(), users.getLastname(), users.getEmail()));
			//			Closing Login View
				Stage stage = (Stage) loginBtn.getScene().getWindow();
				stage.close();
				
	//			Showing Main View
				try {
					Stage primaryStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/Views/MainView.fxml"));
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.show();
					userNotFound = false;
					return;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}  
		}
		
//		Checking if username or password is not matching
		if (userNotFound) {
			System.out.println("Username or Password is incorrect");
		}
	}	
}
