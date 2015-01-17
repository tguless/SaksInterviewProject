package saks.interviewproject.dao;

import java.util.ArrayList;
import java.util.List;

public class UserCache {
	private static UserCache userCache;
	public static List<String> userNameList = new ArrayList<String>();
	 
	private UserCache() {
	}
	 
	public static UserCache getInstance() {
		if (userCache == null)
		userCache = new UserCache();
		 
		return userCache;
	} 	
	
	
}
