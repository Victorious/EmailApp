package Controllers;

import java.io.IOException;

import application.DbUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MainController {
	
	@FXML MenuItem UserSettingsMenuItem;
	@FXML MenuItem AdminSettingsMenuItem;
	@FXML MenuBar menuBar;
	
	public void ShowUserSettingView() {		
//		Stage stage = (Stage) menuBar.getScene().getWindow();
//		stage.close();
		
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
	
	public void ShowAdminView() {
//		Stage stage = (Stage) menuBar.getScene().getWindow();
//		stage.close();
		
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
}
