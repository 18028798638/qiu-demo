package com.example.testToken.service.Impl;

import com.example.testToken.dao.ManagerMapper;
import com.example.testToken.domain.Manager;
import com.example.testToken.service.ManagerService;
import com.example.testToken.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerMapper managerMapper;

    @Override
    public Manager findOne(String username) {
        return managerMapper.findOne(username);
    }

    @Override
    public boolean register(String username, String password) {
        Manager temp = managerMapper.findOne(username);
        String MD5pwd = MD5Util.encode32(password);
        if(null==temp){
            managerMapper.register(username, MD5pwd);
            return true;
        } else {
            return false;
        }
    }


}
