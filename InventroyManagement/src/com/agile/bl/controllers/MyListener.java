package com.agile.bl.controllers;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.agile.bl.loginout.AgileLogin;

public class MyListener implements ServletContextListener{  
	HttpServletRequest request;
	AgileLogin ag = new AgileLogin();
	public void contextInitialized(ServletContextEvent obj) {
		/* ServletContext context=obj.getServletContext();
		 String session =context.getInitParameter("HttpSession");*/
		
		System.out.println("project started");
		/*httpSession = request.getSession(false);*/

	}
	public void contextDestroyed(ServletContextEvent arg0) {  
		/*if(ag.httpSession!=null)
		{
			ag.httpSession.removeAttribute("t");
			ag.httpSession.removeAttribute("email");
			ag.httpSession.removeAttribute("password");
			ag.httpSession.removeAttribute("str");
			ag.httpSession.invalidate();
		}*/
		  
		System.out.println("project ended");

	}  
}  