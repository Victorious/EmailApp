package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import Database.DbUtil;
import Models.Email;
import Models.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainController implements Initializable {
	
//	FXML Variables
	@FXML MenuItem UserSettingsMenuItem;
	@FXML MenuItem AdminSettingsMenuItem;
	@FXML MenuBar menuBar;
	@FXML TableView<Email> emailTableView;
	@FXML TableColumn<Email, String> emailTopicColumn;
	@FXML TableColumn<Email, String> emailFromColumn;
	@FXML TableColumn<Email, Date> emailDateColumn;
	@FXML TableColumn<Email, String> emailContentColumn;
	
//	Variables
	DbUtil dbUtil = new DbUtil();
//	Showing User Setting View
	public void ShowUserSettingView() {		
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/Views/UserView.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	Showing Admin View
	public void ShowAdminView() {
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/Views/AdminView.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	Setting values on Initilizing
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		for (Users user : Users.getCurrentUserList()) {		
	//		Checking if userType is admin or not
			if (user.getUserType() == 1) {
				UserSettingsMenuItem.setVisible(true);
				AdminSettingsMenuItem.setVisible(true);
			} else if (user.getUserType() == 2) {
				UserSettingsMenuItem.setVisible(true);
				AdminSettingsMenuItem.setVisible(false);
			}
		} 
		emailTopicColumn.setCellValueFactory(new PropertyValueFactory<Email, String>("emailTopic"));
		emailFromColumn.setCellValueFactory(new PropertyValueFactory<Email, String>("userFirstname"));
		emailDateColumn.setCellValueFactory(new PropertyValueFactory<Email, Date>("emailDate"));
		emailContentColumn.setCellValueFactory(new PropertyValueFactory<Email, String>("emailContent"));
		emailTableView.setItems(dbUtil.GetEmailsToFXCollection());
		
	}
}
