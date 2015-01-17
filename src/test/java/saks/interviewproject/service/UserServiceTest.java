package saks.interviewproject.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	 UserService userService = new UserService();
	 
	 @Mock
	 UriInfo mockUriInfo; 
	 @Mock
	 MultivaluedMap<String, String> mvmap ; 
	 
	 @Test
	 public void shouldReturnEmptyUserNameList() {
		 
		 WsResponse<Void> clearResult = userService.clearUser();
		 assertTrue(clearResult.isSuccess());
		 
		 WsResponse<List<String>> userNameListResponse = userService.showUser();
		  
		 assertNotNull(userNameListResponse.getData());
		 assertTrue(userNameListResponse.getData().size() == 0);
	 }
	 	 
	 @Test
	 public void shouldReturnPopulatedUserNameList() {
		 
		 WsResponse<Void> clearResult = userService.clearUser();
		 assertTrue(clearResult.isSuccess());

		 HashMap<String, List<String>> params = new HashMap<String, List<String>>();		 
		 ArrayList<String>paramElems = new ArrayList<String>();		 
		 params.put("foo",paramElems);
		 
		 when(mvmap.entrySet()).thenReturn(params.entrySet());
  		 when(mockUriInfo.getQueryParameters()).thenReturn(mvmap);
		 		 
		 WsResponse<Void> result = userService.addUser(mockUriInfo);		 
		 assertTrue(result.isSuccess());		 
		 
		 WsResponse<List<String>> userNameListResponse = userService.showUser();
		 assertTrue(userNameListResponse.isSuccess());
		 assertNotNull(userNameListResponse.getData());
		 System.out.println("Size is" + userNameListResponse.getData().size());
		 assertTrue(userNameListResponse.getData().size() == 1);
	 } 
	 
}
