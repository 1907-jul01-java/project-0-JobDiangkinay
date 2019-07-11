package com.revature.controller;

import com.revature.entities.UserAccountsDao;
import com.revature.models.Employee;
import com.revature.models.UserAccount;
import com.revature.utilities.ConnectionUtil;
import com.revature.views.MainPage;

public class LogInController {
	ConnectionUtil connectionUtil = new ConnectionUtil();
	UserAccountsDao userDao = new UserAccountsDao(connectionUtil.getConnection());

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

	public void logInChecker(String username, String password) {
		if (userDao.isValidLogin(username, password)) {
			System.out.println("Log In Successfully!");
			connectionUtil.close();
		} else {
			System.out.println("Check Username/PassWord \n");
			MainPage main = new MainPage();
			connectionUtil.close();
			main.openingPage(0);
		}
	}

}
