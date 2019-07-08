package com.revature.models;

public class Employee extends Person {
	private String userName;
	private String password;

	public Employee(String firstName, String lastName, String phoneNumber, String userName,
			String password, String userType) {
		super(firstName, lastName, phoneNumber, userType );
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}