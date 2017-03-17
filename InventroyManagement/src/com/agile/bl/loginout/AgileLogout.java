package com.agile.bl.loginout;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession(false);
		try {
//			
//			if (httpSession != null || httpSession.getAttribute("email") != null
//					|| !httpSession.getAttribute("email").toString().isEmpty()) {
				httpSession.removeAttribute("email");
				httpSession.invalidate();
//			}

			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);

//			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//			dispatcher.forward(request, response);

			// response.sendRedirect("agilelogin");
			response.sendRedirect("login.jsp");

		} catch (Exception e) {
			Logger.getGlobal().log(Level.SEVERE, "Unexpected Logout Error", e.getClass().getName());
		}
	}

}
