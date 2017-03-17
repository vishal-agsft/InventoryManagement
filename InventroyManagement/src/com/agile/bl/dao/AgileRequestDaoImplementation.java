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

	private Connection connection = DBConnection.getInstance().getConnection();
	
	@Override
	public String addUserRequests(AgileRequest object) {
//		String query = "insert into Request (userid, itemid, quantity, description, requestdate) values(?,?,?,?,?)";
		String query = "insert into Request (userid, itemid, description, requestdate) values(?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, object.getUserId());
			ps.setInt(2, object.getItemId());
//			ps.setInt(3, object.getQuantity());
			ps.setString(3, object.getDescription());
			ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			
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
	public List<AgileRequest> getAllPendingRequests() {
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
		String query = "UPDATE Items SET quantity = quantity - 1 WHERE itemid = ? and quantity > 0";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, itemId);
			
			int rowCount = ps.executeUpdate();
			
			if(checkRowCount(rowCount)){
				return "Items updated successfully..!";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "Items can not be updated";
	}


	@Override
	public String updateRequestStatus(int requestId, AgileRequestStatus status) {

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

			int rowCount = ps.executeUpdate();

			if(checkRowCount(rowCount))
				return "Request status is updated successfully..!";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Request status can not be updated..!";
	}

	@Override
	public List<RequestDetails> getRequestDetails() {
		
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
