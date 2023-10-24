package com.example.demo.pojo;

import com.example.common.utils.Base;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserPojo extends Base {

    /**
     * 账号
     */
    private String userName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 生日
     */
    private String userBirthday;

    /**
     * 电话
     */
    private String userPhone;

    /**
     * 是否锁定：0：未锁定 1：锁定
     */
    private Integer isLock;

    /**
     * 密码错误次数
     */
    private Integer passwordTimes;

    /**
     * 多租户id
     */
    private String multiTenantId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 验证码
     */
    private String verificationCode;

    /**
     * 角色id
     */
    private String roleId;
}