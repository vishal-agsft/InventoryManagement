package com.agile.bl.dao;

import java.util.List;

import com.agile.bl.model.AgileUser;

public interface AgileUserDao {
	
	/**
	 * @param object 
	 * 			A reference of type AgileUser has to be passed in order to add user details
	 * @return
	 * 			The string value i.e. response which specifies whether user is added or not
	 */
	public String addUserDetails(AgileUser object);
	
	
	/**
	 * @return Returns the list of AgileUser which contains the detail information of all the users in the system
	 */
	public List<AgileUser> getUserDetails();
	
//	public String updateUserDetails(AgileUser object, String emailId);
//	public String deleteUserDetails(String emailId);
}