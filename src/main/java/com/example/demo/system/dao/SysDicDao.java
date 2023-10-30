package com.example.demo.system.dao;

import com.example.demo.system.pojo.SysDicPojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDicDao {

    /**
     * 查询所有字典
     *
     * @param pojo
     * @return
     */
    List<SysDicPojo> listSysDicPage(SysDicPojo pojo);

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    Integer deleteSysDic(String id);

    /**
     * 更新数据
     *
     * @param pojo
     * @return
     */
    Integer updateDataSysDic(SysDicPojo pojo);

    /**
     * 保存数据
     *
     * @param pojo
     * @return
     */
    Integer saveDataSysDic(SysDicPojo pojo);

    /**
     * 分页查询(子节点)
     *
     * @param pojo
     * @return
     */
    List<SysDicPojo> listSysDicChildPage(SysDicPojo pojo);

    /**
     * 通过数据字典类型查询该类型下的数据
     *
     * @param dicType
     * @return
     */
    List<SysDicPojo> getDictByCode(String dicType);
}
