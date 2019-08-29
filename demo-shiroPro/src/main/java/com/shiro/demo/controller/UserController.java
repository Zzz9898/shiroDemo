package com.shiro.demo.controller;

import com.shiro.demo.entity.User;
import com.shiro.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("get/{id}")
    public User get(@PathVariable long id){
        User user = userService.get(id);
        return user;
    }

    @RequiresPermissions("user:add")
    @PostMapping("/post")
    public String post(@RequestParam("username") String username,
                     @RequestParam("password") String password){
        System.out.println(username+ " " +password);
        return null;
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
        }catch (IncorrectCredentialsException ice){
            return "密码不正确";
        }catch(UnknownAccountException uae){
            return "账号不存在";
        }catch(AuthenticationException ae){
            return "状态不正常";
        }
        if(subject.isAuthenticated()){
            System.out.println("认证成功");
            return "shiro: login success";
        }else{
            return "shiro: login fail";
        }
    }

    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "logout success";
    }
}
