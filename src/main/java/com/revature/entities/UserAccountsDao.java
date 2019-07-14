package com.revature.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revature.models.Transaction;
import com.revature.models.UserAccount;

public class UserAccountsDao implements Dao<UserAccount> {
	Connection connection;

	@Override
	public void insert(UserAccount e) {
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"insert into persons(firstname, lastname, phonenumber, username) values(?, ?, ?, ?)");
			pStatement.setString(1, e.getFirstName());
			pStatement.setString(2, e.getLastName());
			pStatement.setString(3, e.getPhoneNumber());
			pStatement.setString(4, e.getUserName());
			pStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		// for accountcredentialstable
		try {
			PreparedStatement pStatement = connection
					.prepareStatement("insert into accountcredentials(username, password, usertype) values(?, ?, ?)");
			pStatement.setString(1, e.getUserName());
			pStatement.setString(2, e.getPassword());
			pStatement.setString(3, e.getUserType());
			pStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		System.out.println("Success");
		try {
			PreparedStatement pStatement = connection
					.prepareStatement("insert into bankaccounts(username, balance, accountnumber) values(?, ?, ?)");
			pStatement.setString(1, e.getUserName());
			pStatement.setDouble(2, e.getBalance());
			pStatement.setString(3, e.getAccountNumber());
			pStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		System.out.println("Success");
	}

	public void insertPerson(UserAccount e) {
		// for person table
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"insert into persons(firstname, lastname, phonenumber, username) values(?, ?, ?, ?)");
			pStatement.setString(1, e.getFirstName());
			pStatement.setString(2, e.getLastName());
			pStatement.setString(3, e.getPhoneNumber());
			pStatement.setString(4, e.getUserName());
			pStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		// for accountcredentialstable
		try {
			PreparedStatement pStatement = connection
					.prepareStatement("insert into accountcredentials(username, password, usertype) values(?, ?, ?)");
			pStatement.setString(1, e.getUserName());
			pStatement.setString(2, e.getPassword());
			pStatement.setString(3, e.getUserType());
			pStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertTransaction(Transaction trans) {
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"insert into transactions(sourceuser, sourceaccount, amount, transactiontype, destinationaccount, transactiondate) values(?, ?, ?, ?, ?, ?)");
			pStatement.setString(1, trans.getUserAccount());
			pStatement.setString(2, trans.getSourceAccount());
			pStatement.setDouble(3, trans.getAmount());
			pStatement.setString(4, trans.getTransactionType());
			pStatement.setString(5, trans.getDestinationAccount());
			pStatement.setString(6, trans.getStringDate());
			pStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertPendingAccount(UserAccount e, String randomAccountNumber, double initDeposit) {
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"insert into pendingbankaccounts(accountnumber, balance, username) values(?, ?, ?)");
			pStatement.setString(1, randomAccountNumber);
			pStatement.setDouble(2, initDeposit);
			pStatement.setString(3, e.getUserName());
			pStatement.executeUpdate();
			System.out.println("Success!");
			System.out.println("Please wait for the account to be accepted.");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertPendingJointAccount(UserAccount e, double balance, String accountNumber) {
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"insert into pendingjointaccounts(accountnumber, balance, username) values(?, ?, ?)");
			pStatement.setString(1, accountNumber);
			pStatement.setDouble(2, balance);
			pStatement.setString(3, e.getUserName());
			pStatement.executeUpdate();
			System.out.println("Success!");
			System.out.println("Please wait for the transaction to be accepted.");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public boolean isValidLogin(String username, String password) {
		int i = 0;
		try {
			PreparedStatement pStatement = connection
					.prepareStatement("select * from accountcredentials where username = ? and password = ?");
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (i == 1) {
			return true;
		}
		return false;
	}

	public String getUserType(String username) {
		String result = null;
		int i = 0;
		try {
			PreparedStatement pStatement = connection
					.prepareStatement("select * from accountcredentials where username = ?");
			pStatement.setString(1, username);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getString("usertype");
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (i == 1) {
			return result;
		}
		return null;
	}

	@Override
	public List<UserAccount> getAll() {
		UserAccount user;
		List<UserAccount> users = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"select * from persons join accountcredentials on persons.username = accountcredentials.username join bankaccounts on persons.username = bankaccounts.username where usertype = 'USER'");
			while (resultSet.next()) {
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				String phoneNumber = resultSet.getString("phonenumber");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				double balance = resultSet.getDouble("balance");
				String accountNumber = resultSet.getString("accountnumber");

				user = new UserAccount(firstName, lastName, phoneNumber, username, password, accountNumber, balance);
				users.add(user);
			}
		} catch (SQLException e) {

		}
		return users;
	}
	
	public ArrayList<Transaction> getAllTransactions(String userName) {
		Transaction transact;
		ArrayList<Transaction> allTrans = new ArrayList<>();
		try {
			PreparedStatement pStatement = connection.prepareStatement("select * from transactions where sourceuser = ?");
			pStatement.setString(1, userName);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				String sourceUser = resultSet.getString("sourceuser");
				String sourceAccount = resultSet.getString("sourceaccount");
				double amount = resultSet.getDouble("amount");
				String transType = resultSet.getString("transactiontype");
				String destAccount = resultSet.getString("destinationAccount");
				String transactiondate = resultSet.getString("transactiondate");
				Date date1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").parse(transactiondate);
				transact = new Transaction(sourceUser, sourceAccount, destAccount, amount, transType, date1);
				allTrans.add(transact);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allTrans;
	}

	public UserAccount getUser(String curUserName) {
		UserAccount curUser = null;
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"select * from persons join accountcredentials on persons.username = accountcredentials.username where accountcredentials.username = ?");
			pStatement.setString(1, curUserName);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				String phoneNumber = resultSet.getString("phonenumber");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");

				curUser = new UserAccount(firstName, lastName, phoneNumber, username, password);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		if (curUser != null) {
			return curUser;
		}
		return null;
	}

	public ArrayList<UserAccount> getUserBankAccounts(String curUserName) {
		ArrayList<UserAccount> bankAccounts = new ArrayList<>();
		try {
			PreparedStatement pStatement = connection.prepareStatement("select * from bankaccounts where username = ?");
			pStatement.setString(1, curUserName);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				String username = resultSet.getString("username");
				double balance = resultSet.getDouble("balance");
				String accountNumber = resultSet.getString("accountnumber");

				bankAccounts.add(new UserAccount(username, accountNumber, balance));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return bankAccounts;
	}

	public boolean checkUserNameIsUsed(String curUserName) {
		boolean hasDuplicate = false;
		try {
			PreparedStatement pStatement = connection.prepareStatement("select * from bankaccounts where username = ?");
			pStatement.setString(1, curUserName);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				hasDuplicate = true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return hasDuplicate;
	}

	public UserAccount getSpecificBankAccount(String curUserName, String curAccountNumber) {
		UserAccount curUser = null;
		try {
			PreparedStatement pStatement = connection
					.prepareStatement("select * from bankaccounts where username = ? and accountnumber = ?");
			pStatement.setString(1, curUserName);
			pStatement.setString(2, curAccountNumber);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				String username = resultSet.getString("username");
				double balance = resultSet.getDouble("balance");
				String accountNumber = resultSet.getString("accountnumber");

				curUser = new UserAccount(username, accountNumber, balance);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return curUser;
	}

	public UserAccount getBankAccount(String curAccountNumber) {
		UserAccount curUser = null;
		try {
			PreparedStatement pStatement = connection
					.prepareStatement("select * from bankaccounts where accountnumber = ?");
			pStatement.setString(1, curAccountNumber);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				String username = resultSet.getString("username");
				double balance = resultSet.getDouble("balance");
				String accountNumber = resultSet.getString("accountnumber");

				curUser = new UserAccount(username, accountNumber, balance);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return curUser;
	}

	@Override
	public void delete(String e) {
		try {
			PreparedStatement pStatement = connection
					.prepareStatement("delete from accountcredentials where username = ?");
			pStatement.setString(1, e);
			pStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		try {
			PreparedStatement pStatement = connection.prepareStatement("delete from persons where username = ?");
			pStatement.setString(1, e);
			pStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		try {
			PreparedStatement pStatement = connection.prepareStatement("delete from bankaccounts where username = ?");
			pStatement.setString(1, e);
			pStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public void depositAmount(double depAmount, String accountNumber) {
		try {
			PreparedStatement pStatement = connection
					.prepareStatement("update bankaccounts set balance = ? where accountnumber = ? ");
			pStatement.setDouble(1, depAmount);
			pStatement.setString(2, accountNumber);
			pStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public UserAccountsDao(Connection connection) {
		this.connection = connection;
	}

}
