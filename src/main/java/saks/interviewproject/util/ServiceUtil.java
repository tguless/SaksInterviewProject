package saks.interviewproject.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;

import saks.interviewproject.service.WsResponse;

public class ServiceUtil {
    public static  <T> WsResponse<T> handleWsException(Exception e, Logger logger) {
		logger.error("Something bad happened" , e);
		if  (System.getProperty("prodmode", "false").equals("false")) { 
			return new WsResponse<T>(false,"Exception Thrown: " + getStackTrace(e));    			
		} else {
			//Never show stack trace to the user on production servers
			return new WsResponse<T>(false,"Server Generated Error");
		}
    }
    
    public static String getStackTrace(Exception e ) {
    	StringWriter sw = new StringWriter();
    	PrintWriter pw = new PrintWriter(sw);
    	e.printStackTrace(pw);
    	return sw.toString();
    }
}
