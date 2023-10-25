package com.example.demo.dao;

import com.example.demo.pojo.RoleMenuPojo;
import com.example.demo.pojo.RolePojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleDao {

    /**
     * 分页查询
     *
     * @param pojo
     * @return
     */
    List<RolePojo> listRoleInfoPage(RolePojo pojo);

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    Integer deleteRole(String id);

    /**
     * 更新数据
     *
     * @param pojo
     * @return
     */
    Integer updateDataRole(RolePojo pojo);

    /**
     * 保存数据
     *
     * @param pojo
     * @return
     */
    Integer saveDataRole(RolePojo pojo);

    /**
     * 查询所有的角色id与name
     *
     * @return
     */
    List<Map<String, Object>> listRoleIdAndName();

    /**
     * 通过角色id删除用户与角色关联表信息
     *
     * @param id
     * @return
     */
    Integer deleteUserRole(String id);

    /**
     * 将角色id与菜单id保存到数据库中
     *
     * @param roleMenuPojo
     */
    Integer saveRoleMenu(RoleMenuPojo roleMenuPojo);

    /**
     * 通过角色id查询菜单id
     *
     * @param roleId
     * @return
     */
    List<Integer> listRoleMenuId(String roleId);

    /**
     * 通过角色id清空之前的配置的菜单
     *
     * @param roleId
     */
    void deleteRoleMenu(String roleId);
}
