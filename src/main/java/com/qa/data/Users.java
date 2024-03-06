package com.qa.data;

public class Users {
	
	String Username;
	String Password;
	String APIKey;
	String Email;
	
	public Users() {
		
	}
	
	public Users(String Username,String Password,String APIKey,String Email)
	{
		this.Username = Username;
		this.Password = Password;
		this.APIKey = APIKey;
		this.Email = Email;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String Username) {
		this.Username = Username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}
	public String getAPIKey() {
		return APIKey;
	}

	public void setAPIKey(String APIKey) {
		this.APIKey = APIKey;
	}
	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}

	
	


}