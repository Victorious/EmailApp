package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Database.DbUtil;
import Models.Users;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	
	
	DbUtil dbUtil = new DbUtil(); 
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
			for (Users user : Users.getCurrentUserList()) {
				if (newPassTextField.getText().equals(user.getPassword())) {
					Alert oldPassErrorAlert = new Alert(AlertType.INFORMATION,"Password is same as old, please type a new password",ButtonType.CLOSE);
					oldPassErrorAlert.showAndWait();
					return;
				} else {
					dbUtil.UpdateUserPassword(user.getId(), newPassTextField.getText());
					Alert successPassErrorAlert = new Alert(AlertType.INFORMATION,"Password has been updated.",ButtonType.CLOSE);
					successPassErrorAlert.showAndWait();
					oldPassTextField.setVisible(false);
					oldPassText.setVisible(false);
					newPassTextField.setVisible(false);
					newPassText.setVisible(false);
					confirmPassTextField.setVisible(false);
					confirmPassText.setVisible(false);
					commitChangePassBtn.setVisible(false);
				}
			}
		} else {
			Alert noPassMatchErrorAlert = new Alert(AlertType.INFORMATION,"Password didn't match. please try again",ButtonType.CLOSE);
			noPassMatchErrorAlert.showAndWait();
		}
	}
	
//	Enabling change settings feature
	public void ChangeSettings() {	
		if (!usernameTextField.getText().isEmpty() || !!emailTextField.getText().isEmpty()) {
			if (changeSettingsBtn.getText().contains("Change Settings")) {
	//			Setting editable to true
				firstnameTextField.setEditable(true);
				lastnameTextField.setEditable(true);
				usernameTextField.setEditable(true);
				emailTextField.setEditable(true);
				changeSettingsBtn.setText("Commit Settings");
			} else if (changeSettingsBtn.getText().contains("Commit Settings")) {
	//			Commiting changes
				for (Users user : Users.getCurrentUserList()) {
					dbUtil.UpdateUser(user.getId(), user.getUserType(), usernameTextField.getText(), firstnameTextField.getText(), lastnameTextField.getText(), emailTextField.getText());	
				}
				firstnameTextField.setEditable(false);
				lastnameTextField.setEditable(false);
				usernameTextField.setEditable(false);
				emailTextField.setEditable(false);
				changeSettingsBtn.setText("Change Settings");
				Alert changeSettingErrorAlert = new Alert(AlertType.INFORMATION,"Settings has been changed.",ButtonType.CLOSE);
				changeSettingErrorAlert.showAndWait();
			}
			else {
	//			Error message
				Alert errorSettingsErrorAlert = new Alert(AlertType.INFORMATION,"Something went wrong, please try again.",ButtonType.CLOSE);
				errorSettingsErrorAlert.showAndWait();
			}
		} else {
			Alert errorEmptyErrorAlert = new Alert(AlertType.INFORMATION,"Username and email can't be empty.",ButtonType.CLOSE);
			errorEmptyErrorAlert.showAndWait();
		}
	}
	
//	Setting values on Initilizing
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
//		Loading values from user
		for (Users user : Users.getCurrentUserList()) {
			firstnameTextField.setText(user.getFirstname());
			lastnameTextField.setText(user.getLastname());
			usernameTextField.setText(user.getUsername());
			emailTextField.setText(user.getEmail());
		}
		
		oldPassTextField.setVisible(false);
		oldPassText.setVisible(false);
		newPassTextField.setVisible(false);
		newPassText.setVisible(false);
		confirmPassTextField.setVisible(false);
		confirmPassText.setVisible(false);
		commitChangePassBtn.setVisible(false);
	}
}
