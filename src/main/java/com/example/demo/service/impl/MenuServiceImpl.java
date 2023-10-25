package com.example.demo.service.impl;

import com.example.common.utils.DateTimeUtils;
import com.example.common.utils.GuidUtils;
import com.example.demo.dao.MenuDao;
import com.example.demo.pojo.MenuPojo;
import com.example.demo.pojo.UserPojo;
import com.example.demo.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    MenuDao menuDao;

    /**
     * 分页查询
     *
     * @param pojo
     * @return
     */
    @Override
    public PageInfo<MenuPojo> listMenuInfoPage(MenuPojo pojo) {

        PageHelper.startPage(pojo.getPage(), pojo.getLimit());

        List<MenuPojo> list = menuDao.listMenuInfoPage(pojo);

        PageInfo<MenuPojo> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    /**
     * 保存数据
     *
     * @param request
     * @param pojo
     * @return
     */
    @Override
    public int saveDataMenu(HttpServletRequest request, MenuPojo pojo) {
        UserPojo userPojo = (UserPojo) request.getSession().getAttribute("userInfo");
        // 获取当前时间（日期+时分秒）
        pojo.setCreateTime(new DateTimeUtils().getYearMonthDayHourMinuteSecond());
        // 创建人
        pojo.setCreator(userPojo.getId());
        // 插入数据
        Integer num = menuDao.saveDataMenu(pojo);

        return num;
    }

    /**
     * 更新数据
     *
     * @param request
     * @param pojo
     * @return
     */
    @Override
    public int updateDataMenu(HttpServletRequest request, MenuPojo pojo) {
        UserPojo userPojo = (UserPojo) request.getSession().getAttribute("userInfo");
        // 获取当前时间（日期+时分秒）
        pojo.setUpdateTime(new DateTimeUtils().getYearMonthDayHourMinuteSecond());
        // 更新人
        pojo.setUpdator(userPojo.getId());
        // 更新数据
        Integer num = menuDao.updateDataMenu(pojo);
        return num;
    }

    /**
     * 更新菜单状态
     *
     * @param id
     * @param type
     * @return
     */
    @Override
    public int updateMenuStatus(String id, String type) {

        Integer num = menuDao.updateMenuStatus(id, type);

        return num;
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @Override
    public int deleteMenu(String id) {

        Integer num = menuDao.deleteMenu(id);

        return num;
    }

    /**
     * 分页查询-子节点
     *
     * @param pojo
     * @return
     */
    @Override
    public PageInfo<MenuPojo> listMenuInfoChildPage(MenuPojo pojo) {

        PageHelper.startPage(pojo.getPage(), pojo.getLimit());

        List<MenuPojo> list = menuDao.listMenuInfoChildPage(pojo);

        PageInfo<MenuPojo> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    /**
     * 获取菜单
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getMenuList() {
        // 获取主菜单
        List<Map<String, Object>> list = menuDao.getHomeMenuList();
        // 判断主菜单是否有值
        if (!CollectionUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                String pid = String.valueOf(list.get(i).get("id"));
                // 通过主菜单id查询对应的子菜单
                List<Map<String, Object>> childList = menuDao.getChildMenuList(pid);
                list.get(i).put("childList", childList);
            }
        }

        return list;
    }

    /**
     * 获取菜单(角色模块)
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getMenuRoleList() {
        // 获取主菜单
        List<Map<String, Object>> list = menuDao.getHomeMenuRoleList();
        // 判断主菜单是否有值
        if (!CollectionUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                String pid = String.valueOf(list.get(i).get("id"));
                // 通过主菜单id查询对应的子菜单
                List<Map<String, Object>> childList = menuDao.getChildMenuRoleList(pid);
                list.get(i).put("children", childList);
            }
        }

        return list;
    }
}
