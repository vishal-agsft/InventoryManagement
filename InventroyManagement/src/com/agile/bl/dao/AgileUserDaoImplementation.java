package com.agile.bl.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.agile.bl.model.AgileUser;

public class AgileUserDaoImplementation implements AgileUserDao{
	
	private static final int MIN_ROW_COUNT = 0;
	
	@Override
	public String addUserDetails(AgileUser object) {
		Connection connection = DBConnection.getInstance().getConnection();
		String query = "insert into User(firstname, lastname, emailid, password, isadmin) values(?,?,?,?,?)";
		System.out.println("adding user");
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, object.getFirstName());
			ps.setString(2, object.getLastName());
			ps.setString(3, object.getEmailId());
			ps.setString(4, object.getPassword());
			ps.setBoolean(5, object.getIsadmin());

//			int rowCount = ps.executeUpdate();

			if(isRowAffected(ps.executeUpdate())){
				return "Record stored successfully..!";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Record can not be stored..!";
	}

	private boolean isRowAffected(int rowCount){
		// row count is total count, to check number of rows affected
		return rowCount > MIN_ROW_COUNT;
	}

	@Override
	public List<AgileUser> getUserDetails() {
		Connection connection = DBConnection.getInstance().getConnection();
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
		Connection connection = DBConnection.getInstance().getConnection();
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
			Connection connection = DBConnection.getInstance().getConnection();
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

				if(isRowAffected(ps.executeUpdate())){
					return "Record updated successfully..!";
				}			

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return "Record can not be updated";
		}// end of updateUserDetails() method

		@Override
		public String deleteUserDetails(String emailId) {
			Connection connection = DBConnection.getInstance().getConnection();
			String query = "delete from User where emailid = ?";

			try {
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, emailId);

				int rowCount = ps.executeUpdate();

				if(isRowAffected(ps.executeUpdate())){
					return "Record deleted successfully..!";
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return "Record can not be deleted";
		} //end of deleteUserDetails() method
	 */
}