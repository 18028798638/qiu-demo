package com.example.testToken.dao;

import com.example.testToken.domain.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ManagerMapper {
    Manager findOne(String username);

    void register(@Param("username") String username,@Param("password") String password);
}
