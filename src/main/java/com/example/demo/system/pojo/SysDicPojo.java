package com.example.demo.system.pojo;

import com.example.common.utils.Base;
import lombok.Data;

@Data
public class SysDicPojo extends Base {

    /**
     * 字典编码
     */
    private String dicCode;

    /**
     * 字典名称
     */
    private String dicName;

    /**
     * 字典排序
     */
    private String dicOrder;

    /**
     * 字典类型
     */
    private String dicType;

    /**
     * 备注
     */
    private String remarks;
}
