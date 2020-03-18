package com.sliit.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sliit.model.*;
import com.sliit.util.DBConnection;

public class CustomerServiceImp implements Customerservice {

	private int size;
	private int count;
	private ResultSet result;
	private ArrayList<Customer> cUsers = null;
	private ArrayList<Users> users = null;
	private int uId;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String image;
	private String telephone;
	private int postalCode;
	private String address;
	private int userType;

	@Override
	public boolean addCustomer(Customer c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCustomer(Customer c) throws ClassNotFoundException, SQLException {

		boolean status = true;

		PreparedStatement ps = DBConnection.getMainConnection()
				.prepareStatement("update Users set userName = '" + c.getUserName() + "', firstName = '"
						+ c.getFirstName() + "', lastName = '" + c.getLastName() + "', email = '" + c.getEmail()
						+ "', telephone = '" + c.getTelephone() + "', postalCode = " + c.getPostalCode()
						+ ", address = '" + c.getAddress() + "', userType = '" + c.getUserType() + "' where uId = "
						+ c.getuId());

		int res = ps.executeUpdate();

		if (res > 0)
			status = true;
		else
			status = false;
		return status;
	}

	@Override
	public boolean removeCustomer(int customerId) throws ClassNotFoundException, SQLException {

		boolean status = true;

		PreparedStatement ps = DBConnection.getMainConnection()
				.prepareStatement("Delete from Users where uId = " + customerId);
		int result = ps.executeUpdate();
		if (result > 0) {

			PreparedStatement ps1 = DBConnection.getTmpConnection()
					.prepareStatement("Insert into user_tmp(userId) values(" + customerId + ")");
			int tmpResult = ps1.executeUpdate();

			if (tmpResult > 0) {
				status = true;
			} else
				status = false;
		} else
			status = false;
		return status;
	}

	@Override
	public ArrayList<Users> searchUser(Users u) throws ClassNotFoundException, SQLException {

		if (u.getFirstName().isEmpty() && u.getUserName().isEmpty() && u.getEmail().isEmpty()) {
			users = null;
			return users;

		} else if (u.getFirstName().isEmpty() && u.getEmail().isEmpty()) {
			PreparedStatement ps = DBConnection.getMainConnection()
					.prepareStatement("select * from Users where userName LIKE '%" + u.getUserName() + "%'");
			ResultSet rs = ps.executeQuery();
			result = rs;
		} else if (u.getFirstName().isEmpty() && u.getUserName().isEmpty()) {
			PreparedStatement ps = DBConnection.getMainConnection()
					.prepareStatement("select * from Users where email LIKE '" + u.getEmail() + "%'");
			ResultSet rs = ps.executeQuery();
			result = rs;
		} else if (u.getEmail().isEmpty() && u.getUserName().isEmpty()) {
			PreparedStatement ps = DBConnection.getMainConnection()
					.prepareStatement("select * from Users where firstName LIKE '" + u.getFirstName() + "%'");
			ResultSet rs = ps.executeQuery();
			result = rs;
		} else if (u.getEmail().isEmpty()) {
			PreparedStatement ps = DBConnection.getMainConnection()
					.prepareStatement("select * from Users where userName LIKE '%" + u.getUserName()
							+ "%' and firstName LIKE '" + u.getFirstName() + "%'");
			ResultSet rs = ps.executeQuery();
			result = rs;
		} else if (u.getFirstName().isEmpty()) {
			PreparedStatement ps = DBConnection.getMainConnection()
					.prepareStatement("select * from Users where userName LIKE '%" + u.getUserName()
							+ "%' and email LIKE '" + u.getEmail() + "%'");
			ResultSet rs = ps.executeQuery();
			result = rs;
		} else if (u.getUserName().isEmpty()) {
			PreparedStatement ps = DBConnection.getMainConnection()
					.prepareStatement("select * from Users where firstName LIKE '" + u.getFirstName()
							+ "%' and email LIKE '" + u.getEmail() + "%'");
			ResultSet rs = ps.executeQuery();
			result = rs;
		} else if (!(u.getFirstName().isEmpty() && u.getUserName().isEmpty() && u.getEmail().isEmpty())) {
			PreparedStatement ps = DBConnection.getMainConnection()
					.prepareStatement("select * from Users where firstName LIKE '" + u.getFirstName()
							+ "%' and userName = '" + u.getUserName() + "' and email = '" + u.getEmail() + "'");
			ResultSet rs = ps.executeQuery();
			result = rs;
		}

		ArrayList<Users> user = new ArrayList<Users>();

		while (result.next()) {

			this.uId = result.getInt(1);
			this.userName = result.getString(2);
			this.firstName = result.getString(3);
			this.lastName = result.getString(4);
			this.email = result.getString(5);
			this.password = result.getString(6);
			this.image = result.getString(7);
			this.telephone = result.getString(8);
			this.postalCode = result.getInt(9);
			this.address = result.getString(10);
			this.userType = result.getInt(11);

			user.add(new Customer(uId, userName, firstName, lastName, email, password, telephone, postalCode, address,
					image, userType));
			++this.size;
		}

		if (this.size > 0) {
			this.users = user;
		} else {
			return users;
		}

		return users;
	}

	@Override
	public ArrayList<Customer> getAllUsers() throws SQLException, ClassNotFoundException {

		PreparedStatement ps = DBConnection.getMainConnection().prepareStatement("select * from Users");
		ResultSet result = ps.executeQuery();

		ArrayList<Customer> user = new ArrayList<Customer>();

		while (result.next()) {
			uId = result.getInt(1);
			userName = result.getString(2);
			firstName = result.getString(3);
			lastName = result.getString(4);
			email = result.getString(5);
			this.password = result.getString(6);
			image = result.getString(7);
			telephone = result.getString(8);
			postalCode = result.getInt(9);
			address = result.getString(10);
			userType = result.getInt(11);

			user.add(new Customer(uId, userName, firstName, lastName, email, password, telephone, postalCode, address,
					image, userType));
			++size;
		}

		if (size > 0)
			this.cUsers = user;
		else
			this.cUsers = null;

		return this.cUsers;
	}

	@Override
	public int countUsers() throws ClassNotFoundException, SQLException {

		PreparedStatement ps = DBConnection.getMainConnection().prepareStatement("select count(*) from Users");
		ResultSet rs = ps.executeQuery();

		while (rs.next())
			count = rs.getInt(1);

		if (count == 0)
			count = -1;

		return count;
	}

}
