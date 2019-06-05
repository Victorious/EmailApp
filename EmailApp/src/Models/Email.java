package Models;

import java.util.Date;

public class Email {
	int id;
	String userFirstname;
	String emailTopic;
	String emailContent;
	Date emailDate;
	
	public Email(int id, String userFirstname, String emailTopic,  String emailContent, Date emailDate) {
		this.id = id;
		this.userFirstname = userFirstname;
		this.emailTopic = emailTopic;
		this.emailContent = emailContent;
		this.emailDate = emailDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserFirstname() {
		return userFirstname;
	}
	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}
	public String getEmailTopic() {
		return emailTopic;
	}
	public void setEmailTopic(String emailTopic) {
		this.emailTopic = emailTopic;
	}
	public Date getEmailDate() {
		return emailDate;
	}
	public void setEmailDate(Date emailDate) {
		this.emailDate = emailDate;
	}
	public String getEmailContent() {
		return emailContent;
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	
}
