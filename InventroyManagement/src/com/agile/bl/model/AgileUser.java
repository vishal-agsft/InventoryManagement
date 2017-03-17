package com.agile.bl.model;


/**
 * @author Rupesh Bharuka
 * 
*/
public class AgileUser {

	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
	private boolean isadmin;
	
	
	public AgileUser(){
		this.firstName = "";
		this.lastName = "";
		this.emailId = "";
		this.password = "";
		this.isadmin = false;
	}
	/**
	 * Initializes a newly created AgileUser objects with the parameters passed
	 * to this method.
	 * 
	 * @param firstname
	 *            A string value used to set the first name of user
	 * @param lastname
	 *            A string value used to set the last name of user
	 * @param emailId
	 * 			  A string value used to set the email id of user         
	 * @param password
	 *            A string value used to set the password of user
	 * @param isadmin
	 *			  A boolean value used to set the isadmin status of user
	 */
	public AgileUser(String firstName, String lastName, String emailId, String password, boolean isadmin) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.isadmin = isadmin;
	}

	/**
	 * Initializes a newly created AgileUser objects with the parameters passed
	 * to this method.
	 * 
	 * @param firstName
	 * 			  A string value used to set the first name of user
	 * @param lastName
	 * 			  A string value used to set the last name of user
	 * @param emailId
	 *   		  A string value used to set the email id of user
	 */
	public AgileUser(String firstName, String lastName, String emailId){
		this.firstName = firstName;
		this.lastName  = lastName;
		this.emailId = emailId;
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
	 * @return The string which is password of user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password The string passed to set the password of user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return The boolean value i.e. status of user
	 */
	public boolean getIsadmin() {
		return isadmin;
	}

	/**
	 * @param isadmin The boolean value passed to set the status of  user 
	 */
	public void setIsadmin(boolean isadmin) {
		this.isadmin = isadmin;
	}
	
	
	public String toString(){
		return "User Details [" +"First Name = " +firstName +", Last Name = " +lastName 
				+", Email Id = " +emailId +"]" +"\n";
	}
	
}//end of AgileUser class