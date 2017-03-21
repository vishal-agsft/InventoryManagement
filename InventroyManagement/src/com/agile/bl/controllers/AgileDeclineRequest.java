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
import com.agile.bl.utility.AgileMailTemplate;
import com.agile.bl.utility.AgileRequestStatus;

@WebServlet("/declinereq")
public class AgileDeclineRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AgileRequestDao agileReqImpl = new AgileRequestDaoImplementation();

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
			AgileMailTemplate.sendEmail(toEmail, rejectionSubject, description);

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
