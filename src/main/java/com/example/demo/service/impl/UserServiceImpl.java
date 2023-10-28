package com.example.demo.service.impl;

import com.example.common.utils.ConstantUtils;
import com.example.common.utils.DateTimeUtils;
import com.example.common.utils.DesUtils;
import com.example.common.utils.GuidUtils;
import com.example.demo.dao.UserDao;
import com.example.demo.pojo.UserPojo;
import com.example.demo.pojo.UserRolePojo;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

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

    /**
     * 分页查询
     *
     * @param pojo
     * @return
     */
    @Override
    public PageInfo<UserPojo> listUserInfoPage(UserPojo pojo) {

        PageHelper.startPage(pojo.getPage(), pojo.getLimit());

        List<UserPojo> list = userDao.listUserInfoPage(pojo);

        PageInfo<UserPojo> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    /**
     * 保存数据
     *
     * @param pojo
     * @return
     */
    @Override
    @Transactional
    public int saveDataUser(HttpServletRequest request, UserPojo pojo) {
        UserPojo userPojo = (UserPojo) request.getSession().getAttribute(ConstantUtils.USER_INFO);
        // 创建主键
        pojo.setId(new GuidUtils().GuidUtils());
        // 获取当前时间（日期+时分秒）
        pojo.setCreateTime(new DateTimeUtils().getYearMonthDayHourMinuteSecond());
        // 创建人
        pojo.setCreator(userPojo.getId());
        // 获取默认密码
        String userPassword = ConstantUtils.DEFAULT_PASSWORD;
        // 对密码进行加密
        String encryptPassword = new DesUtils().encrypt(userPassword);
        // 加密后重新赋值
        pojo.setUserPassword(encryptPassword);

        int num = userDao.saveDataUser(pojo);

        if (num > 0) {
            // 将角色id与用户id保存到用户与角色关联表
            num = saveUserRole(pojo);
        }

        return num;
    }

    /**
     * 更新账号状态
     *
     * @param id
     * @param type
     * @return
     */
    @Override
    public int updateUserStatus(String id, String type) {

        int num = userDao.updateUserStatus(id, type);

        return num;
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int deleteUser(String id) {

        int num = userDao.deleteUser(id);

        if (num > 0) {
            deleteUserRole(id);
        }

        return num;
    }

    /**
     * 更新数据
     *
     * @param pojo
     * @return
     */
    @Override
    @Transactional
    public int updateDataUser(HttpServletRequest request, UserPojo pojo) {
        UserPojo userPojo = (UserPojo) request.getSession().getAttribute(ConstantUtils.USER_INFO);
        // 获取当前时间（日期+时分秒）
        pojo.setUpdateTime(new DateTimeUtils().getYearMonthDayHourMinuteSecond());
        // 更新人
        pojo.setUpdator(userPojo.getId());
        // 更新数据
        Integer num = userDao.updateDataUser(pojo);
        if (num > 0) {
            num = updateDataUserRole(pojo);
        }
        return num;
    }

    /**
     * 修改密码
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public int updatePassword(String userName, String password) {
        // 对密码进行加密
        String encryptPassword = new DesUtils().encrypt(password);
        // 修改密码
        Integer num = userDao.updatePassword(userName,encryptPassword);

        return num;
    }

    /**
     * 将角色id与用户id保存到用户与角色关联表
     *
     * @param pojo
     * @return
     */
    private int saveUserRole(UserPojo pojo) {
        UserRolePojo userRole = new UserRolePojo();
        // 拼接参数
        userRole.setRoleId(pojo.getRoleId());// 角色id
        userRole.setUserId(pojo.getId());// 用户id
        // 获取当前时间（日期+时分秒）
        userRole.setCreateTime(new DateTimeUtils().getYearMonthDayHourMinuteSecond());
        // 创建人
        userRole.setCreator(pojo.getCreator());
        int num = userDao.saveUserRole(userRole);
        return num;
    }

    /**
     * 将用户与角色关联表的数据更新通过用户id
     *
     * @param pojo
     * @return
     */
    private int updateDataUserRole(UserPojo pojo) {
        UserRolePojo userRole = new UserRolePojo();
        // 通过用户id查询用户与角色关联表数据
        UserRolePojo userRolePojo = userDao.getUserRoleByUserId(pojo.getId());
        // 拼接参数
        userRole.setRoleId(pojo.getRoleId());// 角色id
        userRole.setUserId(pojo.getId());// 用户id
        // 判断如果通过id查询到数据则进行更新，否则进行添加
        if (userRolePojo != null) {
            // 获取当前时间（日期+时分秒）
            userRole.setUpdateTime(new DateTimeUtils().getYearMonthDayHourMinuteSecond());
            // 更新人
            userRole.setUpdator(pojo.getUpdator());
            int num = userDao.updateUserRole(userRole);
            return num;
        } else {
            // 获取当前时间（日期+时分秒）
            userRole.setCreateTime(new DateTimeUtils().getYearMonthDayHourMinuteSecond());
            // 创建人
            userRole.setCreator(pojo.getUpdator());
            int num = userDao.saveUserRole(userRole);
            return num;
        }
    }

    /**
     * 通过用户id删除用户与角色关联表信息
     *
     * @return
     */
    private int deleteUserRole(String id) {

        int num = userDao.deleteUserRole(id);

        return num;
    }
}
