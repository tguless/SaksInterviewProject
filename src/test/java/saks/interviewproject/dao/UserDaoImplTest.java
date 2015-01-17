package saks.interviewproject.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class UserDaoImplTest {
	
	 UserDaoImpl userDao = new UserDaoImpl();
	 
	 @Test
	 public void shouldReturnEmptyUserNameList() {
		 List<String> userNameList = userDao.show();
		  
		 assertNotNull(userNameList);
		 assertTrue(userNameList.size() == 0);
	 }
	  
	 @Test
	 public void shouldReturnPopulatedUserNameList() {
		 boolean result = userDao.add("test");
		 assertTrue(result);
		  
		 List<String> userNameList = userDao.show();
		  
		 assertNotNull(userNameList);
		 assertTrue(userNameList.size() == 1);
	 } 

}
