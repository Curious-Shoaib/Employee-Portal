package com.myApp.utility;
import java.io.*;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.myApp.exception.ProjectException;

@Component
@Aspect
public class LoggingAspect {

	private static final Log LOGGER=LogFactory.getLog(LoggingAspect.class);
	
	@Autowired
	Environment env;  // this instance reads property from application.properties file
	
	//this pointcut expression will match all methods of (All classes of service package ending with impl)
	@AfterThrowing(pointcut="execution(* com.myApp.service.*Impl.*(..))", throwing="exception")
	public void logServiceException(Exception exception)
	{
		if(exception instanceof Exception )
		LOGGER.error(exception.getMessage(),exception);
		else
			LOGGER.error(env.getProperty(exception.getMessage()));
	}
}
