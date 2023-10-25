package com.example.demo.service;

import com.example.demo.pojo.RolePojo;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface RoleService {

    /**
     * 分页查询
     *
     * @param pojo
     * @return
     */
    PageInfo<RolePojo> listRoleInfoPage(RolePojo pojo);

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    int deleteRole(String id);

    /**
     * 更新数据
     *
     * @param request
     * @param pojo
     * @param menuTreeData
     * @return
     */
    int updateDataRole(HttpServletRequest request, RolePojo pojo, String menuTreeData);

    /**
     * 保存数据
     *
     * @param request
     * @param pojo
     * @param menuTreeData
     * @return
     */
    int saveDataRole(HttpServletRequest request, RolePojo pojo, String menuTreeData);

    /**
     * 查询所有的角色id与name
     *
     * @return
     */
    List<Map<String, Object>> listRoleIdAndName();

}
