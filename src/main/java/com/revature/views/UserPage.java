package com.revature.views;

import java.util.Scanner;

import com.revature.entities.UserAccountsDao;
import com.revature.models.UserAccount;
import com.revature.utilities.ConnectionUtil;

public class UserPage {
	MainPage mainPage = new MainPage();

	/**
	 * Runs the user page
	 * @param userName - the username of the current user
	 */
	public void runUserPage(String userName) {
		UserAccount user = getUser(userName);
		int choice = 0;
		while (choice != 5) {
			System.out.println("\nWelcome " + user.getFirstName() + " !");
			System.out.println("1. Show User Info:\n2. Show Accounts\n3. All Transactions\n4. Apply New Bank Account\n5. LogOut");
			System.out.print("Choice: ");
			Scanner scan = new Scanner(System.in);
			try {
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					choice = 5;
					user.showUserInfo();
					break;
				case 2:
					choice = 5;
					user.showUserBankAccounts();
					break;
				case 3:
					choice = 5;
					user.showTransactions();
					break;
				case 4:
					choice = 5;
					user.createPendingAccount();
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

	/**
	 * Method to select what to do in the account.
	 * @param account - the username of the current user
	 */
	public void handleAccountView(UserAccount account) {
		UserAccount user = getUserAccount(account.getUserName(), account.getAccountNumber());
		System.out.println("\nAccount Number: " + user.getAccountNumber());
		System.out.println("Balance: "+ user.getBalance());
		int choice = 0;
		while (choice != 5) {
			System.out.println("1. Deposit\n2. Withdraw\n3. Transfer\n4. Apply for Joint Account\n5. Back to Main Menu");
			System.out.print("Choice: ");
			Scanner scan = new Scanner(System.in);
			try {
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					user.depositAmount();
					break;
				case 2:
					user.withdrawAmount();
					break;
				case 3:
					user.transferAmount();
					break;
				case 4:
					user.createJointAccount(user);
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
	
	public UserAccount getUserAccount(String Username, String AccountNumber) {
		ConnectionUtil connectionUtil = new ConnectionUtil();
		UserAccountsDao userDao = new UserAccountsDao(connectionUtil.getConnection());
		UserAccount curUser = userDao.getSpecificBankAccount(Username, AccountNumber);
		connectionUtil.close();
		return curUser;
	}
}
