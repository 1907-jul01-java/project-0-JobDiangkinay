package com.revature.views;

import java.util.Scanner;

import com.revature.controller.LogInController;

public class LogInPage {
	Scanner scan = new Scanner(System.in);
	LogInController logIn = new LogInController();

	public void runLogIn() {

		System.out.println("Log In");
		System.out.println("User Name:");
		String userName = scan.nextLine();
		System.out.println("Password:");
		String password = scan.nextLine();
		logIn.logInChecker(userName, password);
	}

}

