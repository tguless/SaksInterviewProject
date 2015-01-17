package saks.interviewproject.dao;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public List<String> show() {
        return UserCache.userNameList;
    }

    @Override
    public boolean add(String username) {
        UserCache.userNameList.add(username);

        return true;
    }
    
    @Override
    public boolean clear() {
        UserCache.userNameList = new ArrayList<String>();
        return true;
    }

}
