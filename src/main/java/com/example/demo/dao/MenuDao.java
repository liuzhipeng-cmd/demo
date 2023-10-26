package com.example.demo.dao;

import com.example.demo.pojo.MenuPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuDao {

    /**
     * 查询所有菜单
     *
     * @param pojo
     * @return
     */
    List<MenuPojo> listMenuInfoPage(MenuPojo pojo);

    /**
     * 保存数据
     *
     * @param pojo
     * @return
     */
    Integer saveDataMenu(MenuPojo pojo);

    /**
     * 更新数据
     *
     * @param pojo
     * @return
     */
    Integer updateDataMenu(MenuPojo pojo);

    /**
     * 更新菜单状态
     *
     * @param id
     * @param type
     * @return
     */
    Integer updateMenuStatus(@Param("id") String id, @Param("type") String type);

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    Integer deleteMenu(String id);

    /**
     * 分页查询-子节点
     *
     * @param pojo
     * @return
     */
    List<MenuPojo> listMenuInfoChildPage(MenuPojo pojo);

    /**
     * 获取主菜单
     *
     * @return
     */
    List<Map<String, Object>> getHomeMenuList(String userName);

    /**
     * 通过主菜单id查询对应的子菜单
     *
     * @param pid
     * @return
     */
    List<Map<String, Object>> getChildMenuList(@Param("pid") String pid,@Param("userName") String userName);

    /**
     * 获取主菜单
     *
     * @return
     */
    List<Map<String, Object>> getHomeMenuRoleList(String userName);

    /**
     * 通过主菜单id查询对应的子菜单
     *
     * @param pid
     * @return
     */
    List<Map<String, Object>> getChildMenuRoleList(@Param("pid") String pid,@Param("userName") String userName);
}
