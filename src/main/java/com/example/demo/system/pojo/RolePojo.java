package com.example.demo.system.pojo;

import com.example.common.utils.Base;
import lombok.Data;

import java.util.List;

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

    /**
     * 菜单id
     */
    private List<Integer> menuId;
}
