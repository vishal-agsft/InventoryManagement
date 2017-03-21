package com.agile.bl.controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.agile.bl.dao.AgileItemDaoImplementation;
import com.agile.bl.model.AgileItems;

/**
 * Servlet implementation class AgileEditItem
 * 
 * edits existing inventory
 * 
 */
@WebServlet("/edititem")
public class AgileEditItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(AgileEditItem.class);
	AgileItemDaoImplementation agileItemDao = new AgileItemDaoImplementation();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			int itemQuantity = Integer.valueOf(request.getParameter("itemQuantities"));
			String itemName = request.getParameter("itemName");
			String itemDescription = request.getParameter("itemDescription");
			Timestamp currentTs = new Timestamp(System.currentTimeMillis());
			
			AgileItems agileItems = new AgileItems();
			agileItems.setQuantity(itemQuantity);
			agileItems.setItemName(itemName);
			agileItems.setDescription(itemDescription);
			agileItems.setLastModifiedDate(currentTs);
			
			agileItemDao.updateItemDetails(agileItems, itemName);
			response.sendRedirect("agilelogin");
			
		}catch(Exception e){
			log.error("Unable to edit current item", e.getCause());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);;
	}

}
