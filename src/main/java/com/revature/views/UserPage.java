package com.revature.views;

import java.util.Scanner;

import com.revature.entities.UserAccountsDao;
import com.revature.models.UserAccount;
import com.revature.utilities.ConnectionUtil;

public class UserPage {
	MainPage mainPage = new MainPage();

	public void runUserPage(String userName) {
		UserAccount user = getUser(userName);
		System.out.println("\nWelcome " + user.getFirstName() + " !");
		int choice = 0;
		while (choice != 4) {
			System.out.println("1. Show User Info:\n2. Show Accounts\n3. Apply New Bank Account\n4. LogOut");
			System.out.print("Choice: ");
			Scanner scan = new Scanner(System.in);
			try {
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					choice = 4;
					user.showUserInfo();
					break;
				case 2:
					choice = 4;
					user.showUserBankAccounts();
					break;
				case 3:
					choice = 4;
					user.createPendingAccount();
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

	public void handleAccountView(UserAccount account) {
		System.out.println("\nAccount Number: " + account.getAccountNumber());
		System.out.println("Balance: "+ account.getBalance());
		int choice = 0;
		while (choice != 5) {
			System.out.println("1. Deposit\n2. Withdraw\n3. Transfer\n4. Apply for Joint Account\n5. Back to Main Menu");
			System.out.print("Choice: ");
			Scanner scan = new Scanner(System.in);
			try {
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					account.depositAmount();
					break;
				case 2:
					account.withdrawAmount();
					break;
				case 3:
					account.transferAmount();
					break;
				case 4:
					account.createJointAccount(account);
					break;
				case 5:
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
	
	public UserAccount getUser(String Username) {
		ConnectionUtil connectionUtil = new ConnectionUtil();
		UserAccountsDao userDao = new UserAccountsDao(connectionUtil.getConnection());
		UserAccount curUser = userDao.getUser(Username);
		connectionUtil.close();
		return curUser;
	}
}
