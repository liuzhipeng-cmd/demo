package com.example.demo.controller;

import com.example.common.utils.DesUtils;
import com.example.demo.pojo.UserPojo;
import com.example.demo.service.LoginService;
import com.example.demo.service.MenuService;
import com.example.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;

    @RequestMapping("/")
    public String jumpLogin(String errorText, ModelMap model) {
        // 判断是否有报错
        if (!StringUtils.isEmpty(errorText)) {
            model.addAttribute("errorText", errorText);
        }
        return "login";
    }

    /**
     * 登录
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/login_in", method = RequestMethod.POST)
    public String loginIn(HttpServletRequest request, RedirectAttributes model, UserPojo pojo) {
        HttpSession session = request.getSession();
        // 前台传入的账号
        String userNameReception = pojo.getUserName();
        // 前台传入的密码
        String userPasswordReception = pojo.getUserPassword();
        // 前台传入的验证码
        String verificationCodeReception = pojo.getVerificationCode();
        if (StringUtils.isEmpty(userNameReception)) {
            model.addAttribute("errorText", "请输入账号！");
            return "redirect:/";
        }
        if (StringUtils.isEmpty(userPasswordReception)) {
            model.addAttribute("errorText", "请输入密码！");
            return "redirect:/";
        }
        if (StringUtils.isEmpty(verificationCodeReception)) {
            model.addAttribute("errorText", "请输入验证码！");
            return "redirect:/";
        } else {
            // 获取验证码
            String verificationCode = String.valueOf(session.getAttribute("verificationCode"));
            if (!verificationCodeReception.equals(verificationCode)) {
                model.addAttribute("errorText", "验证码不正确！");
                return "redirect:/";
            }
        }
        // 通过账号查询用户信息
        UserPojo user = userService.getUser(userNameReception);
        if (user == null) {
            model.addAttribute("errorText", "账号错误！");
            return "redirect:/";
        } else {
            // 数据库查询出来的密码
            String userPasswordDatabase = user.getUserPassword();
            // 对密码进行解密
            String decrypt = new DesUtils().decrypt(userPasswordDatabase);
            // 判断密码是否一致
            if (!userPasswordReception.equals(decrypt)) {
                model.addAttribute("errorText", "密码错误！");
                return "redirect:/";
            }
        }
        // 将用户信息存入session中以备后续使用
        session.setAttribute("userInfo", user);

        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String jumpIndex(ModelMap model) {
        // 获取菜单
        List<Map<String, Object>> menuList = menuService.getMenuList();
        model.addAttribute("menuList", menuList);
        return "index";
    }

    /**
     * 生成图片验证码
     *
     * @param response
     * @param request
     */
    @RequestMapping(value = "/createImg", method = RequestMethod.GET)
    @ResponseBody
    public void createImg(HttpServletResponse response, HttpServletRequest request) {
        final String CHARACTERS = "123456789abcdefghijkmnpqrstuvwsyz";
        final int LENGTH = 5;
        // 设置响应内容
        response.setContentType("image/png");
        // 禁止缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        BufferedImage image = new BufferedImage(120, 30, BufferedImage.TYPE_INT_RGB);
        // 获取 Graphics2D 对象以便绘制图形
        Graphics2D g = image.createGraphics();
        // 设置透明度
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1.0f));
        // 绘制干扰背景
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        Random random = new Random();
        // 绘制字符
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
            g.setFont(new Font("楷体", Font.PLAIN, 20));
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawString(String.valueOf(CHARACTERS.charAt(index)), 24 * i, 20);
        }
        // 绘制干扰线
        for (int i = 0; i < 5; i++) {
            int x1 = random.nextInt(image.getWidth());
            int y1 = random.nextInt(image.getHeight());
            int x2 = random.nextInt(image.getWidth());
            int y2 = random.nextInt(image.getHeight());
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawLine(x1, y1, x2, y2);
        }
        // 绘制干扰边框
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, image.getWidth() - 1, image.getHeight() - 1);
        HttpSession session = request.getSession();
        // 将验证码字符串保存在 session 中
        session.setAttribute("verificationCode", sb.toString());
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 输出图片
        try {
            ImageIO.write(image, "png", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/home")
    public String jumpHome(ModelMap model) {
//        // 获取菜单
//        List<Map<String, Object>> menuList = menuService.getMenuList();
//        model.addAttribute("menuList", menuList);
        return "home";
    }
}
