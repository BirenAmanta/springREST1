package com.mindtree.cabbooking.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class LoggingAspects {

	@Autowired
	Environment environment;
	
	final Log LOGGER=LogFactory.getLog(LoggingAspects.class);
	
	@AfterThrowing(pointcut = "execute(* com.mindtree.cabbooking.*Impl.*(..)",throwing = "exception")
	public void aspectLogging(Exception exception)
	{
		String message= environment.getProperty(exception.getMessage(),exception.getMessage());
		LOGGER.error(message);
	}
	

}
