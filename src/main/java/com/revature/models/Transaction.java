package com.revature.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	private String userAccount;
	private String sourceAccount;
	private String destinationAccount;
	private double amount;
	private String transactionType;
	private Date transDate;

	public Transaction(String userAccount,String sourceAccount, String destinationAccount, double amount, String transactionType,
			Date transDate) {
		this.userAccount = userAccount;
		this.sourceAccount = sourceAccount;
		this.destinationAccount = destinationAccount;
		this.amount = amount;
		this.transactionType = transactionType;
		this.transDate = transDate;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(String sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public String getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(String destinationAccount) {
		this.destinationAccount = destinationAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	
	public String getStringDate() {
		String sDate = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format( this.getTransDate() );
		return sDate;
	}

	@Override
	public String toString() {
		if(destinationAccount != null) {
		return "User: "+userAccount + "\tSourceAccount: " + sourceAccount + "\tDestinationAccount: "
				+ destinationAccount + "\tAmount: " + amount + "\tTransactionType: " + transactionType + "\tDate: "
				+ getStringDate();
		}else {
			return "User: "+userAccount + "\tSourceAccount: " + sourceAccount + "\tDestinationAccount: "
					+ "N/A\t" + "\tAmount: " + amount + "\tTransactionType: " + transactionType + "\tDate: "
					+ getStringDate();
		}
	}

}
