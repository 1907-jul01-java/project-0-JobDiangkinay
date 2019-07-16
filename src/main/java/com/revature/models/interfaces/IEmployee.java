package com.revature.models.interfaces;

import com.revature.models.UserAccount;

public interface IEmployee {

	public void showAllBankAccounts(String e);
	
	public void handleAccounts();

	public void cancelAccount(UserAccount e);

	public void handlePendingAccounts();

	public void showPendingAccounts();
}
