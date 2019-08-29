package com.shiro.demo.service;

import com.shiro.demo.entity.User;

public interface UserService {
    User get(long id);

    User findByUserName(String username);
}
