package com.agile.bl.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.agile.bl.dao.AgileItemDao;
import com.agile.bl.dao.AgileItemDaoImplementation;
import com.agile.bl.model.AgileItems;

/**
 * Servlet implementation class AgileDropDownItemList
 */
@WebServlet("/description")
public class AgileDropDownItemList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(AgileDropDownItemList.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AgileItemDao agileItems = new AgileItemDaoImplementation();
		try {
			String itemName = request.getParameter("itemName");
			String description = null;

			List<AgileItems> itemList = agileItems.getItemDetails();

			for (AgileItems agItems : itemList) {
				if (agItems.getItemName().equals(itemName)) {
					description = agItems.getDescription();
					break;
				}
			}

			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<p>" + description + "</p>");
			out.println("</body>");
			out.println("</html>");

		} catch (Exception e) {
			log.error("list not populated ", e.getCause());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
