package com.example.demo.pojo;

import com.example.common.utils.Base;
import lombok.Data;

@Data
public class RoleMenuPojo extends Base {

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 菜单id
     */
    private String menuId;

}
