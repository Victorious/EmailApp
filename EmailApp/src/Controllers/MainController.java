package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.DbUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MainController implements Initializable {
	
//	FXML Variables
	@FXML MenuItem UserSettingsMenuItem;
	@FXML MenuItem AdminSettingsMenuItem;
	@FXML MenuBar menuBar;
	
//	Variables
	
	
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
		
		String userTpe = "admin";
		
//		Checking if userType is admin or not
		if (userTpe == "admin") {
			UserSettingsMenuItem.setVisible(true);
			AdminSettingsMenuItem.setVisible(true);
		} else if (userTpe == "user") {
			UserSettingsMenuItem.setVisible(true);
			AdminSettingsMenuItem.setVisible(false);
		}
	}
}
