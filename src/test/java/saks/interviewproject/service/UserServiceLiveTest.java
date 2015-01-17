package saks.interviewproject.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import org.junit.Test;

import saks.interviewproject.util.TestPropUtil;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class UserServiceLiveTest {

	 ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
	
	 
	 
	 String testSrvHost = TestPropUtil.getProperty("TestSrvHost");
	 String testSrvPort = TestPropUtil.getProperty("TestSrvPort");
	 String testSrvProtocol = TestPropUtil.getProperty("TestSrvProtocol");
	 
	 String testSrvUrl = testSrvProtocol + "://"+testSrvHost+":"+testSrvPort;
	 
	 private static boolean serverUp = true;
	 private static boolean serverChecked = false;
	 
	 public boolean isServerUp() {
		 	if (serverChecked == false) {		
		 		serverChecked = true;
			    try (Socket s = new Socket(testSrvHost, Integer.parseInt(testSrvPort))) {
			    	serverUp = true;			    	
			    	return true;
			    } catch (IOException ex) {
			        /* ignore */
			    }
			    serverUp=false;			   
		 	}
		 	
		 	return serverUp;
	 }

	 public void clearUsers() throws JsonParseException, JsonMappingException, IOException {
		 	if (isServerUp()) {
			 	Client client = Client.create();
			 	
				WebResource webResource = client.resource(testSrvUrl+"/sample/user/clear");
				
				ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
				
				if (response.getStatus() != 200) {
					   throw new RuntimeException("Failed : HTTP error code : "	+ response.getStatus());
				}
			 
				String output = response.getEntity(String.class);
				
				WsResponse<Void> userClearResponse = mapper.readValue(output,new TypeReference<WsResponse<Void>>() {});
				
				assertTrue(userClearResponse.isSuccess());
		 	}			
	 }
	 
	 @Test
	 public void shouldReturnEmptyUserNameList() throws JsonParseException, JsonMappingException, IOException {
		 	if (isServerUp()) {
			 	clearUsers();
			 	
			 	Client client = Client.create();
			 
				WebResource webResource = client.resource(testSrvUrl+"/sample/user/show");
		 
				ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		 
				if (response.getStatus() != 200) {
				   throw new RuntimeException("Failed : HTTP error code : "	+ response.getStatus());
				}
		 
				String output = response.getEntity(String.class);
				
				WsResponse<List<String>> userNameListResponse = mapper.readValue(output,new TypeReference<WsResponse<List<String>>>() {});
				
				assertNotNull(userNameListResponse.getData());
				assertTrue(userNameListResponse.getData().size() == 0);
		 	}
	 }
	 	 
	 @Test
	 public void shouldReturnPopulatedUserNameList() throws JsonParseException, JsonMappingException, IOException {
		 	if (isServerUp()) {
			 	clearUsers();
			 
			 	Client client = Client.create();
				 
				WebResource webResource = client.resource(testSrvUrl+"/sample/user/add?Ted");
		 
				ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		 
				if (response.getStatus() != 200) {
				   throw new RuntimeException("Failed : HTTP error code : "	+ response.getStatus());
				}
				
				String output = response.getEntity(String.class);
				
				WsResponse<Void> userAddResponse = mapper.readValue(output,new TypeReference<WsResponse<Void>>() {});
				assertTrue(userAddResponse.isSuccess());
				
				webResource = client.resource(testSrvUrl+"/sample/user/show");
				 
				response = webResource.accept("application/json").get(ClientResponse.class);
	
				if (response.getStatus() != 200) {
					   throw new RuntimeException("Failed : HTTP error code : "	+ response.getStatus());
				}
				
				output = response.getEntity(String.class);
				
				WsResponse<List<String>> userNameListResponse = mapper.readValue(output,new TypeReference<WsResponse<List<String>>>() {});
				
				assertTrue(userNameListResponse.isSuccess());
				assertNotNull(userNameListResponse.getData());
				assertTrue(userNameListResponse.getData().size() == 1);
		 	}
		 

	 } 
	
}
