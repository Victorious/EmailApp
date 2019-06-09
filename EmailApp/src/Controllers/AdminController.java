package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Database.DbUtil;
import Models.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AdminController implements Initializable {

//	FXML Variables
	@FXML TextField firstnameTextField;
	@FXML TextField lastnameTextField;
	@FXML TextField usernameTextField;
	@FXML TextField emailTextField;
	@FXML TextField passwordTextField;
	@FXML ComboBox<String> userTypeComboBox;
	@FXML Button updateUserBtn;
	@FXML Button createUserBtn;
	@FXML Button updateUserPasswordBtn;
	
	@FXML TableView<Users> usersTableView;
	@FXML TableColumn<Users, String> usernameColumn;
	@FXML TableColumn<Users, String> emailColumn;
	
//	Variables
	
//	Objects
	DbUtil dbUtil = new DbUtil();
	
//	Updating users data
	public void UpdateUserData() {
		int usertype = 0;
		if (userTypeComboBox.getValue().equals("Admin")) {
			usertype = 1;
		} else if (userTypeComboBox.getValue().equals("User")) {
			usertype = 2;
		}

		Users selectedUser = usersTableView.getSelectionModel().getSelectedItem();
		
		dbUtil.GetUsers();
		Boolean userExist = false; 
		
//		Checking if username or email exist
		for (Users user : dbUtil.getUserList()) {
			if (usernameTextField.getText().equals(user.getUsername()) || emailTextField.getText().equals(user.getEmail())) {
				System.out.println("error");
				Alert updateUserErrorAlert = new Alert(AlertType.INFORMATION,"Username or Email already exist. please try again.",ButtonType.CLOSE);
				updateUserErrorAlert.showAndWait();
				userExist = true;
				return;
			} else {	
				userExist = false;
			}
		}
		
//		Updating user
		if (!userExist) {
			dbUtil.UpdateUser(selectedUser.getId(), usertype, usernameTextField.getText(), firstnameTextField.getText(), lastnameTextField.getText(), emailTextField.getText());	
		}
	}
	
//	Updating users password
	public void UpdateUserPassword() {
		Users selectedUser = usersTableView.getSelectionModel().getSelectedItem();
		dbUtil.UpdateUserPassword(selectedUser.getId(), passwordTextField.getText());
	}
	
//	Creating new User
	public void CreateUser() {
		int userType = 0;
		if (userTypeComboBox.getValue().equals("Admin")) {
			userType = 1;
		} else if (userTypeComboBox.getValue().equals("User")) {
			userType = 2;
		} else {

		}
		
//		Checking if username or email exist already.
		dbUtil.GetUsers();
		Boolean userExist = false; 
		for (Users user : dbUtil.getUserList()) {
			if (usernameTextField.getText().equals(user.getUsername()) || emailTextField.getText().equals(user.getEmail())) {
				System.out.println("error");
				Alert createUserErrorAlert = new Alert(AlertType.INFORMATION,"Username or Email already exist. please try again.",ButtonType.CLOSE);
				createUserErrorAlert.showAndWait();
				userExist = true;
				return;
			} else {	
				userExist = false;
			}
		}
		
//		Creating new user
		if (!userExist) {
			dbUtil.CreateUser(userType, usernameTextField.getText(), passwordTextField.getText(), firstnameTextField.getText(), lastnameTextField.getText(), emailTextField.getText());	
		}
	}
	
//	Selecting User
	public void LoadSelectedUser() {
		if (usersTableView.getSelectionModel().isEmpty()) {
		} else {
			Users selectedUser = usersTableView.getSelectionModel().getSelectedItem();
			firstnameTextField.setText(selectedUser.getFirstname());
			lastnameTextField.setText(selectedUser.getLastname());
			usernameTextField.setText(selectedUser.getUsername());
			emailTextField.setText(selectedUser.getEmail());
			if (selectedUser.getUserType() == 1) {
				userTypeComboBox.setValue("Admin");
			} else if (selectedUser.getUserType() == 2) {
				userTypeComboBox.setValue("User");
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> options = FXCollections.observableArrayList("Admin", "User");
		userTypeComboBox.setItems(options);
		usernameColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("username"));	
		emailColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("email"));
		usersTableView.setItems(dbUtil.GetUsersFXCollection());
	}
}
