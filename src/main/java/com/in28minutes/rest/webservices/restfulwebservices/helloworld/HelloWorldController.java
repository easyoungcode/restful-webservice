package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// REST API
@RestController
public class HelloWorldController {

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    // Path Param
    // /users/{id}/todos/{id}   ex: /users/2/todos/200
    // hello-world/path-variable/{name}
    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(
            String.format("Hello World, %s", name));
    }
}
