package com.example.demo.system.controller;

import com.example.common.utils.Result;
import com.example.demo.system.pojo.SysDicPojo;
import com.example.demo.system.service.SysDicService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class SysDicController {

    @Autowired
    SysDicService sysDicService;

    @RequestMapping("/sysDic")
    public String jumpRole() {

        return "system/sysDic";
    }

    /**
     * 分页查询
     *
     * @param pojo
     * @return
     */
    @RequestMapping("/listSysDicPage")
    @ResponseBody
    public Map<String, Object> listSysDicPage(SysDicPojo pojo) {

        PageInfo<SysDicPojo> page = sysDicService.listSysDicPage(pojo);

        return new Result().Success(page.getTotal(), page.getList());
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteSysDic", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteSysDic(String id) {

        int num = sysDicService.deleteSysDic(id);

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
    @RequestMapping(value = "/updateDataSysDic", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateDataSysDic(HttpServletRequest request, SysDicPojo pojo) {

        int num = sysDicService.updateDataSysDic(request, pojo);

        if (num > 0) {
            return new Result().success(null);
        }
        return new Result().fail("");
    }

    /**
     * 保存数据
     *
     * @return
     */
    @RequestMapping(value = "/saveDataSysDic", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveDataSysDic(HttpServletRequest request, SysDicPojo pojo) {

        int num = sysDicService.saveDataSysDic(request, pojo);

        if (num > 0) {
            return new Result().success(null);
        }
        return new Result().fail("");
    }

    /**
     * 跳转到子节点
     *
     * @param model
     * @param pid
     * @return
     */
    @RequestMapping("/sysDicChild")
    public String jumpSysDicChild(ModelMap model, String pid) {
        model.addAttribute("sysDicPid", pid);
        return "system/sysDic-child";
    }

    /**
     * 分页查询(子节点)
     *
     * @param pojo
     * @return
     */
    @RequestMapping("/listSysDicChildPage")
    @ResponseBody
    public Map<String, Object> listSysDicChildPage(SysDicPojo pojo) {

        PageInfo<SysDicPojo> page = sysDicService.listSysDicChildPage(pojo);

        return new Result().Success(page.getTotal(), page.getList());
    }
}
