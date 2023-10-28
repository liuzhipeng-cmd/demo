package com.example.demo.controller;

import com.example.common.utils.ConstantUtils;
import com.example.common.utils.Result;
import com.example.demo.pojo.SysDicPojo;
import com.example.demo.pojo.UserPojo;
import com.example.demo.service.RoleService;
import com.example.demo.service.SysDicService;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    SysDicService sysDicService;

    @RequestMapping("/user")
    public String jumpUser(HttpServletRequest request, ModelMap model) {
        // 查询所有的角色id与name
        List<Map<String, Object>> roleList = roleService.listRoleIdAndName(request);
        model.addAttribute("roleList", roleList);
        // 获取性别
        List<SysDicPojo> genderTypeList = sysDicService.getDictByCode("GENDER_TYPE");
        model.addAttribute("genderTypeList", genderTypeList);
        return "system/user";
    }

    /**
     * 分页查询
     *
     * @param pojo
     * @return
     */
    @RequestMapping("/listUserInfoPage")
    @ResponseBody
    public Map<String, Object> listUserInfoPage(HttpServletRequest request, UserPojo pojo) {

        PageInfo<UserPojo> page = userService.listUserInfoPage(request,pojo);

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
        UserPojo user = userService.getUser(pojo.getUserName());
        if (user != null) {
            return new Result().fail("账号已存在，请重新输入");
        } else {
            int num = userService.saveDataUser(request, pojo);
            if (num > 0) {
                return new Result().success(null);
            }
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

    /**
     * 修改密码
     *
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updatePassword(HttpServletRequest request, String userName, String password) {

        int num = userService.updatePassword(userName, password);

        if (num > 0) {
            request.getSession().setAttribute(ConstantUtils.USER_INFO, null);
            return new Result().success(null);
        }
        return new Result().fail("");
    }
}
