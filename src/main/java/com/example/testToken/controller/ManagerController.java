package com.example.testToken.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.testToken.domain.Manager;
import com.example.testToken.service.ManagerService;
import com.example.testToken.util.AESUtil;
import com.example.testToken.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@RestController
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @Autowired
    StringRedisTemplate redisTemplate;

    private String aesPassword = "appotronics";

    @RequestMapping("loginView")
    public String loginView(){
        return "/behind/login";
    }

    @PostMapping("login")
    public Map<String,Object> login(Manager manager, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        Manager temp = managerService.findOne(manager.getUsername());
        String timeStamp = String.valueOf(System.currentTimeMillis());

        if (null!=temp){
            if(MD5Util.encode32(manager.getPassword()).equals(temp.getPassword())){
                manager.setPassword(null);
                String managerJson = JSONObject.toJSONString(manager);
                String token = AESUtil.encrypt(managerJson+timeStamp, aesPassword);
                redisTemplate.boundValueOps(token).set(managerJson);
                redisTemplate.expire(token, 10, TimeUnit.SECONDS);
                Cookie cookie = new Cookie("token",token);
                cookie.setPath("/");
                response.addCookie(cookie);
                System.out.println("登录成功");
                map.put("Code",200);
                map.put("Msg","success");
                return map;
            }else {
                System.out.println("登录失败");
                map.put("Code",300);
                map.put("Msg","error");
                return map;
            }
        }
        map.put("Msg","error");
        return map;
    }

    @PostMapping("register")
    public Map<String,Object> register(Manager manager){
        Map<String,Object> map = new HashMap<>();
        boolean success = managerService.register(manager.getUsername(), manager.getPassword());
        if(success){
            map.put("Code",200);
            map.put("Msg","success");
            return map;
        }else {
            map.put("Code",300);
            map.put("Msg","error");
            return map;
        }
    }

    @GetMapping("logout")
    public Map<String,Object> logout(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies){
            if (c.getName().equals("token")){
                redisTemplate.delete(c.getValue());
            }
        }
        System.out.println("退出成功");
        map.put("Code",200);
        map.put("Msg","logout");
        return map;
    }

//    @GetMapping("exit")
//    public String exit(HttpServletRequest request,HttpServletResponse response){
//        Cookie[] cookies = request.getCookies();
//        for(Cookie c : cookies){
//            c.setMaxAge(0);
//            response.addCookie(c);
//        }
//        return "exit";
//    }
}
