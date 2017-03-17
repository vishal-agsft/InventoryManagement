package com.agile.bl.utility;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class AgileSessionHandler implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("Session Created ! ");
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("Session Destroyed ! ");
		
	}

}
