package com.revature.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;

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

		}
		return employees;
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

}
