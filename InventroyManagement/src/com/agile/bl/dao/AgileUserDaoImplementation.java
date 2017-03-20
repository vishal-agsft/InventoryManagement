package com.agile.bl.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.agile.bl.model.AgileUser;

public class AgileUserDaoImplementation implements AgileUserDao{

	private Connection connection = DBConnection.getInstance().getConnection();

	@Override
	public String addUserDetails(AgileUser object) {
		String query = "insert into User(firstname, lastname, emailid, password, isadmin) values(?,?,?,?,?)";
		System.out.println("adding user");
		try {
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, object.getFirstName());
			ps.setString(2, object.getLastName());
			ps.setString(3, object.getEmailId());
			ps.setString(4, object.getPassword());
			ps.setBoolean(5, object.getIsadmin());

			int rowCount = ps.executeUpdate();

			if(checkRowCount(rowCount)){
				return "Record stored successfully..!";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Record can not be stored..!";
	}

	private boolean checkRowCount(int rowCount){

		if(rowCount > 0)
			return true;

		return false;
	}

	@Override
	public List<AgileUser> getUserDetails() {

		String query = "select *from User";
		List<AgileUser> listOfUsers = new ArrayList<>();


		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				String firstName = rs.getString("firstname");
				String lastName  = rs.getString("lastname");
				String emailId   = rs.getString("emailid");

				listOfUsers.add(new AgileUser(firstName, lastName, emailId));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listOfUsers;

	}//end of getUserDetails() method

	@Override
	public int getUserId(String emailId) {

		String query = "SELECT userid FROM User WHERE emailid = ?";
		int userId = 0;

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, emailId);

			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				userId = rs.getInt("userid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userId;
	}

	/*
		@Override
		public String updateUserDetails(AgileUser object, String emailId) {
			String query = "update User set firstname=?, lastname=?, emailid=?, password=?, isadmin=? where emailid = ?";

			try {
				PreparedStatement ps = connection.prepareStatement(query);

				ps.setString(1, object.getFirstName());
				ps.setString(2, object.getLastName());
				ps.setString(3, object.getEmailId());
				ps.setString(4, object.getPassword());
				ps.setBoolean(5, object.getIsadmin());
				ps.setString(6, emailId);

				int rowCount = ps.executeUpdate();

				if(checkRowCount(rowCount)){
					return "Record updated successfully..!";
				}			

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return "Record can not be updated";
		}// end of updateUserDetails() method

		@Override
		public String deleteUserDetails(String emailId) {
			String query = "delete from User where emailid = ?";

			try {
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, emailId);

				int rowCount = ps.executeUpdate();

				if(checkRowCount(rowCount)){
					return "Record deleted successfully..!";
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return "Record can not be deleted";
		} //end of deleteUserDetails() method
	 */
}