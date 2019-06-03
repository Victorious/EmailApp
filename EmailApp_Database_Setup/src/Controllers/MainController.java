package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.DbUtil;

public class MainController {
	
//	Variables
	Connection con;
	ResultSet rs;
	Statement statm;
	
//	start the setup of database
	public void StartSetup() throws SQLException {
		con = DbUtil.getConnection();
		statm = con.createStatement();
		addDatabase();
		addTables();
		addValues();
	}
	
//	adding database
	private void addDatabase() {
		try {
			statm.executeUpdate("CREATE SCHEMA `emailapp`");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	adding tables 
	private void addTables() {
		try {
//			adding table 'users'
			statm.executeUpdate("CREATE TABLE `emailapp`.`users` (" + 
					"  `id` INT NOT NULL AUTO_INCREMENT," + 
					"  `user_type` INT NOT NULL," + 
					"  `username` VARCHAR(100) NOT NULL," + 
					"  `password` VARCHAR(100) NULL," + 
					"  `firstname` VARCHAR(100) NULL," + 
					"  `lastname` VARCHAR(100) NOT NULL," + 
					"  `email` VARCHAR(100) NOT NULL," + 
					"  PRIMARY KEY (`id`)," + 
					"  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE," + 
					"  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)");
			
//			adding table 'emails'
			statm.executeUpdate("CREATE TABLE `emailapp`.`emails` (" + 
					"  `id` INT NOT NULL AUTO_INCREMENT," + 
					"  `user_id` VARCHAR(100) NULL," + 
					"  `email_topic` VARCHAR(255) NULL," + 
					"  `email_content` LONGTEXT NULL," + 
					"  `email_date` DATETIME NULL," + 
					"  PRIMARY KEY (`id`))");
			
//			adding table 'user_types'
			statm.executeUpdate("CREATE TABLE `emailapp`.`user_types` (" + 
					"  `id` INT NOT NULL AUTO_INCREMENT," +  
					"  `user_type` VARCHAR(45) NULL," + 
					"  PRIMARY KEY (`id`))");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	adding values
	private void addValues() {
		try {
//			adding test email value
			statm.executeUpdate("INSERT INTO `emailapp`.`emails` (`user_id`, `email_topic`, `email_content`, `email_date`)"
					+ " VALUES ('1', 'Test Topic', 'Test Content Test Content Test Content Test Content Test Content Test Content Test Content Test Content Test Content', '2019-06-03 20:44:30')");
		
//			adding test user_types value
			statm.executeUpdate("INSERT INTO `emailapp`.`user_types` (`id`, `user_type`) "
					+ "VALUES ('1', 'Admin')," + 
					"('2', 'User')");
			
//			adding test users value
			statm.executeUpdate("INSERT INTO `emailapp`.`users` (`id`, `user_type`, `username`, `password`, `firstname`, `lastname`, `email`) "
					+ "VALUES ('1', '1', 'admin', 'password', 'admin', 'admin', 'admin@admin.com')," + 
					"('2', '2', 'user', 'password', 'user', 'user', 'user@user.com')");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
