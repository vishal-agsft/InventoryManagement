package com.agile.bl.controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agile.bl.dao.AgileItemDaoImplementation;
import com.agile.bl.model.AgileItems;

/**
 * Servlet implementation class AgileEditItem
 */
@WebServlet("/edititem")
public class AgileEditItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
			System.out.println("redirecting to admin page");
			response.sendRedirect("agilelogin");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);;
	}

}
