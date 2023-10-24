package com.example.demo.controller;

import com.example.common.utils.DateTimeUtils;
import com.example.common.utils.GuidUtils;
import com.example.common.utils.Result;
import com.example.demo.pojo.UserPojo;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户管理
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/user")
    public String jumpUser() {

        return "user";
    }

    /**
     * 分页查询
     *
     * @param pojo
     * @return
     */
    @RequestMapping("/listUserInfoPage")
    @ResponseBody
    public Map<String, Object> listUserInfoPage(UserPojo pojo) {

        PageInfo<UserPojo> page = userService.listUserInfoPage(pojo);

        return new Result().Success(page.getTotal(), page.getList());
    }

    /**
     * 保存数据
     *
     * @param pojo
     * @return
     */
    @RequestMapping(value = "/saveDataUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveDataUser(HttpServletRequest request, UserPojo pojo) {

        int num = userService.saveDataUser(request, pojo);
        if (num > 0) {
            return new Result().success(null);
        }
        return new Result().fail("");
    }

    /**
     * 更新账号状态
     *
     * @return
     */
    @RequestMapping(value = "/updateUserStatus", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateUserStatus(String id, String type) {

        int num = userService.updateUserStatus(id, type);

        if (num > 0) {
            return new Result().success(null);
        }
        return new Result().fail("");
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteUser(String id) {

        int num = userService.deleteUser(id);

        if (num > 0) {
            return new Result().success(null);
        }
        return new Result().fail("");
    }

    /**
     * 更新数据
     *
     * @param request
     * @param pojo
     * @return
     */
    @RequestMapping(value = "/updateDataUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateDataUser(HttpServletRequest request, UserPojo pojo) {

        int num = userService.updateDataUser(request, pojo);

        if (num > 0) {
            return new Result().success(null);
        }
        return new Result().fail("");
    }
}
