package com.agile.bl.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agile.bl.dao.AgileRequestDao;
import com.agile.bl.dao.AgileRequestDaoImplementation;
import com.agile.bl.utility.AgileRequestStatus;
import com.agile.bl.utility.MailFunctionality;

/**
 * Servlet implementation class AgileModerateRequests
 * 
 * @author Vishal Arora
 * 
 *         approves pending requests ...
 * 
 */
@WebServlet("/approvereq")
public class AgileApprovalRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AgileRequestDao agileReqImpl = new AgileRequestDaoImplementation();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String approvalSubject = "Approval";
		
		try {

			String description = "";
			String a = request.getParameter("itemid");
			String b = request.getParameter("requestid");
			
			int i = Integer.parseInt(a);
			int j = Integer.parseInt(b);
			
			String toEmail = request.getParameter("emailid");
			int itemId = i;
			int requestId = j;
			String itemName = request.getParameter("itemName");

			description = "Your request has been accepted for Item : " + itemName;
			agileReqImpl.approveRequest(itemId);
			agileReqImpl.updateRequestStatus(requestId, AgileRequestStatus.APPROVED);
			MailFunctionality.sendEmail(toEmail, approvalSubject, description);

			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>Approved</h1>");
			out.println("</body>");
			out.println("</html>");
			// response.sendRedirect("agilelogin");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}