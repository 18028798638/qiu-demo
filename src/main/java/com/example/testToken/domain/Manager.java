package com.example.testToken.domain;

import lombok.Data;

@Data
public class Manager {
    private int id;
    private String username;
    private String password;
//    private String token;

    public Manager() {

    }
}
