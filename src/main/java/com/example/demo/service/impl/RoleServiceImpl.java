package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.common.utils.DateTimeUtils;
import com.example.common.utils.GuidUtils;
import com.example.demo.dao.RoleDao;
import com.example.demo.pojo.RoleMenuPojo;
import com.example.demo.pojo.RolePojo;
import com.example.demo.pojo.UserPojo;
import com.example.demo.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleDao roleDao;

    /**
     * 分页查询
     *
     * @param pojo
     * @return
     */
    @Override
    public PageInfo<RolePojo> listRoleInfoPage(RolePojo pojo) {

        PageHelper.startPage(pojo.getPage(), pojo.getLimit());

        List<RolePojo> list = roleDao.listRoleInfoPage(pojo);
        if (!CollectionUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                String id = list.get(i).getId();
                // 通过角色id查询菜单id
                List<Integer> menuIdList = roleDao.listRoleMenuId(id);
                list.get(i).setMenuId(menuIdList);
            }
        }

        PageInfo<RolePojo> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @Override
    public int deleteRole(String id) {

        int num = roleDao.deleteRole(id);
        if (num > 0) {
            num = deleteUserRole(id);
        }

        return num;
    }

    /**
     * 更新数据
     *
     * @param request
     * @param pojo
     * @param menuTreeData
     * @return
     */
    @Override
    @Transactional
    public int updateDataRole(HttpServletRequest request, RolePojo pojo, String menuTreeData) {
        UserPojo userPojo = (UserPojo) request.getSession().getAttribute("userInfo");
        // 获取当前时间（日期+时分秒）
        pojo.setUpdateTime(new DateTimeUtils().getYearMonthDayHourMinuteSecond());
        // 更新人
        pojo.setUpdator(userPojo.getId());
        // 更新数据
        Integer num = roleDao.updateDataRole(pojo);
        if (num > 0) {
            // 将角色id与菜单id保存到数据库中
            num = saveRoleMenu(pojo, menuTreeData);
        }
        return num;
    }

    /**
     * 保存数据
     *
     * @param request
     * @param pojo
     * @param menuTreeData
     * @return
     */
    @Override
    @Transactional
    public int saveDataRole(HttpServletRequest request, RolePojo pojo, String menuTreeData) {
        UserPojo userPojo = (UserPojo) request.getSession().getAttribute("userInfo");
        // 创建主键
        pojo.setId(new GuidUtils().GuidUtils());
        // 获取当前时间（日期+时分秒）
        pojo.setCreateTime(new DateTimeUtils().getYearMonthDayHourMinuteSecond());
        // 创建人
        pojo.setCreator(userPojo.getId());
        // 插入数据
        Integer num = roleDao.saveDataRole(pojo);
        if (num > 0) {
            // 将角色id与菜单id保存到数据库中
            num = saveRoleMenu(pojo, menuTreeData);
        }
        return num;
    }

    /**
     * 查询所有的角色id与name
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> listRoleIdAndName() {

        List<Map<String, Object>> list = roleDao.listRoleIdAndName();

        return list;
    }

    /**
     * 通过角色id删除用户与角色关联表信息
     *
     * @return
     */
    private int deleteUserRole(String id) {

        int num = roleDao.deleteUserRole(id);

        return num;
    }

    /**
     * 将角色id与菜单id保存到数据库中
     *
     * @return
     */
    private int saveRoleMenu(RolePojo pojo, String menuTreeData) {
        // 判断如果创建时间为空则清除角色与菜单关联信息表的信息
        if (pojo.getCreateTime() == null) {
            // 通过角色id清空之前的配置的菜单
            roleDao.deleteRoleMenu(pojo.getId());
        }
        // 转化数据
        List<Map<String, Object>> list = new GuidUtils().stringToListMap(menuTreeData);
        int num = 0;
        if (!CollectionUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                // 获得菜单id
                String menuId = String.valueOf(list.get(i).get("id"));
                //拼接参数
                RoleMenuPojo roleMenuPojo = new RoleMenuPojo();
                roleMenuPojo.setRoleId(pojo.getId());
                roleMenuPojo.setMenuId(menuId);
                // 如果是新增测CreateTime、Creator有值
                roleMenuPojo.setCreateTime(pojo.getCreateTime());
                roleMenuPojo.setCreator(pojo.getCreator());
                // 如果是新增测UpdateTime、Updator有值
                roleMenuPojo.setUpdateTime(pojo.getCreateTime());
                roleMenuPojo.setUpdator(pojo.getUpdator());
                num = roleDao.saveRoleMenu(roleMenuPojo);
                if (num > 0) {
                    // 获取子节点菜单
                    List<Map<String, Object>> childrenList = (List<Map<String, Object>>) list.get(i).get("children");
                    // 判断子节点菜单是否有数据
                    if (!CollectionUtils.isEmpty(childrenList)) {
                        for (int a = 0; a < childrenList.size(); a++) {
                            // 获取字节但菜单id
                            String menuIdChild = String.valueOf(childrenList.get(a).get("id"));
                            RoleMenuPojo roleMenuPojoChild = new RoleMenuPojo();
                            roleMenuPojoChild.setRoleId(pojo.getId());
                            roleMenuPojoChild.setMenuId(menuIdChild);
                            // 如果是新增测CreateTime、Creator有值
                            roleMenuPojoChild.setCreateTime(pojo.getCreateTime());
                            roleMenuPojoChild.setCreator(pojo.getCreator());
                            // 如果是新增测UpdateTime、Updator有值
                            roleMenuPojoChild.setUpdateTime(pojo.getCreateTime());
                            roleMenuPojoChild.setUpdator(pojo.getUpdator());
                            num = roleDao.saveRoleMenu(roleMenuPojoChild);
                        }
                    }
                }
            }
        } else {
            num = 1;
        }
        return num;
    }
}
