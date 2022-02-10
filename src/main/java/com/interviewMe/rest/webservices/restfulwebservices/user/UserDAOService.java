package com.interviewMe.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDAOService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Divya", new Date()));
        users.add(new User(3, "Devesh", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {

        if (user.getName().isEmpty()) {
            return null;
        }

        if (user.getId() == null) {
            user.setId(usersCount + 1);
        }
        users.add(user);
        return user;
    }

    public User findUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id) {

        Iterator<User> userList=users.iterator();

        while (userList.hasNext()){
            User user=userList.next();
            if(user.getId()==id){
                userList.remove();
                return user;
            }
        }
        return null;
    }
}
