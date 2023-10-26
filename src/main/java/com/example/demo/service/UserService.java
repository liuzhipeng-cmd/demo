package com.example.demo.service;

import com.example.demo.pojo.UserPojo;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    /**
     * 通过账号查询用户信息
     *
     * @param userName 账号
     * @return
     */
    UserPojo getUser(String userName);

    /**
     * 分页查询
     *
     * @param pojo
     * @return
     */
    PageInfo<UserPojo> listUserInfoPage(UserPojo pojo);

    /**
     * 保存数据
     *
     * @param pojo
     * @return
     */
    int saveDataUser(HttpServletRequest request, UserPojo pojo);

    /**
     * 更新账号状态
     *
     * @param id
     * @param type
     * @return
     */
    int updateUserStatus(String id, String type);

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    int deleteUser(String id);

    /**
     * 更新数据
     *
     * @param pojo
     * @return
     */
    int updateDataUser(HttpServletRequest request, UserPojo pojo);

    /**
     * 修改密码
     *
     * @param userName
     * @param password
     * @return
     */
    int updatePassword(String userName, String password);
}
