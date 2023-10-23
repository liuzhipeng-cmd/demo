package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.pojo.UserPojo;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    /**
     * 通过账号查询用户信息
     *
     * @param userName 账号
     * @return
     */
    @Override
    public UserPojo getUser(String userName) {

        UserPojo user = userDao.getUser(userName);

        return user;
    }
}
