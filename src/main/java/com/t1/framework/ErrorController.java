package com.t1.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/error")
public class ErrorController extends BaseController
{
	@RequestMapping(value = "/warning")
	public String toWarning( HttpServletRequest request , HttpServletResponse response )
	{
		return "error/warning";
	}

	@RequestMapping(value = "/404")
	public String toWarning404( HttpServletRequest request , HttpServletResponse response )
	{
		return "error/warning404";
	}

	@RequestMapping(value = "/500")
	public String toWarning500( HttpServletRequest request , HttpServletResponse response )
	{
		return "error/warning500";
	}
}
