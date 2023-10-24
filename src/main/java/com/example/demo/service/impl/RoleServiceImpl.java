package com.example.demo.service.impl;

import com.example.common.utils.DateTimeUtils;
import com.example.common.utils.GuidUtils;
import com.example.demo.dao.RoleDao;
import com.example.demo.pojo.RolePojo;
import com.example.demo.pojo.UserPojo;
import com.example.demo.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public int updateDataRole(HttpServletRequest request, RolePojo pojo) {
        UserPojo userPojo = (UserPojo) request.getSession().getAttribute("userInfo");
        // 获取当前时间（日期+时分秒）
        pojo.setUpdateTime(new DateTimeUtils().getYearMonthDayHourMinuteSecond());
        // 更新人
        pojo.setUpdator(userPojo.getId());
        // 更新数据
        Integer num = roleDao.updateDataRole(pojo);
        return num;
    }

    /**
     * 保存数据
     *
     * @param request
     * @param pojo
     * @return
     */
    @Override
    public int saveDataRole(HttpServletRequest request, RolePojo pojo) {
        UserPojo userPojo = (UserPojo) request.getSession().getAttribute("userInfo");
        // 创建主键
        pojo.setId(new GuidUtils().GuidUtils());
        // 获取当前时间（日期+时分秒）
        pojo.setCreateTime(new DateTimeUtils().getYearMonthDayHourMinuteSecond());
        // 创建人
        pojo.setCreator(userPojo.getId());
        // 插入数据
        Integer num = roleDao.saveDataRole(pojo);

        return num;
    }
}
