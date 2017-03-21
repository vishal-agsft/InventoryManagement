package com.agile.bl.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.agile.bl.dao.AgileItemDao;
import com.agile.bl.dao.AgileItemDaoImplementation;
import com.agile.bl.dao.AgileRequestDao;
import com.agile.bl.dao.AgileRequestDaoImplementation;
import com.agile.bl.dao.AgileUserDao;
import com.agile.bl.dao.AgileUserDaoImplementation;
import com.agile.bl.model.AgileRequest;

/**
 * Servlet implementation class AgileMakeNewRequest
 */
@WebServlet("/newrequest")
public class AgileMakeNewRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AgileRequestDao agileReqDao = new AgileRequestDaoImplementation();
	AgileItemDao agileItemDao = new AgileItemDaoImplementation();
	AgileUserDao agileUserDao = new AgileUserDaoImplementation();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession httpSession = request.getSession(false);
			String emailId = (String) httpSession.getAttribute("email");
			String itemName = request.getParameter("itemName");

			int userId = agileUserDao.getUserId(emailId);
			int itemId = agileItemDao.getItemId(itemName);
			Timestamp requestedDate = new Timestamp(System.currentTimeMillis());
			int currentRequestedStatus = 2;

			String description = "";

			AgileRequest agileReq = new AgileRequest();
			agileReq.setUserId(userId);
			agileReq.setItemId(itemId);
			agileReq.setRequestedDate(requestedDate);
			agileReq.setRequestStatus(currentRequestedStatus);
			agileReq.setDescription(description);

			agileReqDao.addUserRequests(agileReq);
			PrintWriter out=response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>Requested</h1>");
			out.println("</body>");
			out.println("</html>");
		/*	response.sendRedirect("agilelogin");*/
//			RequestDispatcher dispatcher = request.getRequestDispatcher("adminpanel.jsp");
//			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}