package com.revature.views;


import java.util.ArrayList;
import java.util.Scanner;

import com.revature.controller.LogInController;
import com.revature.controller.MainController;
import com.revature.models.Employee;
import com.revature.models.UserAccount;
import com.revature.utilities.PersonDataUtil;

public class MainPage {
	ArrayList<Employee> employees = PersonDataUtil.loadEmployees();
	ArrayList<UserAccount> users = PersonDataUtil.loadAccounts();
	MainController mainCtrl = new MainController();

	public void openingPage(int choice) {
		while (choice != 3) {
			System.out.println("Banking App Project 0");
			System.out.println("Menu");
			System.out.println("1. Log In \n2. Sign Up \n3. Exit");
			System.out.print("Choice:");
			Scanner scan = new Scanner(System.in);
			try {
				choice = scan.nextInt();
				switch (choice) {
				case 1:

					LogInPage theLogin = new LogInPage();
					theLogin.runLogIn();
					choice = 3;
					break;
				case 2:

					System.out.println("Sign Up");
					choice = 3;
					break;
				case 3:
					scan.close();
					System.exit(0);
					break;
				default:
					System.out.println("Invalid Input. Please Try Again. \n");
				}
			} catch (Exception ex) {
				System.out.println("Invalid Input. Please Try Again. \n");
			}
		}
	}

	public void runEmployeePage(Employee user) {
		System.out.println("Welcome " + user.getFirstName() + " !");
		if (user.getUserType().equals("Admin")) {
			adminMain(user);
		} else if (user.getUserType().equals("Employee")) {
			employeeMain(user);
		}
	}

	public void runUserPage(UserAccount user) {
		System.out.println("Welcome " + user.getFirstName() + " !");
		System.out.println("Account Number: " + user.getAccountNumber());
		int choice = 0;
		while (choice != 5) {
			System.out.println("1. Show User Info:\n2. Deposit\n3. Withdraw\n4. Transfer\n5. LogOut");
			System.out.print("Choice: ");
			Scanner scan = new Scanner(System.in);
			try {
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					choice = 3;
					mainCtrl.showUserInfo(user);
					break;
				case 2:
					// Deposit function
					choice = 3;
					break;
				case 3:
					// Withdraw function
					break;
				case 4:
					// Transfer function
					break;
				case 5:
					System.out.println("Successfully Logged Out");
					openingPage(0);
					scan.close();
					break;
				default:
					System.out.println("Invalid Input \n\n");
				}
			} catch (Exception ex) {
				System.out.println("Invalid Input \n\n");
			}
		}
	}

	public void adminMain(Employee user) {
		int choice = 0;
		while (choice != 4) {
			System.out.println("1. Show Account List\n2. Manage Pending Accounts\n3. Cancel Accounts\n4. Log Out");
			System.out.print("Choice: ");
			Scanner scan = new Scanner(System.in);
			try {
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					// Add withdraw/deposit/transfer function between accounts
					choice = 3;
					System.out.println("Show Account List:");
					mainCtrl.showUserList(user);
					break;
				case 2:
					choice = 3;
					System.out.println("Accept/Deny Pending Accounts");
					mainCtrl.managePendingAccounts(user);
					break;
				case 3:
					// Cancel/Delete accounts
					mainCtrl.cancelUserAccount(user);
					break;
				case 4:
					System.out.println("Successfully Logged Out");
					openingPage(0);
					scan.close();
					break;
				default:
					System.out.println("Invalid Input \n\n");
				}
			} catch (Exception ex) {
				System.out.println("Invalid Input \n\n");
			}
		}
	}

	public void employeeMain(Employee user) {
		int choice = 0;
		while (choice != 3) {
			System.out.println("1. Show User List:\n2. Pending Account Management\n3.Log Out");
			System.out.print("Choice: ");
			Scanner scan = new Scanner(System.in);
			if (scan.hasNextInt()) {
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					choice = 3;
					System.out.println("Show User List");
					mainCtrl.showUserList(user);
					break;
				case 2:
					choice = 3;
					System.out.println("Accept/Deny Pending Accounts");
					mainCtrl.managePendingAccounts(user);
					break;
				case 3:
					System.out.println("Successfully Logged Out");
					openingPage(0);
					scan.close();
					break;
				default:
					System.out.println("Invalid Input \n\n");
				}
			} else {
				System.out.println("Invalid Input \n\n");
			}
		}
	}

}