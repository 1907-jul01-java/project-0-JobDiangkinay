package com.revature.views;

import java.util.Scanner;

import com.revature.controller.SignUpController;
import com.revature.models.UserAccount;

public class SignUpPage {
	MainPage mainPage = new MainPage();
	SignUpController signCtrl = new SignUpController();

	public void handleSignUp() {
		boolean isValid = false;
		boolean blankAccount = false;
		while (!isValid) {
			try {
				Scanner scan = new Scanner(System.in);
				System.out.println("Open an account:");
				System.out.print("User Name:");
				String userName = scan.nextLine();
				System.out.print("Password:");
				isValid = !signCtrl.hasDuplicateUserName(userName);
				String password = scan.nextLine();
				System.out.print("First Name:");
				String firstName = scan.nextLine();
				System.out.print("Last Name:");
				String lastName = scan.nextLine();
				System.out.print("Phone Number:");
				String phoneNumber =  scan.nextLine();
				UserAccount signUpUser = new UserAccount(firstName, lastName, phoneNumber, userName, password);
				blankAccount = signCtrl.isFieldEmpty(signUpUser);
				if(blankAccount) {
					System.out.println("There is a blank field or Contains Spaces.\n");
					mainPage.openingPage(0);
				}
				if(!isValid) {
					System.out.println("UserName already inuse.\n");
					mainPage.openingPage(0);
				}
				signCtrl.registerUser(signUpUser);
				
				scan.close();
			}
			catch (Exception ex) {
				System.out.println("\nInvalid Input");
			}
		}
	}
}
