package com.in28minutes.rest.webservices.restfulwebservices.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

public class User {


    private Long id;
    private String name;
    private LocalDate birthday;


    public User(Long id, String name, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", birthday=" + birthday +
            '}';
    }
}
