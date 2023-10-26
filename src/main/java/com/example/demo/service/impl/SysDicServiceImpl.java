package com.example.demo.service.impl;

import com.example.common.utils.DateTimeUtils;
import com.example.demo.dao.SysDicDao;
import com.example.demo.pojo.SysDicPojo;
import com.example.demo.pojo.UserPojo;
import com.example.demo.service.SysDicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class SysDicServiceImpl implements SysDicService {

    @Resource
    SysDicDao sysDicDao;

    /**
     * 分页查询
     *
     * @param pojo
     * @return
     */
    @Override
    public PageInfo<SysDicPojo> listSysDicPage(SysDicPojo pojo) {

        PageHelper.startPage(pojo.getPage(), pojo.getLimit());

        List<SysDicPojo> list = sysDicDao.listSysDicPage(pojo);

        PageInfo<SysDicPojo> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @Override
    public int deleteSysDic(String id) {

        int num = sysDicDao.deleteSysDic(id);

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
    public int updateDataSysDic(HttpServletRequest request, SysDicPojo pojo) {
        UserPojo userPojo = (UserPojo) request.getSession().getAttribute("userInfo");
        // 获取当前时间（日期+时分秒）
        pojo.setUpdateTime(new DateTimeUtils().getYearMonthDayHourMinuteSecond());
        // 更新人
        pojo.setUpdator(userPojo.getId());
        // 更新数据
        int num = sysDicDao.updateDataSysDic(pojo);
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
    public int saveDataSysDic(HttpServletRequest request, SysDicPojo pojo) {
        UserPojo userPojo = (UserPojo) request.getSession().getAttribute("userInfo");
        // 获取当前时间（日期+时分秒）
        pojo.setCreateTime(new DateTimeUtils().getYearMonthDayHourMinuteSecond());
        // 创建人
        pojo.setCreator(userPojo.getId());
        // 插入数据
        int num = sysDicDao.saveDataSysDic(pojo);

        return num;
    }

    /**
     * 分页查询(子节点)
     *
     * @param pojo
     * @return
     */
    @Override
    public PageInfo<SysDicPojo> listSysDicChildPage(SysDicPojo pojo) {

        PageHelper.startPage(pojo.getPage(), pojo.getLimit());

        List<SysDicPojo> list = sysDicDao.listSysDicChildPage(pojo);

        PageInfo<SysDicPojo> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }
}
