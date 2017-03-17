package com.agile.bl.dao;

public interface AgileLoginDao {
	
	/**
	 * @param email 
	 * 			an email-id of user
	 * @param password
	 * 			a password of user
	 * @return
	 * 			returns true iff specified email id and password exists in the system as simple user
	 */
	public boolean authenticateUser(String email, String password);
	
	
	/**
	 * @param email
	 * 			an email-id of user
	 * @param password
	 * 			a password of user
	 * @return
	 * 			returns true iff specified email id and password exists in the system as admin user
	 */
	public boolean authenticateAdmin(String email, String password);
	
}
