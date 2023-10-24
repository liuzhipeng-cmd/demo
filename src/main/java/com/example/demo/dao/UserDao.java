package com.example.demo.dao;


import com.example.demo.pojo.UserPojo;
import com.example.demo.pojo.UserRolePojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 通过账号查询数据
     *
     * @param userName 账号
     * @return
     */
    UserPojo getUser(String userName);

    /**
     * 查询所有数据
     *
     * @param pojo
     * @return
     */
    List<UserPojo> listUserInfoPage(UserPojo pojo);

    /**
     * 保存数据
     *
     * @param pojo
     * @return
     */
    Integer saveDataUser(UserPojo pojo);

    /**
     * 更新账号状态
     *
     * @param id
     * @param type
     * @return
     */
    Integer updateUserStatus(@Param("id") String id, @Param("type") String type);

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
    Integer updateDataUser(UserPojo pojo);

    /**
     * 将数据保存到用户与角色关联表
     *
     * @param userRole
     * @return
     */
    Integer saveUserRole(UserRolePojo userRole);

    /**
     * 将用户与角色关联表的数据更新通过用户id
     *
     * @param userRole
     * @return
     */
    Integer updateUserRole(UserRolePojo userRole);

    /**
     * 通过用户id删除用户与角色关联表信息
     *
     * @param id
     * @return
     */
    Integer deleteUserRole(String id);
}
