package saks.interviewproject.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import saks.interviewproject.dao.DaoFactory;
import saks.interviewproject.dao.UserDao;
import saks.interviewproject.util.ServiceUtil;

@Path("/")
public class UserService {
	
	final Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET    
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add")    
    public WsResponse<Void> addUser(@Context UriInfo ui) {
    	
    	 try {
	    	 MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
	  
	    	if (!queryParams.entrySet().isEmpty()) {
	    		 String username = queryParams.entrySet().iterator().next().getKey();
	    	     	 
	    		 UserDao dao = DaoFactory.getDao();
	    	 
	    		 dao.add(username);
	    	 
	    		 return new WsResponse<Void>(true,"success");
	    	    		
    	 	 } else {
	    		 return new WsResponse<Void>(false,"No User Id Passed");
	    	 }
			
    	 } catch (Exception e) {
    		 return ServiceUtil.<Void>handleWsException(e, logger);
    	 }
    	 
    }
    
    
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/show")    
    public  WsResponse<List<String>> showUser() {
        try {
        	return new WsResponse<List<String>>(true, "success", DaoFactory.getDao().show());
	   	 } catch (Exception e) {
			 return ServiceUtil.<List<String>>handleWsException(e, logger);
		 }
    }
    
    @GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/clear")    
    public  WsResponse<Void> clearUser() {
        try {
        	 if (DaoFactory.getDao().clear()) {
        		 return new WsResponse<Void>(true, "success");
        	 } else {
        		 return new WsResponse<Void>(false, "Unable to clear users");
        	 }
	   	 } catch (Exception e) {
			 return ServiceUtil.<Void>handleWsException(e, logger);
		 }
    }
    
}
