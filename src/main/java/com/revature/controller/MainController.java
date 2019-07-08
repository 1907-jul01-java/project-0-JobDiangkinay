package com.revature.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.models.Employee;
import com.revature.models.UserAccount;
import com.revature.utilities.PersonDataUtil;
import com.revature.views.MainPage;

public class MainController {
	ArrayList<UserAccount> persons = PersonDataUtil.loadAccounts();
	ArrayList<UserAccount> pendingAccounts = PersonDataUtil.loadPendingAccounts();

	public void showUserInfo(UserAccount user) {
		MainPage userMenu = new MainPage();
		System.out.println("User Info:");
		System.out.println("Account Number: " + user.getAccountNumber());
		System.out.println("Name: " + user.getFirstName() + " " + user.getLastName());
		System.out.println("Available Funds: " + user.getBalance());
		pressContinue();
		userMenu.runUserPage(user);
	}

	public void showUserList(Employee curUser) {
		MainPage userMenu = new MainPage();
		System.out.println("User List:");
		for (UserAccount p : persons) {
			System.out.println(p.toString());
		}
		pressContinue();
		userMenu.runEmployeePage(curUser);
	}

	public void showPendingAccounts(Employee curUser) {
		MainPage userMenu = new MainPage();
		System.out.println("Pending Account List:");
		if (pendingAccounts != null) {
			for (UserAccount p : pendingAccounts) {
				System.out.println(p.toString());
			}
		} else {
			System.out.println("No Pending Accounts");
			userMenu.runEmployeePage(curUser);
		}
	}

	public void addAccount(UserAccount account) {
		boolean isUsernameValid = false;

		for (UserAccount p : PersonDataUtil.loadAccounts()) {
			if (!account.getUserName().equals(p.getUserName())) {
				isUsernameValid = true;
			}
		}

		if (isUsernameValid) {
			PersonDataUtil.addPendingAccount(account);
			System.out.println("Added Successfully \nYour account will be submitted for Confirmation");
			MainPage openPage = new MainPage();
			openPage.openingPage(0);
		}
	}

	public void managePendingAccounts(Employee curEmp) {
		MainPage empMenu = new MainPage();
		showPendingAccounts(curEmp);
		System.out.println("Enter the username of the account to manage:");
		Scanner scan = new Scanner(System.in);
		String user = scan.nextLine();
		UserAccount account = null;
		for (UserAccount p : PersonDataUtil.loadPendingAccounts()) {
			if (p.getUserName().equals(user)) {
				account = p;
			}
		}
		if (account != null) {
			try (Scanner scan2 = new Scanner(System.in)) {
				System.out.println("Accept? (Y,N): ");
				String choice = scan2.nextLine();
				switch (choice) {
				case "y":
					System.out.println("User Accepted!");
					PersonDataUtil.addAccount(account);
					PersonDataUtil.removePendingAccount(account);
					empMenu.runEmployeePage(curEmp);
					break;
				case "x":
					System.out.println("User Denied!");
					PersonDataUtil.removePendingAccount(account);
					empMenu.runEmployeePage(curEmp);
					break;
				default:
					System.out.println("Invalid Input");
					empMenu.runEmployeePage(curEmp);
				}
			}
		} else {
			System.out.println("Account Not Found");
			empMenu.runEmployeePage(curEmp);
		}
		scan.close();
	}

	public void cancelUserAccount(Employee curEmp) {
		MainPage empMenu = new MainPage();
		System.out.println("Cancel User Account");
		System.out.println("Enter the Account Number:");
		Scanner scan = new Scanner(System.in);
		String account = scan.nextLine();
		UserAccount user = null;
		for (UserAccount p : PersonDataUtil.loadAccounts()) {
			if (p.getUserName().equals(account)) {
				user = p;
			}
		}
		if (user != null) {
			try (Scanner scan2 = new Scanner(System.in)){
				System.out.println("Cancel Account? (Y,N): ");
			}
		}
		else {
			System.out.println("Account Not Found");
			empMenu.runEmployeePage(curEmp);
		}
		scan.close();
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
