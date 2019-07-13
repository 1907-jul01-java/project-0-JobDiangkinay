package com.revature.views;

import java.util.Scanner;

import com.revature.entities.EmployeeDao;
import com.revature.entities.UserAccountsDao;
import com.revature.models.Employee;
import com.revature.models.UserAccount;
import com.revature.utilities.ConnectionUtil;

public class EmployeePage {
	MainPage mainPage = new MainPage();

	public void runEmployeePage(String userName) {
		Employee emp = getUser(userName);
		System.out.println("\nWelcome " + emp.getFirstName() + " !");
		System.out.println("Authorization Level: "+ emp.getUserType());
		int choice = 0;
		while (choice != 4) {
			System.out.println("1. Employee Info:\n2. Show All Bank Accounts\n3. Manage Pending Accounts\n4. LogOut");
			System.out.print("Choice: ");
			Scanner scan = new Scanner(System.in);
			try {
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					choice = 4;
					emp.showUserInfo();
					break;
				case 2:
					choice = 4;
					emp.showAllBankAccounts();
					break;
				case 3:
					choice = 4;
					emp.showPendingAccounts();
					break;
				case 4:
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

	public Employee getUser(String Username) {
		ConnectionUtil connectionUtil = new ConnectionUtil();
		EmployeeDao empDao = new EmployeeDao(connectionUtil.getConnection());
		Employee curEmp = empDao.getEmployee(Username);
		connectionUtil.close();
		return curEmp;
	}
}
