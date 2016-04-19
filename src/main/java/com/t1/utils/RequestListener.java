package com.t1.utils;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class RequestListener implements ServletRequestListener {

	private HttpServletRequest request;

	//@Override
	public void requestInitialized(ServletRequestEvent sre) {
		request = (HttpServletRequest) sre.getServletRequest();
		String path = request.getContextPath();
		String ctx = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		request.setAttribute("basePath", ctx);
	}

	//@Override
	public void requestDestroyed(ServletRequestEvent sre) {

	}


}