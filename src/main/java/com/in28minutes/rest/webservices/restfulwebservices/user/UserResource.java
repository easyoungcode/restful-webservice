package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

    private UserDaoService userDaoService;

    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    /** GET /users  */
    @GetMapping("/users")
    public List<User> retrieveUsers() {
        return userDaoService.findAll();
    }
 
    /** GET /users/{id}, 특정 user 정보 조회  */
    @GetMapping("/users/{id}")
    public User retrieveUsers(@PathVariable Long id) {
        User user = userDaoService.findOne(id);

        if(user == null) throw new UserNotFoundException("id: "+id);
        return user;
    }

    /** DELETE /users/{id}, 특정 user 정보 삭제  */
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userDaoService.deleteById(id);
    }

    /** POST /users */
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saveUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(saveUser.getId())
                        .toUri();
        // /users/생성된userId
        return ResponseEntity.created(location).build();
    }
}
