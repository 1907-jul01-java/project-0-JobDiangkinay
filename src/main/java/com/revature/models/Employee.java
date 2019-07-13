package com.revature.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.entities.EmployeeDao;
import com.revature.utilities.ConnectionUtil;
import com.revature.views.EmployeePage;

public class Employee extends Person {
	private String userName;
	private String password;

	public Employee(String firstName, String lastName, String phoneNumber, String userName, String password,
			String userType) {
		super(firstName, lastName, phoneNumber, userType);
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	@Override
	public String toString() {
		return "Employee [userName=" + userName + ", password=" + password + ", UserType=" + getUserType()
				+ ", FirstName=" + getFirstName() + ", LastName=" + getLastName() + ", PhoneNumber=" + getPhoneNumber()
				+ "]";
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

	// Methods
	public Employee get() {
		return this;
	}

	public void showUserInfo() {
		Employee user = get();
		ConnectionUtil connectionUtil = new ConnectionUtil();
		EmployeeDao empDao = new EmployeeDao(connectionUtil.getConnection());
		String curUserName = user.getUserName();
		Employee curUser = empDao.getEmployee(curUserName);
		System.out.println("User Info:");
		System.out.println("UserName: " + curUser.getUserName());
		System.out.println("Name: " + curUser.getFirstName() + " " + user.getLastName());
		System.out.println("Phone Number: " + curUser.getPhoneNumber());
		pressContinue();
		connectionUtil.close();
		EmployeePage userMenu = new EmployeePage();
		userMenu.runEmployeePage(curUserName);
	}

	public void showAllBankAccounts() {
		Employee user = get();
		ConnectionUtil connectionUtil = new ConnectionUtil();
		EmployeeDao empDao = new EmployeeDao(connectionUtil.getConnection());
		ArrayList<UserAccount> userAccounts = empDao.getAllBankAccounts();
		if (userAccounts != null) {
			System.out.println("Bank Accounts:");
			for (UserAccount bankAcc : userAccounts) {
				System.out.println("Account Number: " + bankAcc.getAccountNumber() + "\tBalance: "
						+ bankAcc.getBalance() + "\t\tOwner: " + bankAcc.getFirstName() + " " + bankAcc.getLastName()
						+ "\tUserName: " + bankAcc.getUserName() + "\tPhone#: " + bankAcc.getPhoneNumber());
			}
			pressContinue();
			EmployeePage userMenu = new EmployeePage();
			userMenu.runEmployeePage(user.getUserName());

		} else {
			System.out.println("No Bank Account Available!");
			pressContinue();
			EmployeePage userMenu = new EmployeePage();
			userMenu.runEmployeePage(user.getUserName());
		}
	}

	public void showPendingAccounts() {
		Employee user = get();
		ConnectionUtil connectionUtil = new ConnectionUtil();
		EmployeeDao empDao = new EmployeeDao(connectionUtil.getConnection());
		ArrayList<UserAccount> pendingAccounts = empDao.getAllPendingAccounts();
		if (pendingAccounts != null) {
			System.out.println("\nPending Bank Accounts:");
			for (UserAccount penBank : pendingAccounts) {
				System.out.println("Account Number: " + penBank.getAccountNumber() + "\tBalance: "
						+ penBank.getBalance() + "\tUser: " + penBank.getUserName());
			}
			handlePendingAccounts();
		} else {
			System.out.println("No Bank Account Available!");
			pressContinue();
			EmployeePage userMenu = new EmployeePage();
			userMenu.runEmployeePage(user.getUserName());
		}
	}

	public void handlePendingAccounts() {
		EmployeePage empPage = new EmployeePage();
		Employee user = get();
		ConnectionUtil connectionUtil = new ConnectionUtil();
		EmployeeDao empDao = new EmployeeDao(connectionUtil.getConnection());
		System.out.println("Enter the Account you want to handle:");
		try {
			Scanner scan = new Scanner(System.in);
			String accountNumber = scan.nextLine();
			boolean isValid = empDao.checkPendingAccountNumber(accountNumber);
			if (isValid) {
				try {
					UserAccount penAccount = empDao.getSpecificPendingAccount(accountNumber);
					System.out.println("Accept/Deny Account: " + penAccount.getAccountNumber() + " ?(Y/N)");
					String choice = scan.nextLine();
					switch (choice.toUpperCase()) {
					case "Y":
						empDao.acceptPendingAccount(penAccount);
						System.out.println("Account Accepted!");
						pressContinue();
						empPage.runEmployeePage(user.getUserName());
						break;

					case "N":
						empDao.deletePendingAccount(penAccount.getAccountNumber());
						System.out.println("Account Denied!");
						empPage.runEmployeePage(user.getUserName());
						break;
					default:
						System.out.println("Invalid Choice!");
						empPage.runEmployeePage(user.getUserName());
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				System.out.println("Account Number can't be found!");
				empPage.runEmployeePage(user.getUserName());
			}
		} catch (Exception ex) {
			System.out.println("Invalid Transaction!");
			empPage.runEmployeePage(user.getUserName());
		}
	}

	public void pressContinue() {
		try {
			System.out.println("Press Enter to continue...");
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}