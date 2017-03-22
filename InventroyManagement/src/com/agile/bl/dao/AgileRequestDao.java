package com.agile.bl.dao;

import java.util.List;

import com.agile.bl.model.AgileRequest;
import com.agile.bl.model.RequestDetails;
import com.agile.bl.utility.AgileRequestStatus;

public interface AgileRequestDao {
	
	/**
	 * @param object
	 * 			A reference of type AgileRequests has to be passed in order to add request details
	 * @return
	 * 			The string value i.e. response which specifies whether request details are added or not
	 */
	public String addUserRequests(AgileRequest object);
	
	
	/**
	 * @return The List of AgileRequest which contains details about all the pending requests
	 */
	public List<AgileRequest> getAllPendingRequests();
	
	
	/**
	 * @param itemId
	 * 			An int value has to be passed in order to approve request for this item
	 * @return
	 * 			The string value i.e. response message
	 */
	public String approveRequest(int itemId, int subtractQuantity);
	
	
	/**
	 * @param requestId 
	 * 			An int value i.e. request id
	 * @param object
	 * 			A reference of type {@link AgileRequestStatus}
	 * @return
	 * 			The string value i.e. response message
	 */
	public String updateRequestStatus(int requestId, AgileRequestStatus object);
	
	
	/**
	 * @param userId
	 * 			An int value has to be passed
	 * @param itemId
	 * 			An int value has to be passed
	 * @return
	 * 			The List of RequestDetails which contains details about user and requested item with quantity 
	 */
	public List<RequestDetails> getRequestDetails();	
}
