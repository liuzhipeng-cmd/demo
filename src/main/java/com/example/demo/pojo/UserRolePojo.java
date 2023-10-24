package com.example.demo.pojo;

import com.example.common.utils.Base;
import lombok.Data;

@Data
public class UserRolePojo extends Base {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;
}
