package com.agile.bl.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.agile.bl.dao.AgileItemDao;
import com.agile.bl.dao.AgileItemDaoImplementation;
import com.agile.bl.dao.AgileLoginDao;
import com.agile.bl.dao.AgileLoginDaoImplementation;
import com.agile.bl.dao.AgileRequestDao;
import com.agile.bl.dao.AgileRequestDaoImplementation;
import com.agile.bl.dao.AgileUserDao;
import com.agile.bl.dao.AgileUserDaoImplementation;
import com.agile.bl.model.AgileItems;
import com.agile.bl.model.AgileUser;
import com.agile.bl.model.RequestDetails;

/**
 * Servlet implementation class AgileLogin
 * 
 * @author Vishal Arora
 * 
 *         This servlet performs the task of logging the user into the system...
 *         There are internally two types of log in actions 1. By admin 2. By
 *         common user The authenticity check of whether the user exists in the
 *         database, and in what type of role is performed here.
 * 
 */
@WebServlet("/agilelogin")
public class AgileLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(AgileLogin.class);

	// singleton instance of the DB class..
	AgileItemDao agileItemDao = new AgileItemDaoImplementation();
	AgileUserDao agileUserDao = new AgileUserDaoImplementation();
	AgileRequestDao agileReqDao = new AgileRequestDaoImplementation();
	AgileLoginDao agileLoginDao = new AgileLoginDaoImplementation();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession httpSession = request.getSession(false);
			RequestDispatcher dispatcher;

			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);

			
			
			if (httpSession == null || httpSession.getAttribute("email") == null) {
				System.out.println("Null Session Detected ... creating new one");

				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
				response.setHeader("Pragma", "no-cache");
				response.setDateHeader("Expires", 0);
				String email = request.getParameter("email");
				String password = request.getParameter("pwd");
				// checks for user existence
				if (agileLoginDao.authenticateUser(email, password)) {
					httpSession = request.getSession(true);
					httpSession.setAttribute("email", email);
					httpSession.setAttribute("password", password);

					setUpPageValues(request, response);

					// checks for admin privilege.
					if (agileLoginDao.authenticateAdmin(email, password)) {
						dispatcher = request.getRequestDispatcher("adminpanel.jsp");
						dispatcher.forward(request, response);
					} else {
						dispatcher = request.getRequestDispatcher("UsersView.jsp");
						dispatcher.forward(request, response);
					}

				} else {
					// response.sendRedirect("login.jsp");
					String log="loginfail";
					request.setAttribute("fail", log);
					dispatcher = request.getRequestDispatcher("errorpage.jsp");
					dispatcher.forward(request, response);
				}
			} else if (httpSession != null && httpSession.getAttribute("email") != null) {
				System.out.println("session already exists");
/*				String email1 = request.getParameter("email");
				String password1 = request.getParameter("pwd");*/
				String email = (String) httpSession.getAttribute("email");
				String password = (String) httpSession.getAttribute("password");

				if (agileLoginDao.authenticateAdmin(email, password)) {
					setUpPageValues(request, response);

					dispatcher = request.getRequestDispatcher("adminpanel.jsp");
					dispatcher.forward(request, response);

				} else {
					setUpPageValues(request, response);
					
					dispatcher = request.getRequestDispatcher("UsersView.jsp");
					dispatcher.forward(request, response);
				}

			}

		} catch (Exception e) {
			log.error("Unable to proceed with request", e.getCause());
		}
	}
	
	
	private void setUpPageValues(HttpServletRequest request, HttpServletResponse response){
		List<AgileItems> allItems = new ArrayList<>();
		List<RequestDetails> allPendingRequests = new ArrayList<>();
		List<AgileUser> allUsers = new ArrayList<>();

		allItems = agileItemDao.getItemDetails();
		allPendingRequests = agileReqDao.getRequestDetails();
		allUsers = agileUserDao.getUserDetails();
		request.setAttribute("itemsList", allItems);
		request.setAttribute("reqList", allPendingRequests);
		request.setAttribute("usersList", allUsers);
	}

}