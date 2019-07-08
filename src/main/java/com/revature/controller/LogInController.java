package com.revature.controller;

import java.util.Scanner;

import com.revature.models.Employee;
import com.revature.models.UserAccount;
import com.revature.views.MainPage;

public class LogInController {

	public void acceptLogIn(boolean checkUser, boolean isEmployee, UserAccount curUser, Employee curEmployee) {
		if (checkUser) {

			System.out.println("Log In Successfully!");
			System.out.println();
			MainPage main = new MainPage();
			if (isEmployee)
				main.runEmployeePage(curEmployee);
			if (!isEmployee)
				main.runUserPage(curUser);
		} else {
			System.out.println("Check Username/PassWord \n");
			MainPage main = new MainPage();
			main.openingPage(0);
		}

	}
	
	public void handleSignUp()
	{
		try (Scanner scan = new Scanner(System.in)){
		System.out.println("Open an account");
		System.out.println("User Name");
		String userName = scan.nextLine();
		System.out.println("Password");
		// put isUserNameValid method
		String password = scan.nextLine();
		System.out.println("First Name");
		String firstName = scan.nextLine();
		System.out.println("Last Name");
		String lastName = scan.nextLine();
		System.out.println("Initial Deposit");
		double deposit = scan.nextDouble();
		}
	}
}
