package com.revature.controller;

import java.io.IOException;
import java.util.regex.Pattern;

import com.revature.entities.UserAccountsDao;
import com.revature.models.UserAccount;
import com.revature.utilities.ConnectionUtil;
import com.revature.views.MainPage;

public class SignUpController {

	public boolean hasDuplicateUserName(String curUserName) {
		ConnectionUtil connectionUtil = new ConnectionUtil();
		UserAccountsDao userDao = new UserAccountsDao(connectionUtil.getConnection());
		boolean hasDuplicate = userDao.checkUserNameIsUsed(curUserName);
		connectionUtil.close();
		return hasDuplicate;
	}

	public void registerUser(UserAccount curUser) {
		ConnectionUtil connectionUtil = new ConnectionUtil();
		UserAccountsDao userDao = new UserAccountsDao(connectionUtil.getConnection());
		userDao.insertPerson(curUser);
		System.out.println("Success");
		pressContinue();
		MainPage main = new MainPage();
		main.openingPage(0);
	}

	public boolean isFieldEmpty(UserAccount curUser) {
		if (curUser.getUserName().isEmpty() || curUser.getPassword().isEmpty() || curUser.getFirstName().isEmpty()
				|| curUser.getLastName().isEmpty() || curUser.getPhoneNumber().isEmpty()) {
			return true;
		}else if (curUser.getUserName().indexOf(' ') >= 0 || curUser.getPassword().indexOf(' ') >= 0 || curUser.getFirstName().indexOf(' ') >= 0
				|| curUser.getLastName().indexOf(' ') >= 0 || curUser.getPhoneNumber().indexOf(' ') >= 0 || Pattern.matches("[a-zA-Z]+", curUser.getPhoneNumber())) {
			return true;
		}

		return false;
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
