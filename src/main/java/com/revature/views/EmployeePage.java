package com.revature.views;

import java.util.Scanner;

import com.revature.entities.EmployeeDao;
import com.revature.models.Employee;
import com.revature.models.UserAccount;
import com.revature.utilities.ConnectionUtil;

public class EmployeePage {
	MainPage mainPage = new MainPage();

	public void runEmployeePage(String userName) {
		Employee emp = getUser(userName);
		if (emp.getUserType().equals("ADMIN")) {
			runAdminPage(userName);
		} else {
			runEmpPage(userName);
		}
	}

	public void runEmpPage(String userName) {
		Employee emp = getUser(userName);
		System.out.println("\nWelcome " + emp.getFirstName() + " !");
		System.out.println("Authorization Level: " + emp.getUserType());
		int choice = 0;
		while (choice != 4) {
			System.out.println("1. Employee Info:\n2. Show All Bank Accounts\n3. Manage Pending Accounts\n4. Manage Pending Joint Accounts\n5. LogOut");
			System.out.print("Choice: ");
			Scanner scan = new Scanner(System.in);
			try {
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					choice = 5;
					emp.showUserInfo();
					break;
				case 2:
					choice = 5;
					emp.showAllBankAccounts(emp.getUserType());
					break;
				case 3:
					choice = 5;
					emp.showPendingAccounts();
					break;
				case 4:
					choice = 5;
					emp.showPendingJointAccounts();
					break;
				case 5:
					System.out.println("Successfully Logged Out");
					mainPage.openingPage(0);
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

	public void runAdminPage(String userName) {
		Employee emp = getUser(userName);
		System.out.println("\nWelcome " + emp.getFirstName() + " !");
		System.out.println("Authorization Level: " + emp.getUserType());
		int choice = 0;
		while (choice != 6) {
			System.out.println("1. Show Admin Info\n2. Show All Bank Accounts\n3. Show Transactions"
					+ "\n4. Manage Pending Accounts\n5. Manage Pending Joint Accounts\n6. Cancel Accounts\n7. Log Out");
			System.out.print("Choice: ");
			Scanner scan = new Scanner(System.in);
			try {
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					emp.showUserInfo();
					break;
				case 2:
					emp.showAllBankAccounts(emp.getUserType());
					break;
				case 3:
					emp.showAllTransaction();
					break;
				case 4:
					emp.showPendingAccounts();
					break;
				case 5:
					choice = 5;
					emp.showPendingJointAccounts();
					break;
				case 6:
					emp.cancelAccount();
					break;
				case 7:
					System.out.println("Successfully Logged Out");
					mainPage.openingPage(0);
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

	public void handleAdminAccountView(UserAccount account, Employee curUser) {
		System.out.println("\nAccount Number: " + account.getAccountNumber());
		int choice = 0;
		while (choice != 4) {
			System.out.println("1. Deposit\n2. Withdraw\n3. Transfer\n4. Back to Main Menu");
			System.out.print("Choice: ");
			Scanner scan = new Scanner(System.in);
			try {
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					curUser.depositAmount(account);
					break;
				case 2:
					curUser.withdrawAmount(account);
					break;
				case 3:
					curUser.transferAmount(account);
					break;
				case 4:
					runEmployeePage(curUser.getUserName());
					break;
				default:
					System.out.println("Invalid Input!");
					runEmployeePage(curUser.getUserName());
				}
			} catch (Exception ex) {
				System.out.println("Invalid Input!");
				runEmployeePage(curUser.getUserName());
			}
		}
	}

	public Employee getUser(String Username) {
		ConnectionUtil connectionUtil = new ConnectionUtil();
		EmployeeDao empDao = new EmployeeDao(connectionUtil.getConnection());
		Employee curEmp = empDao.getEmployee(Username);
		connectionUtil.close();
		return curEmp;
	}
}
