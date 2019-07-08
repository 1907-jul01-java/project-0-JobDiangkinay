package com.revature.utilities;


import java.util.ArrayList;

import com.revature.models.Employee;
import com.revature.models.UserAccount;

public class PersonDataUtil {
	private static ArrayList<Employee> employees = new ArrayList<>();
	private static ArrayList<UserAccount> accounts = new ArrayList<>();
	private static ArrayList<UserAccount> pendingAccounts = new ArrayList<>();

	public static ArrayList<UserAccount> initLoadAccounts() {
		accounts.add(new UserAccount("Job", "Diangkinay", "123", "userjob", "passjob", "1929895", 1000));
		accounts.add(new UserAccount("Keith", "Torres", "123", "userkeith", "passkeith", "1929894", 2500));
		employees.add(new Employee("Job", "AdminDiangkinay", "321", "adminjob", "passjob", "Admin"));
		employees.add(new Employee("Keith", "EmpTorres", "321", "empkeith", "passkeith", "Employee"));
		pendingAccounts.add(new UserAccount("Ford", "Torres", "123", "userFord", "passFord", "1929893", 1000));
		return accounts;
	}

	public static ArrayList<UserAccount> loadAccounts() {
		return accounts;
	}

	public static ArrayList<UserAccount> loadPendingAccounts()
	{
		return pendingAccounts;
	}
	
	public static ArrayList<Employee> loadEmployees(){
		return employees;
	}
	
	public static void addAccount(UserAccount p)
	{
		accounts.add(p);
	}
	
	public static void addPendingAccount(UserAccount p)
	{
		pendingAccounts.add(p);
	}
	
	public static void removePendingAccount(UserAccount p)
	{
		pendingAccounts.remove(p);
	}
}
