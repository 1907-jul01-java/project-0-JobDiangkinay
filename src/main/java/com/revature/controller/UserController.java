package com.revature.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.entities.UserAccountsDao;
import com.revature.models.UserAccount;
import com.revature.utilities.ConnectionUtil;
import com.revature.views.UserPage;

public class UserController {

	public void showUserBankAccounts(UserAccount user) {
		ConnectionUtil connectionUtil = new ConnectionUtil();
		UserAccountsDao userDao = new UserAccountsDao(connectionUtil.getConnection());
		String curUserName = user.getUserName();
		ArrayList<UserAccount> bankAccounts = userDao.getUserBankAccounts(curUserName);
		System.out.println("\nYour Bank Accounts");
		for (int i = 0; i < bankAccounts.size(); i++) {
			UserAccount b = bankAccounts.get(i);
			System.out.println(
					(i + 1) + ". " + "Account Number: " + b.getAccountNumber() + "\tBalance: " + b.getBalance());
		}
		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("Choose Account to handle:");
			int i = scan.nextInt();
			UserAccount accountToHandle = bankAccounts.get(i - 1);
			if (accountToHandle != null) {
				connectionUtil.close();
				System.out.println("Account Found!");
				UserPage userMenu = new UserPage();
				userMenu.handleAccountView(accountToHandle);
			} else {
				connectionUtil.close();
				UserPage userMenu = new UserPage();
				userMenu.runUserPage(curUserName);
			}
		} catch (Exception ex) {
			System.out.println("Account Not Found!");
			connectionUtil.close();
			UserPage userMenu = new UserPage();
			userMenu.runUserPage(curUserName);
		}
		pressContinue();
		connectionUtil.close();
		UserPage userMenu = new UserPage();
		userMenu.runUserPage(curUserName);
	}

	public void depositAmount(UserAccount curAccount) {
		ConnectionUtil connectionUtil = new ConnectionUtil();
		UserAccountsDao userDao = new UserAccountsDao(connectionUtil.getConnection());
		double curBalance = curAccount.getBalance();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter amount:");
		try {
			double depAmount = scan.nextDouble();
			String trystring = String.format("%.2f", depAmount);
			double finaldoub = Double.parseDouble(trystring);
			double finBalance = curBalance + finaldoub;
			userDao.depositAmount(finBalance, curAccount.getAccountNumber());
			System.out.println("Success!");
			pressContinue();
			connectionUtil.close();
			UserPage userMenu = new UserPage();
			userMenu.runUserPage(curAccount.getUserName());
		} catch (Exception ex) {
			System.out.println("Invalid Input!");
			connectionUtil.close();
			UserPage userMenu = new UserPage();
			userMenu.handleAccountView(curAccount);
		}
	}

	public void withdrawAmount(UserAccount curAccount) {
		ConnectionUtil connectionUtil = new ConnectionUtil();
		UserAccountsDao userDao = new UserAccountsDao(connectionUtil.getConnection());
		double curBalance = curAccount.getBalance();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter amount:");
		try {
			double depAmount = scan.nextDouble();
			String trystring = String.format("%.2f", depAmount);
			double finaldoub = Double.parseDouble(trystring);
			if (curBalance > finaldoub) {
				double finBalance = curBalance - finaldoub;
				userDao.depositAmount(finBalance, curAccount.getAccountNumber());
				System.out.println("Success!");
				pressContinue();
				connectionUtil.close();
				UserPage userMenu = new UserPage();
				userMenu.runUserPage(curAccount.getUserName());
			} else {
				System.out.println("Invalid Amount!");
				connectionUtil.close();
				UserPage userMenu = new UserPage();
				userMenu.handleAccountView(curAccount);
			}
		} catch (Exception ex) {
			System.out.println("Invalid Input!");
			connectionUtil.close();
			UserPage userMenu = new UserPage();
			userMenu.handleAccountView(curAccount);
		}
	}

	public void transferAmount(UserAccount curAccount) {
		ConnectionUtil connectionUtil = new ConnectionUtil();
		UserAccountsDao userDao = new UserAccountsDao(connectionUtil.getConnection());
		double curBalance = curAccount.getBalance();
		Scanner scan = new Scanner(System.in);

		try {
			System.out.println("Transfer to AccountNumber: ");
			String desAccountNum = scan.nextLine();
			UserAccount desAccount = userDao.getBankAccount(desAccountNum);
			double desAccountBalance = desAccount.getBalance();
			System.out.println("Enter amount:");
			double depAmount = scan.nextDouble();
			String trystring = String.format("%.2f", depAmount);
			double finaldoub = Double.parseDouble(trystring);
			if (curBalance > finaldoub) {
				double finBalance = curBalance - finaldoub;
				userDao.depositAmount(finBalance, curAccount.getAccountNumber());
				double desFinBalance = desAccountBalance + finaldoub;
				userDao.depositAmount(desFinBalance, desAccount.getAccountNumber());
				System.out.println("Success!");
				pressContinue();
				connectionUtil.close();
				UserPage userMenu = new UserPage();
				userMenu.runUserPage(curAccount.getUserName());
			} else {
				System.out.println("Invalid Amount!");
				connectionUtil.close();
				UserPage userMenu = new UserPage();
				userMenu.handleAccountView(curAccount);
			}
		} catch (Exception ex) {
			System.out.println("Invalid Transaction!");
			connectionUtil.close();
			UserPage userMenu = new UserPage();
			userMenu.handleAccountView(curAccount);
		}

	}

	public void showUserInfo(UserAccount user) {
		ConnectionUtil connectionUtil = new ConnectionUtil();
		UserAccountsDao userDao = new UserAccountsDao(connectionUtil.getConnection());
		String curUserName = user.getUserName();
		UserAccount curUser = userDao.getUser(curUserName);
		System.out.println("User Info:");
		System.out.println("UserName: " + curUser.getUserName());
		System.out.println("Name: " + curUser.getFirstName() + " " + user.getLastName());
		System.out.println("Phone Number: " + curUser.getPhoneNumber());
		pressContinue();
		connectionUtil.close();
		UserPage userMenu = new UserPage();
		userMenu.runUserPage(curUserName);
	}

	public UserAccount getUser(String Username) {
		ConnectionUtil connectionUtil = new ConnectionUtil();
		UserAccountsDao userDao = new UserAccountsDao(connectionUtil.getConnection());
		UserAccount curUser = userDao.getUser(Username);
		connectionUtil.close();
		return curUser;
	}

	public void pressContinue() {
		try {
			System.out.println("Press Enter to continue...");
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
