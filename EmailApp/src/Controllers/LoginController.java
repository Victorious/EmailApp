package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {	
	
	@FXML TextField passwordTextField;
	@FXML TextField usernameTextField;
	
	public void Login() {
		System.out.println(usernameTextField.getText());
		System.out.println(passwordTextField.getText());
	}
}
