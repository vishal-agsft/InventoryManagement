package com.agile.bl.controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AgileMakeNewRequest
 */
@WebServlet("/newrequest")
@SuppressWarnings("unused")
public class AgileMakeNewRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			int userId = Integer.valueOf(request.getParameter(""));
			int itemId = Integer.valueOf(request.getParameter(""));
			int quantity = Integer.valueOf(request.getParameter(""));
			
			Timestamp requestedDate = new Timestamp(System.currentTimeMillis());
			int currentRequestedStatus = 0;
			
			String description = "";
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
