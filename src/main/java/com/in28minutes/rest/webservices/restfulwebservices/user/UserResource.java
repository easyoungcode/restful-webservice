package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {

    private UserDaoService userDaoService;

    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    // GET /users
    @GetMapping("/users")
    public List<User> retrieveUsers() {
        return userDaoService.findAll();
    }
 
    // GET /users/{id}, 특정 user 정보 조회
    @GetMapping("/users/{id}")
    public User retrieveUsers(@PathVariable Long id) {
        return userDaoService.findOne(id);
    }
}
