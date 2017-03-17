package com.agile.bl.model;

import java.sql.Timestamp;

/**
 * @author Rupesh Bharuka
 *
 */
public class AgileItems {
	
	private String itemName;
	private int quantity;
	private String description;
	private Timestamp lastModifiedDate;
	
	/**
	 * Initializes a newly created AgileItem objects with the parameters passed
	 * to this method.
	 * 
	 * @param itemName
	 *            A string value used to set the name of item
	 * @param quantity
	 *            An int value used to set the quantity of item
	 * @param description
	 * 			  A string value used to set the description of item
	 * @param lastModifiedDate
	 *            A Timestamp used to set the last modified date of item
	 *             
	 */
	public AgileItems(String itemName, int quantity, String description){
		this.itemName = itemName;
		this.quantity = quantity;
		this.description = description;
	}
	
	public AgileItems(String itemName, int quantity, String description, Timestamp lastModifiedDate){
		this.itemName = itemName;
		this.quantity = quantity;
		this.description = description;
		this.lastModifiedDate = lastModifiedDate;
	}

	public AgileItems(){
		
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
	 * @return An int which is quantity of item
	*/
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity An int passed to set the quantity of item
	*/
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return The string which is description of item
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description The string passed to set the description of item
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Timestamp i.e. last modified date of an item
	 */
	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate A Timestamp passed to set the last modified date of an item
	 */
	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public String toString(){
		return "Item Details [" +"Item Name = " +this.itemName +", Quantity = " +this.quantity
				+", Description = " +this.description +", Last Modified Date = " +this.lastModifiedDate +"] \n";
	}
		
}//end of AgileItem class