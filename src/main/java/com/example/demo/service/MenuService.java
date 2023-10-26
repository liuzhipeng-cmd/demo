package com.example.demo.service;

import com.example.demo.pojo.MenuPojo;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.List;
import java.util.Map;

public interface MenuService {

    /**
     * 分页查询
     *
     * @param pojo
     * @return
     */
    PageInfo<MenuPojo> listMenuInfoPage(MenuPojo pojo);

    /**
     * 保存数据
     *
     * @param request
     * @param pojo
     * @return
     */
    int saveDataMenu(HttpServletRequest request, MenuPojo pojo);

    /**
     * 更新数据
     *
     * @param request
     * @param pojo
     * @return
     */
    int updateDataMenu(HttpServletRequest request, MenuPojo pojo);

    /**
     * 更新菜单状态
     *
     * @param id
     * @param type
     * @return
     */
    int updateMenuStatus(String id, String type);

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    int deleteMenu(String id);

    /**
     * 分页查询-子节点
     *
     * @param pojo
     * @return
     */
    PageInfo<MenuPojo> listMenuInfoChildPage(MenuPojo pojo);

    /**
     * 获取菜单
     *
     * @return
     */
    List<Map<String, Object>> getMenuList(String userName);

    /**
     * 获取菜单(角色模块)
     *
     * @return
     */
    List<Map<String, Object>> getMenuRoleList(String userName);

}
