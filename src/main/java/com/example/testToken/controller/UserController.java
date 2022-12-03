package com.example.testToken.controller;

import com.example.testToken.domain.User;
import com.example.testToken.service.UserService;
import com.example.testToken.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("user")
    public Result insertUser(@RequestBody User user){
        Result result = new Result();
        int flag = userService.saveUser(user);
        try {
            if(flag==2){
                result.setCode(400);
                result.setMsg("该用户已存在");
            }else{
                result.setData(user);
            }
        } catch (Exception e) {
            result.setCode(1003);
            result.setMsg("操作失败");
        }
        return result;
    }

    @DeleteMapping("user/{id}")
    public Result deleteUser(@PathVariable int id){
        Result result = new Result();
        int flag = userService.deleteUser(id);
        try {
            if(flag==2){
                result.setCode(400);
                result.setMsg("该用户不存在");
            }else{
                result.setMsg("删除成功");
            }
        } catch (Exception e) {
            result.setCode(1003);
            result.setMsg("操作失败");
        }
        return result;
    }

    @PutMapping("user")
    public Result updateUser(@RequestBody User user){
        Result result = new Result();
        int flag = userService.updateUser(user);
        try {
            if(flag==2){
                result.setCode(400);
                result.setMsg("该用户不存在");
            }else{
                result.setMsg("更新成功");
                result.setData(user);
            }
        } catch (Exception e) {
            result.setCode(1003);
            result.setMsg("操作失败");
        }
        return result;
    }

    @GetMapping("/user/{id}")
    public Result findById(@PathVariable int id){
        Result result = new Result();
        try {
            User user = userService.getById(id);
            if(null!=user)
                result.setData(user);
            else {
                result.setMsg("查无此人");
            }

        }catch (Exception e){
            result.setCode(400);
            result.setMsg("操作失败");
        }
        return result;
    }

    @GetMapping("user")
    public Result queryAll(){
        try {
            return new Result(userService.queryAll());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("getByName")
    public Result getByName(String name){
        Result result = new Result();
        try {
            User user = userService.getByName(name);
            result.setData(user);
        }catch (Exception e){
            result.setCode(300);
            result.setMsg("操作失败");
        }
        return result;
    }

    //
//    @GetMapping("exit")
//    public String exit(HttpServletRequest request, HttpServletResponse response){
//        Cookie[] cookies = request.getCookies();
//        for(Cookie c : cookies){
//            c.setMaxAge(0);
//            response.addCookie(c);
//        }
//        return "exit";
//    }

}
