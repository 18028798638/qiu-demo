package com.example.testToken.service;

import com.example.testToken.domain.User;

import java.util.List;


public interface UserService {

    int saveUser(User user);

    int deleteUser(int id);

    int updateUser(User user);

    List<User> queryAll();

    User getByName(String name);

    User getById(int id);
}
