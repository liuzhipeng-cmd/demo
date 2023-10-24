package com.example.demo.pojo;

import com.example.common.utils.Base;
import lombok.Data;

@Data
public class RolePojo extends Base {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remarks;
}
