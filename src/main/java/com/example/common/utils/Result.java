package com.example.common.utils;

import java.util.HashMap;
import java.util.Map;

public class Result {


    public Map<String, Object> success(Object data) {
        return success(200, "操作成功", data);
    }

    public Map<String, Object> success(int code, String msg, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", data);
        return map;
    }

    public Map<String, Object> fail(String msg) {

        return fail(400, msg, null);
    }

    public Map<String, Object> fail(int code, String msg, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", data);
        return map;
    }


    /**
     * 分页查询返回数据（成功）
     *
     * @param count 总条数
     * @param data  list数据
     * @return
     */
    public Map<String, Object> Success(long count, Object data) {

        return Success(0, "", count, data);
    }

    public Map<String, Object> Success(int code, String msg, long count, Object dataList) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        map.put("count", count);
        map.put("data", dataList);
        return map;
    }

    /**
     * 分页查查询返回数据（失败）
     *
     * @param msg 失败原因
     * @return
     */
    public Map<String, Object> Error(String msg) {

        return Error(1, msg, 0, null);
    }

    public Map<String, Object> Error(int code, String msg, long count, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        map.put("count", count);
        map.put("data", data);
        return map;
    }
}
