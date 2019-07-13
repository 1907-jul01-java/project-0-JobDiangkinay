package com.revature.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.UserAccount;

public class EmployeeDao implements Dao<Employee> {
	Connection connection;

	@Override
	public void insert(Employee emp) {
		// for personstable
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"insert into persons(firstname, lastname, phonenumber, username) values(?, ?, ?, ?)");
			pStatement.setString(1, emp.getFirstName());
			pStatement.setString(2, emp.getLastName());
			pStatement.setString(3, emp.getPhoneNumber());
			pStatement.setString(4, emp.getUserName());
			pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// for accountcredentialstable
		try {
			PreparedStatement pStatement = connection
					.prepareStatement("insert into accountcredentials(username, password, usertype) values(?, ?, ?)");
			pStatement.setString(1, emp.getUserName());
			pStatement.setString(2, emp.getPassword());
			pStatement.setString(3, emp.getUserType());
			pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Success");

	}
	
	public void acceptPendingAccount(UserAccount penUser) {
		try {
			PreparedStatement pStatement = connection
					.prepareStatement("insert into bankaccounts(accountnumber, balance, username) values(?, ?, ?)");
			pStatement.setString(1, penUser.getAccountNumber());
			pStatement.setDouble(2, penUser.getBalance());
			pStatement.setString(3, penUser.getUserName());
			pStatement.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		deletePendingAccount(penUser.getAccountNumber());
	}
	
	public void deletePendingAccount(String accountnumber) {
		try {
			PreparedStatement pStatement = connection
					.prepareStatement("delete from pendingbankaccounts where accountnumber = ?");
			pStatement.setString(1, accountnumber);
			pStatement.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<Employee> getAll() {
		Employee employee;
		List<Employee> employees = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"select * from persons join accountcredentials on persons.username = accountcredentials.username where usertype != 'USER'");
			while (resultSet.next()) {
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				String phoneNumber = resultSet.getString("phonenumber");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String usertype = resultSet.getString("usertype");

				employee = new Employee(firstName, lastName, phoneNumber, username, password, usertype);
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}
	
	public UserAccount getSpecificPendingAccount(String penaccountNumber) {
		UserAccount penAccount = null;
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"select * from pendingbankaccounts where accountnumber = ?");
			pStatement.setString(1, penaccountNumber);
			ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				String accountNumber = resultSet.getString("accountnumber");
				String userName = resultSet.getString("username");
				double balance = resultSet.getDouble("balance");
				
				penAccount = new UserAccount(userName, accountNumber, balance);
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return penAccount;
	}
	
	public UserAccount getSpecificBankAccount(String bankAccountNumber) {
		UserAccount bankAccount = null;
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"select * from bankaccounts where accountnumber = ?");
			pStatement.setString(1, bankAccountNumber);
			ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				String accountNumber = resultSet.getString("accountnumber");
				String userName = resultSet.getString("username");
				double balance = resultSet.getDouble("balance");
				
				bankAccount = new UserAccount(userName, accountNumber, balance);
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return bankAccount;
	}

	public Employee getEmployee(String curUserName) {
		Employee curEmp = null;
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
				String userType = resultSet.getString("usertype");

				curEmp = new Employee(firstName, lastName, phoneNumber, username, password, userType);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		if (curEmp != null) {
			return curEmp;
		}
		return null;
	}
	
	public boolean checkPendingAccountNumber(String accountNumber) {
		boolean isValid = false;
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"select * from pendingbankaccounts where accountnumber = ?");
			pStatement.setString(1, accountNumber);
			ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				isValid = true;
			}
		}catch (Exception ex) {
			
		}
	return isValid;	
	}
	
	public boolean checkBankAccountNumber(String accountNumber) {
		boolean isValid = false;
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"select * from bankaccounts where accountnumber = ?");
			pStatement.setString(1, accountNumber);
			ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				isValid = true;
			}
		}catch (Exception ex) {
			
		}
	return isValid;	
	}
	
	public ArrayList<UserAccount> getAllPendingAccounts(){
		ArrayList<UserAccount> pendingUsers = new ArrayList<>();
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"select * from pendingbankaccounts");
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				String username = resultSet.getString("username");
				double balance = resultSet.getDouble("balance");
				String accountnumber = resultSet.getString("accountnumber");

				UserAccount pUser = new UserAccount(username, accountnumber, balance);
				pendingUsers.add(pUser);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return pendingUsers;
	}

	public ArrayList<UserAccount> getAllBankAccounts() {
		ArrayList<UserAccount> bankAccounts = new ArrayList<>();
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"select * from persons join accountcredentials on persons.username = accountcredentials.username join bankaccounts on persons.username = bankaccounts.username where usertype = 'USER'");
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				String phoneNumber = resultSet.getString("phonenumber");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String userType = resultSet.getString("usertype");
				String accountNumber = resultSet.getString("accountnumber");
				double balance = resultSet.getDouble("balance");

				UserAccount userAccount = new UserAccount(firstName, lastName, phoneNumber, username, password,
						accountNumber, balance);
				bankAccounts.add(userAccount);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return bankAccounts;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public EmployeeDao(Connection connection) {
		this.connection = connection;
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
	}
	
	public void deleteBankAccount(String accountNumber) {
		try {
			PreparedStatement pStatement = connection
					.prepareStatement("delete from bankaccounts where accountnumber = ?");
			pStatement.setString(1, accountNumber);
			pStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
