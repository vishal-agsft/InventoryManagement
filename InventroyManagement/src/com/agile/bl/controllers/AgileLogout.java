package com.agile.bl.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class AgileLogout
 * 
 * @author Vishal Arora This servlet performs the taks of escorting out current
 *         user.
 * 
 * 
 */
@WebServlet("/agilelogout")
public class AgileLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(AgileAddNewItem.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession(false);

		try {

			httpSession.removeAttribute("email");
			httpSession.invalidate();

			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);

			response.sendRedirect("login.jsp");

		} catch (Exception e) {
			log.error("Unexpected logout error", e.getCause());
		}
	}

}
