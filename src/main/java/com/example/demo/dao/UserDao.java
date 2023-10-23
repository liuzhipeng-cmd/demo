package com.example.demo.dao;


import com.example.demo.pojo.UserPojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    /**
     * 通过账号查询数据
     *
     * @param userName 账号
     * @return
     */
    UserPojo getUser(String userName);
}
