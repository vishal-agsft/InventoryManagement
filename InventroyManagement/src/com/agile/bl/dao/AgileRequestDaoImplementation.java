	package com.agile.bl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.agile.bl.model.AgileRequest;
import com.agile.bl.model.RequestDetails;
import com.agile.bl.utility.AgileRequestStatus;

public class AgileRequestDaoImplementation implements AgileRequestDao {

	private static final int MIN_ROW_COUNT = 0;
	
	@Override
	public String addUserRequests(AgileRequest object) {
		Connection connection = DBConnection.getInstance().getConnection();
		String query = "insert into Request (userid, itemid, description, requestdate) values(?,?,?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, object.getUserId());
			ps.setInt(2, object.getItemId());
			ps.setString(3, object.getDescription());
			ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			
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
	public List<AgileRequest> getAllPendingRequests() {
		Connection connection = DBConnection.getInstance().getConnection();
		String query = "SELECT * FROM Request WHERE requeststatus = 2";
		List<AgileRequest>listOfAllPendingRequests = new ArrayList<>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				listOfAllPendingRequests.add(new AgileRequest(rs.getInt("requestid"), rs.getInt("userid"), rs.getInt("itemid"), 
											 rs.getInt("quantity"), rs.getInt("requeststatus"), rs.getString("description"), rs.getTimestamp("requestdate")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return listOfAllPendingRequests;
	}

	@Override
	public String approveRequest(int itemId) {
		Connection connection = DBConnection.getInstance().getConnection();
		String query = "UPDATE Items SET quantity = quantity - 1 WHERE itemid = ? and quantity > 0";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, itemId);
			
//			int rowCount = ps.executeUpdate();
			
			if(isRowAffected(ps.executeUpdate())){
				return "Items updated successfully..!";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "Items can not be updated";
	}


	@Override
	public String updateRequestStatus(int requestId, AgileRequestStatus status) {
		Connection connection = DBConnection.getInstance().getConnection();
		String query = null;

		switch(status){

		case APPROVED : 
			query = "UPDATE Request SET requeststatus = 1 WHERE requestid = ?";
			break;

		case DECLINED:
			query = "UPDATE Request SET requeststatus = 0 WHERE requestid = ?";
			break;
		}

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,requestId);

//			int rowCount = ps.executeUpdate();

			if(isRowAffected(ps.executeUpdate()))
				return "Request status is updated successfully..!";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Request status can not be updated..!";
	}

	@Override
	public List<RequestDetails> getRequestDetails() {
		Connection connection = DBConnection.getInstance().getConnection();
		String query = "SELECT " 
				+"firstname, lastname, emailid, itemtable.itemname, requesttable.quantity, requesttable.requestid, itemtable.itemid"
				+" FROM User As usertable" 
				+" INNER JOIN Request As requesttable"
				+" ON usertable.userid = requesttable.userid"
				+" INNER JOIN Items As itemtable"
				+" ON itemtable.itemid = requesttable.itemid"
				+" WHERE requesttable.requeststatus = 2";
						
		
		List<RequestDetails>listOfRequests = new ArrayList<>();
		
		try {
			
			PreparedStatement ps = connection.prepareStatement(query);
		
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String emailId = rs.getString("emailid");
				String itemName = rs.getString("itemname");
				int totalQuantity = rs.getInt("quantity");
				int requestId = rs.getInt("requestid");
				int itemId = rs.getInt("itemid");
				
				listOfRequests.add(new RequestDetails(firstName, lastName, emailId, itemName, totalQuantity, requestId, itemId));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listOfRequests;
	}

}
