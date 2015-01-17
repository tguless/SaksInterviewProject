package saks.interviewproject.dao;

import java.util.List;

public interface UserDao {
    List<String> show();
    boolean add(String username);
    public boolean clear();
}
