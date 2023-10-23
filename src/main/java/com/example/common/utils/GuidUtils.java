package com.example.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.UserPojo;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 获取id
 */
public class GuidUtils {

    /**
     * 创建主键id
     *
     * @return
     */
    public String GuidUtils() {
        String guid = "";

        guid = UUID.randomUUID().toString().replace("-", "");

        return guid;
    }

    /**
     * 获取访问者IP
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
     *
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            if (ip.contains("../") || ip.contains("..\'")) {
                return "";
            }
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                ip = ip.substring(0, index);
            }
            if (ip.contains("../") || ip.contains("..\'")) {
                return "";
            }
            return ip;
        } else {
            ip = request.getRemoteAddr();
            if (ip.contains("../") || ip.contains("..\'")) {
                return "";
            }
            if (ip.equals("0:0:0:0:0:0:0:1")) {
                ip = "127.0.0.1";
            }
            return ip;
        }
    }

    /**
     * 实体转map
     *
     * @param object
     * @return
     */
    public Map<String, Object> pojoToMap(Object object) {
        Map<String, Object> map = new HashMap<>();
        map = JSONObject.parseObject(JSONObject.toJSONString(object), Map.class);
        return map;
    }
}
