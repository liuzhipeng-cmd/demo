package com.example.demo.pojo;

import com.example.common.utils.Base;
import lombok.Data;

@Data
public class MenuPojo extends Base {

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单状态（0：未锁定 1：锁定）
     */
    private Integer menuStatus;

    /**
     * 菜单动作
     */
    private String menuAction;

    /**
     * 是否有子节点（1：否 2：是）
     */
    private Integer isChildNode;

    /**
     * 显示顺序
     */
    private Integer menuOrder;

    /**
     * 菜单备注
     */
    private String menuRemarks;
}
