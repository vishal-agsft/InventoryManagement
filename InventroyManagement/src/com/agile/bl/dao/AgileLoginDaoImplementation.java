package com.agile.bl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgileLoginDaoImplementation implements AgileLoginDao{

	@Override
	/**
	 * @param email
	 *            It is compulsory to pass a string which contains an email id
	 *            of user
	 * @param password
	 *            It is compulsory to pass a string which contains password of
	 *            user
	 * 
	 * @return A boolean value true iff specified user exists in the system else
	 *         false
	 */
	public boolean authenticateUser(String email, String password) {
		Connection connection = DBConnection.getInstance().getConnection();
		boolean status = false;

		try {
			String query = "SELECT * FROM User where emailid = ? and password = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) { // loop will iterate only once
				status = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	} // end of authenticateUser() method


	@Override
	/**
	 * @param email
	 *            It is compulsory to pass a string which contains an email id
	 *            of user
	 * @param password
	 *            It is compulsory to pass a string which contains password of
	 *            user
	 * @return A boolean value true if specified admin exists in the system
	 *         else false
	 */
	public boolean authenticateAdmin(String email, String password) {
		Connection connection = DBConnection.getInstance().getConnection();
		boolean status = false;

		try {
			String query = "SELECT isadmin FROM User where emailid = ? and password = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) { //loop will iterate only once
				status = rs.getBoolean("isadmin");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

}
