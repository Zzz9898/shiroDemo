package com.shiro.demo.service.impl;

import com.shiro.demo.entity.User;
import com.shiro.demo.repository.UserDao;
import com.shiro.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User get(long id) {
        return userDao.getOne(id);
    }

    @Override
    public User findByUserName(String username) {
        return userDao.findByUsername(username);
    }
}
