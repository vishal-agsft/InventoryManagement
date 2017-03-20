package com.agile.bl.loginout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.agile.bl.dao.AgileItemDaoImplementation;
import com.agile.bl.dao.AgileLoginDaoImplementation;
import com.agile.bl.dao.AgileRequestDaoImplementation;
import com.agile.bl.dao.AgileUserDaoImplementation;
import com.agile.bl.model.AgileItems;
import com.agile.bl.model.AgileRequest;
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

	// singleton instance of the DB class..
	AgileItemDaoImplementation agileItemDao = new AgileItemDaoImplementation();
	AgileUserDaoImplementation agileUserDao = new AgileUserDaoImplementation();
	AgileRequestDaoImplementation agileReqDao = new AgileRequestDaoImplementation();
	AgileLoginDaoImplementation agileLoginDao = new AgileLoginDaoImplementation();

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

					List<AgileItems> allItems = new ArrayList<>();
					List<RequestDetails> allPendingRequests = new ArrayList<>();
					List<AgileUser> allUsers = new ArrayList<>();

					allItems = agileItemDao.getItemDetails();
					allPendingRequests = agileReqDao.getRequestDetails();

					allUsers = agileUserDao.getUserDetails();
					System.out.println(allPendingRequests);
					request.setAttribute("itemsList", allItems);
					request.setAttribute("reqList", allPendingRequests);
					request.setAttribute("usersList", allUsers);

					// checks for admin privilege.
					if (agileLoginDao.authenticateAdmin(email, password)) {
						System.out.println("Value of flag becomes true !!!");
						// response.sendRedirect("adminpanel.jsp");
						dispatcher = request.getRequestDispatcher("adminpanel.jsp");
						dispatcher.forward(request, response);
					} else {
						System.out.println("Value of flag becomes false !!!");
						// response.sendRedirect("UsersView.jsp");
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

				String email = (String) httpSession.getAttribute("email");
				String password = (String) httpSession.getAttribute("password");

				if (agileLoginDao.authenticateAdmin(email, password)) {
					System.out.println("passing parameters again");
					List<AgileItems> allItems = new ArrayList<>();
					List<RequestDetails> allPendingRequests = new ArrayList<>();
					List<AgileUser> allUsers = new ArrayList<>();

					allItems = agileItemDao.getItemDetails();
					allPendingRequests = agileReqDao.getRequestDetails();
					allUsers = agileUserDao.getUserDetails();

					request.setAttribute("itemsList", allItems);
					request.setAttribute("reqList", allPendingRequests);
					request.setAttribute("usersList", allUsers);

					dispatcher = request.getRequestDispatcher("adminpanel.jsp");
					dispatcher.forward(request, response);

				} else {
					List<AgileItems> allItems = new ArrayList<>();
					List<RequestDetails> allPendingRequests = new ArrayList<>();
					List<AgileUser> allUsers = new ArrayList<>();

					allItems = agileItemDao.getItemDetails();
					allPendingRequests = agileReqDao.getRequestDetails();
					allUsers = agileUserDao.getUserDetails();

					request.setAttribute("itemsList", allItems);
					request.setAttribute("reqList", allPendingRequests);
					request.setAttribute("usersList", allUsers);
					dispatcher = request.getRequestDispatcher("UsersView.jsp");
					dispatcher.forward(request, response);
				}

			}

		} catch (Exception e) {
			Logger.getGlobal().log(Level.SEVERE, "Unexpected Login Error", e.getClass().getName());
		}
	}

}