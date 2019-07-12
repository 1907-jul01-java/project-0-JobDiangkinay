package com.revature.views;

import java.util.Scanner;

import com.revature.controller.UserController;
import com.revature.models.UserAccount;

public class UserPage {
	MainPage mainPage = new MainPage();
	UserController userCtrl = new UserController();

	public void runUserPage(String userName) {
		UserAccount user = userCtrl.getUser(userName);
		System.out.println("\nWelcome " + user.getFirstName() + " !");
		int choice = 0;
		while (choice != 3) {
			System.out.println("1. Show User Info:\n2. Show Accounts\n3. LogOut");
			System.out.print("Choice: ");
			Scanner scan = new Scanner(System.in);
			try {
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					choice = 3;
					userCtrl.showUserInfo(user);
					break;
				case 2:
					choice = 3;
					userCtrl.showUserBankAccounts(user);
					break;
				case 3:
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

	public void handleAccountView(UserAccount account) {
		System.out.println("\nAccount Number: " + account.getAccountNumber());
		System.out.println("Balance: "+ account.getBalance());
		int choice = 0;
		while (choice != 4) {
			System.out.println("1. Deposit\n2. Withdraw\n3. Transfer\n4. Back to Main Menu");
			System.out.print("Choice: ");
			Scanner scan = new Scanner(System.in);
			try {
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					userCtrl.depositAmount(account);
					break;
				case 2:
					userCtrl.withdrawAmount(account);
					break;
				case 3:
					userCtrl.transferAmount(account);
					break;
				case 4:
					runUserPage(account.getUserName());
					break;
				default:
					System.out.println("Invalid Input!");
					runUserPage(account.getUserName());
				}
			} catch (Exception ex) {
				System.out.println("Invalid Input!");
				runUserPage(account.getUserName());
			}
		}
	}
}
