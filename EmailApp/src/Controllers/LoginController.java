package Controllers;

import java.io.IOException;

import application.DbUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {	
	
	@FXML TextField passwordTextField;
	@FXML TextField usernameTextField;
	@FXML Button loginBtn;
	
	public void Login() {
		DbUtil.getConnection();
		Stage stage = (Stage) loginBtn.getScene().getWindow();
		stage.close();
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/Views/MainView.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
