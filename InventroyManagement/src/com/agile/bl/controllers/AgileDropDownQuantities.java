package com.agile.bl.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agile.bl.dao.AgileItemDao;
import com.agile.bl.dao.AgileItemDaoImplementation;
import com.agile.bl.model.AgileItems;

/**
 * Servlet implementation class AgileDropDownQuantities
 * @author Vishal Arora
 * 
 */
@WebServlet("/quantity")
public class AgileDropDownQuantities extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			AgileItemDao agileItems = new AgileItemDaoImplementation();
			String itemName = request.getParameter("");
			int quantities = 0;

			List<AgileItems> itemList = agileItems.getItemDetails();

			for (AgileItems agItems : itemList) {
				if (agItems.getItemName().equals(itemName)) {
					quantities = agItems.getQuantity();
					break;
				}
			}

			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<p>" + quantities + "</p>");
			out.println("</body>");
			out.println("</html>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
