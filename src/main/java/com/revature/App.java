package com.revature;

import com.revature.utilities.PersonDataUtil;
import com.revature.views.MainPage;

/**
 * Console Banking App - Diangkinay
 *
 */
public class App {
	public static void main(String[] args) {
		PersonDataUtil.initLoadAccounts();
		int choice = 0;
		MainPage main = new MainPage();
		main.openingPage(choice);
	}
}
