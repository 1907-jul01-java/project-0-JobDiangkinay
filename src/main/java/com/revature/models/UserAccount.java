package com.revature.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.revature.entities.UserAccountsDao;
import com.revature.models.interfaces.IUserAccount;
import com.revature.utilities.ConnectionUtil;
import com.revature.views.UserPage;

public class UserAccount extends Person implements IUserAccount{
	private String userName;
	private String password;
	private String accountNumber;
	private double balance;

	// Constructors
	public UserAccount(String firstName, String lastName, String phoneNumber, String userName, String password,
			String accountNumber, double balance) {
		super(firstName, lastName, phoneNumber, "USER");
		this.userName = userName;
		this.password = password;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public UserAccount(String firstName, String lastName, String phoneNumber, String userName, String password) {
		super(firstName, lastName, phoneNumber, "USER");
		this.userName = userName;
		this.password = password;
	}

	public UserAccount(String userName, String accountNumber, double balance) {
		super();
		this.userName = userName;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	// Getters and Setters
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "|UserName: " + userName + "| Account Number: " + accountNumber + "| Balance: " + balance + "| Name: "
				+ super.getFirstName() + " " + super.getLastName();
	}

	// Methods

	public UserAccount get() {
		return this;
	}

	public void depositAmount() {
		UserAccount curAccount = get();
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

	public void withdrawAmount() {
		UserAccount curAccount = get();
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

	public void transferAmount() {
		UserAccount curAccount = get();
		ConnectionUtil connectionUtil = new ConnectionUtil();
		UserAccountsDao userDao = new UserAccountsDao(connectionUtil.getConnection());
		double curBalance = curAccount.getBalance();
		Scanner scan = new Scanner(System.in);

		try {
			System.out.println("Transfer to AccountNumber: ");
			String desAccountNum = scan.nextLine();
			UserAccount desAccount = userDao.getBankAccount(desAccountNum);
			double desAccountBalance = desAccount.getBalance();
			if (curAccount.getAccountNumber().equals(desAccount.getAccountNumber())) {
				System.out.println("Invalid Transaction! Same AccountNumber\n");
				pressContinue();
				connectionUtil.close();
				UserPage userMenu = new UserPage();
				userMenu.runUserPage(curAccount.getUserName());
			}
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

	public void showUserBankAccounts() {
		UserAccount user = get();
		ConnectionUtil connectionUtil = new ConnectionUtil();
		UserAccountsDao userDao = new UserAccountsDao(connectionUtil.getConnection());
		String curUserName = user.getUserName();
		ArrayList<UserAccount> bankAccounts = userDao.getUserBankAccounts(curUserName);
		System.out.println("\nYour Bank Accounts:");
		for (int i = 0; i < bankAccounts.size(); i++) {
			UserAccount b = bankAccounts.get(i);
			System.out.println(
					(i + 1) + ". " + "Account Number: " + b.getAccountNumber() + "\tBalance: " + b.getBalance());
		}
		try {
			if (bankAccounts.size() > 0) {
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
			} else {
				connectionUtil.close();
				System.out.println("No Account Available");
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

	public void showUserInfo() {
		UserAccount user = get();
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

	public void createPendingAccount() {
		UserAccount curAccount = get();
		ConnectionUtil connectionUtil = new ConnectionUtil();
		UserAccountsDao userDao = new UserAccountsDao(connectionUtil.getConnection());
		System.out.println("Create new account:");
		System.out.println("Enter amount($50 minimum deposit): ");
		try {
			Scanner scan = new Scanner(System.in);
			double initDeposit = scan.nextDouble();
			if (initDeposit >= 50) {
				String accountNumber = generateRandomChars();
				userDao.insertPendingAccount(curAccount, accountNumber, initDeposit);
				UserPage userMenu = new UserPage();
				userMenu.runUserPage(curAccount.getUserName());
			} else {
				System.out.println("Minimum Amount is $50!");
				UserPage userMenu = new UserPage();
				userMenu.runUserPage(curAccount.getUserName());
			}
		} catch (Exception ex) {
			System.out.println("Invalid Input!");
			UserPage userMenu = new UserPage();
			userMenu.runUserPage(curAccount.getUserName());
		}
	}

	public String generateRandomChars() {
		String numbers = "1234567890";
		int length = 7;
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(numbers.charAt(random.nextInt(numbers.length())));
		}

		return "19" + sb.toString();
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