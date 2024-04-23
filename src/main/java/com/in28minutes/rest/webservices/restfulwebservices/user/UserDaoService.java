package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.function.Predicate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

@Component
public class UserDaoService {
    // JPA/Hibernate > Database
    // UserDaoService > Static List

    private static List<User> users = new ArrayList<>();

    private static Long usersCount = 3l;

    static {
        users.add(new User(1l,"Adam",LocalDate.now().minusYears(30)));
        users.add(new User(2l,"Eve",LocalDate.now().minusYears(25)));
        users.add(new User(3l,"Jim",LocalDate.now().minusYears(20)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(Long id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().get();
    }

    //public User save(User user)
    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

}
