package com.learnsimon.rest.webservices.restful_web_service.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class UserDoaService {
    //JPA Database
    //static list
    private static List<User> users = new ArrayList<>();

    private static int userCount = 0;

    static {
        users.add(new User(++userCount," simon", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount," Bishwas", LocalDate.now().minusYears(20)));
        users.add(new User(++userCount," Bhagwan", LocalDate.now().minusYears(40)));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public User findUser(Integer id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(Integer id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

}
