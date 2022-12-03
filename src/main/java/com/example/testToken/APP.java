package com.example.testToken;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.example.testToken.dao")
public class APP extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(APP.class, args);
    }

}
