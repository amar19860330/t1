package com.t1.framework;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class CheckInterceptor implements HandlerInterceptor
{
	private final Logger log = Logger.getLogger( this.getClass() );

	@Override
	public void afterCompletion( HttpServletRequest arg0 , HttpServletResponse arg1 , Object arg2 , Exception arg3 ) throws Exception
	{

	}

	@Override
	public void postHandle( HttpServletRequest arg0 , HttpServletResponse arg1 , Object arg2 , ModelAndView arg3 ) throws Exception
	{

	}

//	public boolean validateRight( String url , User user )
//	{
//		boolean result = true;
//
//		String forbiddenUrl[] = { "diaryJob.amar", "jobPlan.amar" };
//		for( String fbUrl : forbiddenUrl )
//		{
//			if ( user == null && url.contains( fbUrl ) )
//			{
//				result = false;
//				break;
//			}
//		}
//
//		return result;
//	}

	public boolean validate( String data )
	{
		boolean result = true;

		String forbiddens[] = { "drop", "select" };
		if ( data != null && ! "".equals( data.trim() ) )
		{
			for( String forbidden : forbiddens )
			{
				if ( data.toLowerCase().contains( forbidden ) )
				{
					result = false;
					break;
				}
			}
		}

		return result;
	}

	@SuppressWarnings( "rawtypes" )
	@Override
	public boolean preHandle( HttpServletRequest arg0 , HttpServletResponse arg1 , Object arg2 ) throws Exception
	{
		boolean result = true;

		Enumeration names = arg0.getParameterNames();
		while ( names.hasMoreElements() )
		{
			String name = names.nextElement().toString();
			String value = arg0.getParameter( name );

			if ( ! validate( value ) )
			{
				log.info( "" + name + "=" + value );
				result = false;
				arg1.sendRedirect( "/error/warning" );
				break;
			}
		}

//		String url = arg0.getRequestURI();
//		User user = ( User ) arg0.getSession().getAttribute( "user" );
//		if ( ! validateRight( url , user ) )
//		{
//			arg1.sendRedirect( "login.amar?method=main&waring=noright" );
//			result = false;
//		}

		return result;
	}

}
