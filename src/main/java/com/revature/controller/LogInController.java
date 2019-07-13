package com.revature.controller;

import com.revature.entities.UserAccountsDao;
import com.revature.utilities.ConnectionUtil;
import com.revature.views.EmployeePage;
import com.revature.views.MainPage;
import com.revature.views.UserPage;

public class LogInController {
	ConnectionUtil connectionUtil = new ConnectionUtil();
	UserAccountsDao userDao = new UserAccountsDao(connectionUtil.getConnection());

	public void logInChecker(String username, String password) {
		if (userDao.isValidLogin(username, password)) {
			System.out.println("Log In Successfully!");
			runAccountPage(username);

		} else {
			System.out.println("Check Username/PassWord \n");
			MainPage main = new MainPage();
			connectionUtil.close();
			main.openingPage(0);
		}
	}
	
	public void runAccountPage(String userName) {
		String userType = userDao.getUserType(userName);
		if (userType.equals("USER")) {
			connectionUtil.close();
			UserPage userPage = new UserPage();
			userPage.runUserPage(userName);
		}else {
			EmployeePage empPage = new EmployeePage();
			empPage.runEmployeePage(userName);
		}
	}
}
