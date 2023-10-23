package com.example.demo.service;

import com.example.demo.pojo.UserPojo;

public interface UserService {

    /**
     * 通过账号查询用户信息
     *
     * @param userName 账号
     * @return
     */
    UserPojo getUser(String userName);
}
