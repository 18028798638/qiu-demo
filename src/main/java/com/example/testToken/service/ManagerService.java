package com.example.testToken.service;

import com.example.testToken.domain.Manager;

public interface ManagerService {
    Manager findOne(String username);

    boolean register(String username,String password);
}
