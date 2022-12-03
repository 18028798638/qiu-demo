package com.example.testToken.intercepetor;


import com.alibaba.fastjson2.JSONObject;
import com.example.testToken.domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

//自定义拦截器类

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle (HttpServletRequest request ,
                              HttpServletResponse response , Object o )throws Exception {

        String token = getToken(request);
        System.out.println(token);

        if (null==token){
            System.out.println("请先登录");
            response.sendRedirect("/login.jsp");
            return false;
        }

        String managerJson = redisTemplate.boundValueOps(token).get();

        if (null==managerJson){
            System.out.println("登录身份已过期");
            response.sendRedirect("/login.jsp");
            return false;
        }
        //刷新过期时间
        redisTemplate.expire(token, 30, TimeUnit.SECONDS);
        return true;
    }

    public String getToken(HttpServletRequest request)  {
        Cookie[] cookies = request.getCookies() ;
        for(Cookie c : cookies){
            if(c.getName().equals("token")){
                return c.getValue() ;
            }
        }
        return null ;
    }
}


