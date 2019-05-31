package Controllers;

import application.DbUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {	
	
	@FXML TextField passwordTextField;
	@FXML TextField usernameTextField;
	
	public void Login() {
		DbUtil.getConnection();
	}
}
