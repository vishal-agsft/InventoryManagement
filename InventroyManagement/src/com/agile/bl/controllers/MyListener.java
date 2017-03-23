package com.agile.bl.controllers;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MyListener implements ServletContextListener{  
	HttpServletRequest request;
	AgileLogin ag = new AgileLogin();
	public void contextInitialized(ServletContextEvent obj) {
		/* ServletContext context=obj.getServletContext();
		 String session =context.getInitParameter("HttpSession");*/
		
		System.out.println("project started");
		/*httpSession = request.getSession(false);*/

	}
	public void contextDestroyed(ServletContextEvent obj) {  
	/*	 
		HttpSession session =request.getSession();
		if(ag.getInitParameter("HttpSession")!=null)
		{
			
		session.invalidate();
		}
		  
		System.out.println("project ended");
}*/
	}  
}  