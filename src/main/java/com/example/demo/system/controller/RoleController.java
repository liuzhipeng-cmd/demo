package com.example.demo.system.controller;

import com.example.common.utils.Result;
import com.example.demo.system.pojo.RolePojo;
import com.example.demo.system.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping("/role")
    public String jumpRole() {

        return "system/role";
    }

    /**
     * 分页查询
     *
     * @param pojo
     * @return
     */
    @RequestMapping("/listRoleInfoPage")
    @ResponseBody
    public Map<String, Object> listRoleInfoPage(HttpServletRequest request, RolePojo pojo) {

        PageInfo<RolePojo> page = roleService.listRoleInfoPage(request,pojo);

        return new Result().Success(page.getTotal(), page.getList());
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteRole(String id) {

        int num = roleService.deleteRole(id);

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
    @RequestMapping(value = "/updateDataRole", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateDataRole(HttpServletRequest request, RolePojo pojo) {

        String menuTreeData = request.getParameter("menuTreeData");

        int num = roleService.updateDataRole(request, pojo,menuTreeData);

        if (num > 0) {
            return new Result().success(null);
        }
        return new Result().fail("");
    }

    /**
     * 保存数据
     *
     * @param request
     * @param pojo
     * @return
     */
    @RequestMapping(value = "/saveDataRole", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveDataRole(HttpServletRequest request, RolePojo pojo) {

        String menuTreeData = request.getParameter("menuTreeData");

        int num = roleService.saveDataRole(request, pojo, menuTreeData);

        if (num > 0) {
            return new Result().success(null);
        }
        return new Result().fail("");
    }
}
