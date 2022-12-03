package com.example.testToken.service.Impl;


import com.example.testToken.dao.UserMapper;
import com.example.testToken.domain.User;
import com.example.testToken.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int saveUser(User user) {
        User user1 = userMapper.getByName(user.getName());
        if(user1!=null){
            return 2;
        }else {
            return userMapper.saveUser(user);
        }
    }

    @Override
    public int deleteUser(int id) {
        User temp = userMapper.getById(id);
        if(null==temp){
            return 2;
        }else {
            return userMapper.deleteUser(id);
        }
    }

    @Override
    public int updateUser(User user) {
        User temp = userMapper.getById(user.getId());
        if(null==temp){
            return 2;
        }else {
            return userMapper.updateUser(user);
        }
    }

    @Override
    public List<User> queryAll() {
        List<User> list = userMapper.queryAll();
        return list;
    }

    @Override
    public User getByName(String name) {
        return userMapper.getByName(name);
    }

    @Override
    public User getById(int id) {
        return userMapper.getById(id);
    }
}

