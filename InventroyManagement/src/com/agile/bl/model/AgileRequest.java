package com.agile.bl.model;


import java.sql.Timestamp;

/**
 * @author Rupesh Bharuka
 *
 */
public class AgileRequest {

	private int requestId; // primary key
	private int userId;
	private int itemId;
	private int quantity;
	private int requestStatus; // int because 0-declined, 1-approved and 2-pending
	private Timestamp requestedDate;
	private String description;

	
	/**
	 * Initializes a newly created AgileRequest objects with the parameters passed
	 * to this method.
	 * 
	 * @param userId An int value used to set the user id
	 * @param itemId An int value used to set the item id
	 * @param quantity An int value used to set the quantity of requested item
	 * @param requestStatus A boolean value used to set the status of request
	 * @param requestedDate Timestamp used to set the requested date
	 * @param description A string value used to set the description of request
	 */
	public AgileRequest(int userId, int itemId, int quantity, int requestStatus, Timestamp requestedDate,
			String description) {
		
		this.userId = userId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.requestStatus = requestStatus;
		this.requestedDate = requestedDate;
		this.description = description;
	}
	
	/**
	 * Initializes a newly created AgileRequest objects with the parameters passed
	 * to this method.
	 * 
	 * @param userId An int value used to set the user id
	 * @param itemId An int value used to set the item id
	 * @param description A string value used to set the description of request
	 * 
	 */
	public AgileRequest(int userId, int itemId, String description) {
		
		this.userId = userId;
		this.itemId = itemId;
		this.description = description;
	}
	
	/**
	 * Initializes a newly created AgileRequest objects with the parameters passed
	 * to this method.
	 * 
	 * 
	 * @param requestId An int value used to set the request id
	 * @param userId An int value used to set the user id
	 * @param itemId An int value used to set the item id
	 * @param quantity An int value used to set the quantity of requested item
	 * @param requestStatus An int value used to set the request status of requested item
	 * @param description A string value used to set the description of request 
	 * @param requestedDate A Timestamp used to set the current date and time of the requested item
	 * 
	 * 
	 */
	public AgileRequest(int requestId, int userId, int itemId, int quantity, int requestStatus, String description, 
			Timestamp requestedDate) {
		
		this.requestId = requestId;
		this.userId = userId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.requestStatus = requestStatus;
		this.description = description;
		this.requestedDate = requestedDate;
	}

	
	public AgileRequest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return An int which is request id
	 */
	public int getRequestId() {
		return userId;
	}


	/**
	 * @return An int which is user id
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId An int value passed to set the user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return An int which is item id
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * @param itemId An int value passed to set the item id
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return An int which is quantity of requested item
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity An int value passed to set the quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return A boolean value which is status of request
	 */
	public int isRequestStatus() {
		return requestStatus;
	}

	/**
	 * @param requestStatus A boolean value passed to set the status of request
	 */
	public void setRequestStatus(int requestStatus) {
		this.requestStatus = requestStatus;
	}

	/**
	 * @return Timestamp which is requested date of item
	 */
	public Timestamp getRequestedDate() {
		return requestedDate;
	}

	/**
	 * @param requestedDate A Timestamp passed to set the request date of an item
	 */
	public void setRequestedDate(Timestamp requestedDate) {
		this.requestedDate = requestedDate;
	}

	/**
	 * @return A string value which is description of request
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description A string value passed to set the description of an item
	 */
	public void setDescription(String description) {
		this.description = description;
	}	
	
	public String toString(){
		return "User Reqeust Details [" +"Request ID = " +requestId +", User Id = " +userId
				+", Item Id = " +itemId +", Quantity : " +quantity +", Request Status : " +requestStatus 
				+"Description : " +description +"Request Date : " +requestedDate +"]" +"\n";
	}
		

}//end of AgileRequest class

