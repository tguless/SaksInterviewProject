package saks.interviewproject.dao;

public class DaoFactory {
	
	public static UserDao getDao() {
		return new UserDaoImpl();
	}
		
}
