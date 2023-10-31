package com.example.common.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class Base implements Serializable {

    /**
     * 分页参数
     */
    private int page;
    private int limit;

    /**
     * 主键
     */
    private String id;

    /**
     * 父节点id
     */
    private String pid;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 是否删除 0：未删除 1：删除
     */
    private Integer isDel;

    /**
     * 创建人id
     */
    private String creator;

    /**
     * 更新人id
     */
    private String updator;
}
