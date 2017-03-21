package com.agile.bl.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.agile.bl.dao.AgileRequestDao;
import com.agile.bl.dao.AgileRequestDaoImplementation;
import com.agile.bl.utility.MailFunctionality;
import com.agile.bl.utility.AgileRequestStatus;

/**
 * 
 * @author Vishal Arora
 * 
 * declines pending requests
 *
 */
@WebServlet("/declinereq")
public class AgileDeclineRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AgileRequestDao agileReqImpl = new AgileRequestDaoImplementation();
	private static Logger log = Logger.getLogger(AgileDeclineRequest.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String rejectionSubject = "Denial";
		try {

			String description = "";
			String b = request.getParameter("requestid");

			int j = Integer.parseInt(b);
			
			String toEmail = request.getParameter("emailid");
			int requestId = j;
			String itemName = request.getParameter("itemName");

			description = "Your request has been accepted for Item : " + itemName;
			agileReqImpl.updateRequestStatus(requestId, AgileRequestStatus.DECLINED);
			MailFunctionality.sendEmail(toEmail, rejectionSubject, description);

			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>Approved</h1>");
			out.println("</body>");
			out.println("</html>");
			// response.sendRedirect("agilelogin");
			
		} catch (Exception e) {
			log.error("unable to decline request or mail is not sent as expected", e.getCause());
		}
	}

}
