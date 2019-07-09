package com.revature.views;

import java.util.Scanner;

public class SignUpPage {

	public void handleSignUp() {
		boolean isValid = false;
		while (!isValid) {
			try {
				Scanner scan = new Scanner(System.in);
				System.out.println("Open an account");
				System.out.print("User Name");
				String userName = scan.nextLine();
				System.out.print("Password");
				// put isUserNameValid method
				String password = scan.nextLine();
				System.out.print("First Name");
				String firstName = scan.nextLine();
				System.out.print("Last Name");
				String lastName = scan.nextLine();
				System.out.print("Initial Deposit");
				double deposit = scan.nextDouble();
				isValid = true;
				scan.close();
			}
			catch (Exception ex) {
				System.out.println("\nInvalid Input");
			}
		}
	}
}
