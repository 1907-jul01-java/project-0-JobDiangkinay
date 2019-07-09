package com.revature.controller;

import com.revature.models.Employee;
import com.revature.models.UserAccount;
import com.revature.views.MainPage;

public class LogInController {

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
	

}
