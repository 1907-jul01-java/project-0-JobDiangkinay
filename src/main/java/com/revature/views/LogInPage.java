package com.revature.views;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.controller.LogInController;
import com.revature.models.Employee;
import com.revature.models.UserAccount;
import com.revature.utilities.PersonDataUtil;

public class LogInPage {
	ArrayList<Employee> employees = PersonDataUtil.loadEmployees();
	ArrayList<UserAccount> users = PersonDataUtil.loadAccounts();
	Scanner scan = new Scanner(System.in);
	LogInController logIn = new LogInController();

	public void runLogIn() {
		boolean checkUser = false;
		boolean isEmployee = false;
		Employee curEmployee = null;
		UserAccount curUser = null;

		System.out.println("Log In");
		System.out.println("User Name:");
		String userName = scan.nextLine();
		System.out.println("Password:");
		String lastName = scan.nextLine();

		for (Employee p : employees) {
			if (userName.equals(p.getUserName()) && lastName.equals(p.getPassword())) {
				checkUser = true;
				isEmployee = true;
				curEmployee = p;
			}
		}
		if (!isEmployee) {
			for (UserAccount p : users) {
				if (userName.equals(p.getUserName()) && lastName.equals(p.getPassword())) {
					checkUser = true;
					curUser = p;
				}
			}
		}
		logIn.acceptLogIn(checkUser, isEmployee, curUser, curEmployee);
	}

}

