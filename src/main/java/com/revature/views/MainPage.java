package com.revature.views;

import java.util.Scanner;

public class MainPage {

	public void openingPage(int choice) {
		while (choice != 3) {
			System.out.println("\nBanking App Project 0");
			System.out.println("Menu");
			System.out.println("1. Log In \n2. Sign Up \n3. Exit");
			System.out.print("Choice:");
			Scanner scan = new Scanner(System.in);
			try {
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					choice = 3;
					LogInPage theLogin = new LogInPage();
					theLogin.runLogIn();
					
					break;
				case 2:
					choice = 3;
					SignUpPage theSignUp = new SignUpPage();
					theSignUp.handleSignUp();
					
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
}