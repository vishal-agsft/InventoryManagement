package com.agile.bl.dao;

import java.util.List;

import com.agile.bl.model.AgileItems;

public interface AgileItemDao {
	
	/**
	 * @param object	
	 * 			A reference of type AgileItem has to be passed in order to add item details
	 * @return
	 * 			The string value i.e. response which specifies whether item is added or not
	 */
	public String addItemDetails(AgileItems object);
	
	
	/**
	 * @return Returns the list of AgileItems which contains the detail information of all the items available in the system
	 */
	public List<AgileItems> getItemDetails();
	
	
	/**
	 * @param object 
	 * 			A reference of type AgileItems has to be passed in order to update all the item details
	 * @param itemName
	 * 			The string which identifies which item has to be updated
	 * @return
	 * 			The string value i.e. response message which specifies whether item details are updated or not 
	 */
	public String updateItemDetails(AgileItems object, String itemName);
	
	
	/**
	 * @param itemName
	 * 			A string value i.e. item name has to be passed to delete this item 
	 * @return
	 * 			The string value i.e. response message which specifies whether item details are deleted or not
	 */
	public String deleteItemDetails(String itemName);
}