package org.automationcart.pojos;

public class InvalidUsers {
	
	private String emailAddress;
	private String password;
	
	public InvalidUsers(String emailAddress, String password) {
		super();
		this.emailAddress = emailAddress;
		this.password = password;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "InvalidUsers [emailAddress=" + emailAddress + ", password=" + password + "]";
	}

}
