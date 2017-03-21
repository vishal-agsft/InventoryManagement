package com.agile.bl.controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.agile.bl.dao.AgileItemDao;
import com.agile.bl.dao.AgileItemDaoImplementation;
import com.agile.bl.model.AgileItems;

/**
 * Servlet implementation class AgileAddNewItem
 * 
 * @author Vishal Arora adds a new item
 * 
 */
@WebServlet("/newitem")
public class AgileAddNewItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AgileItemDao agileItemImpl = new AgileItemDaoImplementation();
	private static Logger log = Logger.getLogger(AgileAddNewItem.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession(false);

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache"); 
		response.setDateHeader("Expires", 0); 

		if (httpSession == null) {
			response.sendRedirect("login");
		} else {
			try {
				String itemName = request.getParameter("itemname");
				int quantity = Integer.valueOf(request.getParameter("quantity"));
				Timestamp currentTS = new Timestamp(System.currentTimeMillis());
				String description = request.getParameter("discription");

				AgileItems agileItems = new AgileItems();
				agileItems.setItemName(itemName);
				agileItems.setQuantity(quantity);
				agileItems.setLastModifiedDate(currentTS);
				agileItems.setDescription(description);

				agileItemImpl.addItemDetails(agileItems);
				response.sendRedirect("agilelogin");

			} catch (Exception e) {
				log.error("Unable to add a new item");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
