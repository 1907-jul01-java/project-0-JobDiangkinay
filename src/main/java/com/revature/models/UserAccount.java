package com.revature.models;

public class UserAccount extends Person {
	private String userName;
	private String password;
	private String accountNumber;
	private double balance;

	public UserAccount(String firstName, String lastName, String phoneNumber, String userName, String password,
			String accountNumber, double balance) {
		super(firstName, lastName, phoneNumber, "USER");
		this.userName = userName;
		this.password = password;
		this.accountNumber = accountNumber;
		this.balance = balance;
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

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void depositAmount(double depAmount) {
		double newAmount = this.balance + depAmount;
		this.balance = newAmount;
	}

	@Override
	public String toString() {
		return "|UserName: " + userName + "| Account Number: " + accountNumber + "| Balance: " + balance + "| Name: "
				+ super.getFirstName() + " " + super.getLastName();
	}

}