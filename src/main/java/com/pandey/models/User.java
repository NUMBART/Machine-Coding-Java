package com.pandey.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    int id;
    String name;
    String email;
    String phone;

    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
