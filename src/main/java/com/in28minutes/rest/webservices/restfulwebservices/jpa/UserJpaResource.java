package com.in28minutes.rest.webservices.restfulwebservices.jpa;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.in28minutes.rest.webservices.restfulwebservices.user.Post;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserDaoService;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJpaResource {

    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    /** GET /users  */
    @GetMapping("/jpa/users")
    public List<User> retrieveUsers() {
        return userRepository.findAll();
    }
 
    /** GET /users/{id}, 특정 user 정보 조회  */
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUsers(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()) throw new UserNotFoundException("id: "+id);

        EntityModel<User> userEntityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveUsers());
        userEntityModel.add(link.withRel("all-users"));

        return userEntityModel;
    }

    /** DELETE /users/{id}, 특정 user 정보 삭제  */
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()) throw new UserNotFoundException("id: "+id);
        
        // 특정 user의 post를 반환
        return user.get().getPosts();
    }

    /** DELETE /users/{id}, 특정 user 정보 삭제  */
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    /** POST /users
     * Valid로 유효성 검증
     * */
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User saveUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(saveUser.getId())
                        .toUri();
        // /users/생성된userId
        return ResponseEntity.created(location).build();
    }

    /** POST /users
     * Valid로 유효성 검증
     * */
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Post> createPostsForUser(@PathVariable long id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()) throw new UserNotFoundException("id: "+id);
        post.setUser(user.get());
        Post savePost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savePost.getId())
            .toUri();

        return ResponseEntity.created(location).build();
    }
}
