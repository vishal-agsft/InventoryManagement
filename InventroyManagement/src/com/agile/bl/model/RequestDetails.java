package com.agile.bl.model;

public class RequestDetails {
	
	private String firstName;
	private String lastName;
	private String emailId;
	private String itemName;
	private int totalQuantity;
	private int requestId;
	private int itemId;
	
	/**
	 * Initializes a newly created RequestDetails objects with the parameters passed
	 * to this method.
	 * 
	 * @param firstname
	 *            A string value used to set the first name of user
	 * @param lastname
	 *            A string value used to set the last name of user
	 * @param emailId
	 * 			  A string value used to set the email id of user 
	 * @param itemName
	 *            A string value used to set the name of item
	 * @param totalQuantity
	 * 			  An int value used to set the total quantity for requested item
	 */
	public RequestDetails(String firstName, String lastName, String emailId, String itemName, int totalQuantity, int requestId, int itemId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.itemName = itemName;
		this.totalQuantity = totalQuantity;
		this.requestId = requestId;
		this.itemId = itemId;
	}
	
	
	/**
	 * @return The string which is first name of user  
	 * 
	 */
	public String getFirstName() {
		return firstName;
	}
	
	
	/**
	 * @param firstname The string passed to set the first name of user
	*/
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return The string which is last name of user  
	 * 
	*/
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastname The string passed to set the last name of user
	*/
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return The string which is email id of user
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId The string passed to set the mail id of user
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return The string which is item name
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName The string passed to set the item name
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return An int which is total quantity for the requested item
	 */
	public int getTotalQuantity() {
		return totalQuantity;
	}

	/**
	 * @param quantity An int passed to set the total quantity for the requested item
	 */
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
		
	/**
	 * @return An int which is request id of the requested user
	 */
	public int getRequestId() {
		return requestId;
	}

	
	/**
	 * @param requestId An int passed to set the request id 
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	
	/**
	 * @return An int which is item id of the requested item
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * @param itemId An int passed to set the item id 
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	public String toString(){
		return "Request Details [" +"First Name : " +firstName +", Last Name : " +lastName
				+", Email Id : " +emailId +", Item Name : " +itemName +", Quantity : " +totalQuantity +", Request Id : " +requestId +", Item Id : " +itemId +"]" +"\n";
	}	
	
	
}
