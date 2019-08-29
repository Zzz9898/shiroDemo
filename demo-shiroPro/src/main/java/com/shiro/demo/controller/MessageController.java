package com.shiro.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @RequestMapping("/notLogin")
    public String notLogin(){
        return "you must to login!";
    }

    @RequestMapping("/notRole")
    public String notRole(){
        return "you don't have permission";
    }
}
