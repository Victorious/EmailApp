package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class UserController implements Initializable {

//	FXML Variables
	@FXML TextField oldPassTextField;
	@FXML Text oldPassText;
	@FXML TextField newPassTextField;
	@FXML Text newPassText;
	@FXML TextField confirmPassTextField;
	@FXML Text confirmPassText;
	@FXML TextField firstnameTextField;
	@FXML TextField lastnameTextField;
	@FXML TextField usernameTextField;
	@FXML TextField emailTextField;
	@FXML Button changeSettingsBtn;
	@FXML Button commitChangePassBtn;
	
//	Variables
	
//	Enable Change Password Feature
	public void ChangePassword() {
		oldPassTextField.setVisible(true);
		oldPassText.setVisible(true);
		newPassTextField.setVisible(true);
		newPassText.setVisible(true);
		confirmPassTextField.setVisible(true);
		confirmPassText.setVisible(true);
		commitChangePassBtn.setVisible(true);
	}
	
//	Commiting Password to database
	public void CommitChangePassword() {
		if (newPassTextField.getText().equals(confirmPassTextField.getText())) {
			System.out.println("Congratulations, password changed");
			oldPassTextField.setVisible(false);
			oldPassText.setVisible(false);
			newPassTextField.setVisible(false);
			newPassText.setVisible(false);
			confirmPassTextField.setVisible(false);
			confirmPassText.setVisible(false);
			commitChangePassBtn.setVisible(false);
		} else {
			System.out.println(newPassTextField.getText() + " " + confirmPassTextField.getText());
			System.out.println("Password didn't match, Password not changed");
		}
	}
	
//	Enabling change settings feature
	public void ChangeSettings() {
		String change = "Change Settings";
		
		if (changeSettingsBtn.getText().contains("Change Settings")) {
//			Setting editable to true
			firstnameTextField.setEditable(true);
			lastnameTextField.setEditable(true);
			usernameTextField.setEditable(true);
			emailTextField.setEditable(true);
			changeSettingsBtn.setText("Commit Settings");
		} else if (changeSettingsBtn.getText().contains("Commit Settings")) {
//			Commiting changes
			firstnameTextField.setEditable(false);
			lastnameTextField.setEditable(false);
			usernameTextField.setEditable(false);
			emailTextField.setEditable(false);
			changeSettingsBtn.setText("Change Settings");
			System.out.println("Settings has been changed");
		}
		else {
//			Error message
			System.out.println("Something went wrong");
		}
	}
	
//	Setting values on Initilizing
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		oldPassTextField.setVisible(false);
		oldPassText.setVisible(false);
		newPassTextField.setVisible(false);
		newPassText.setVisible(false);
		confirmPassTextField.setVisible(false);
		confirmPassText.setVisible(false);
		commitChangePassBtn.setVisible(false);
	}
}
