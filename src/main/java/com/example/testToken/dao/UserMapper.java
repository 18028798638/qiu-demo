package com.example.testToken.dao;

import com.example.testToken.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> queryAll();

    int saveUser(User user);

    int deleteUser(int id);

    User getByName(String name);

    User getById(int id);

    int updateUser(User user);
}
