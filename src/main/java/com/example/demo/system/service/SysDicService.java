package com.example.demo.system.service;

import com.example.demo.system.pojo.SysDicPojo;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SysDicService {

    /**
     * 分页查询
     *
     * @param pojo
     * @return
     */
    PageInfo<SysDicPojo> listSysDicPage(SysDicPojo pojo);

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    int deleteSysDic(String id);

    /**
     * 更新数据
     *
     * @param request
     * @param pojo
     * @return
     */
    int updateDataSysDic(HttpServletRequest request, SysDicPojo pojo);

    /**
     * 保存数据
     *
     * @param request
     * @param pojo
     * @return
     */
    int saveDataSysDic(HttpServletRequest request, SysDicPojo pojo);

    /**
     * 分页查询(子节点)
     *
     * @param pojo
     * @return
     */
    PageInfo<SysDicPojo> listSysDicChildPage(SysDicPojo pojo);

    /**
     * 通过数据字典类型查询该类型下的数据
     *
     * @param genderType
     * @return
     */
    List<SysDicPojo> getDictByCode(String genderType);

    /**
     * 根据字典类型与值获取名称
     *
     * @param data
     * @param type
     * @return
     */
    String getDicName(String data, String type);
}
