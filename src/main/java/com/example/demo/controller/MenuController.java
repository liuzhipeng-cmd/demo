package com.example.demo.controller;

import com.example.common.utils.Result;
import com.example.demo.pojo.MenuPojo;
import com.example.demo.service.MenuService;
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
 * 菜单管理
 */
@Controller
public class MenuController {

    @Autowired
    MenuService menuService;

    @Autowired
    UserService userService;

    @RequestMapping("/menu")
    public String jumpMenu(ModelMap model) {

        return "system/menu";
    }

    /**
     * 分页查询
     *
     * @param pojo
     * @return
     */
    @RequestMapping("/listMenuInfoPage")
    @ResponseBody
    public Map<String, Object> listMenuInfoPage(MenuPojo pojo) {

        PageInfo<MenuPojo> page = menuService.listMenuInfoPage(pojo);

        return new Result().Success(page.getTotal(), page.getList());
    }

    /**
     * 保存数据
     *
     * @return
     */
    @RequestMapping(value = "/saveDataMenu", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveDataMenu(HttpServletRequest request, MenuPojo pojo) {

        int num = menuService.saveDataMenu(request, pojo);

        if (num > 0) {
            return new Result().success(null);
        }
        return new Result().fail("");
    }

    /**
     * 更新数据
     *
     * @return
     */
    @RequestMapping(value = "/updateDataMenu", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateDataMenu(HttpServletRequest request, MenuPojo pojo) {

        int num = menuService.updateDataMenu(request, pojo);

        if (num > 0) {
            return new Result().success(null);
        }
        return new Result().fail("");
    }

    /**
     * 更新菜单状态
     *
     * @return
     */
    @RequestMapping(value = "/updateMenuStatus", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateMenuStatus(String id, String type) {

        int num = menuService.updateMenuStatus(id, type);

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
    @RequestMapping(value = "/deleteMenu", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteMenu(String id) {

        int num = menuService.deleteMenu(id);

        if (num > 0) {
            return new Result().success(null);
        }
        return new Result().fail("");
    }

    @RequestMapping("/menuChild")
    public String jumpMenuChild(ModelMap model, String pid) {
        model.addAttribute("menuPid", pid);
        return "system/menu-child";
    }

    /**
     * 分页查询-子节点
     *
     * @param pojo
     * @return
     */
    @RequestMapping("/listMenuInfoChildPage")
    @ResponseBody
    public Map<String, Object> listMenuInfoChildPage(MenuPojo pojo) {

        PageInfo<MenuPojo> page = menuService.listMenuInfoChildPage(pojo);

        return new Result().Success(page.getTotal(), page.getList());
    }

    /**
     * 获取菜单
     *
     * @return
     */
    @RequestMapping("/menuTreeData")
    @ResponseBody
    public List<Map<String, Object>> menuTreeData() {

        List<Map<String, Object>> menuList = menuService.getMenuRoleList();

        return menuList;
    }
}
