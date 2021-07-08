package com.powerstot.travels.controller;


import com.powerstot.travels.entity.Result;
import com.powerstot.travels.entity.User;
import com.powerstot.travels.service.UserService;
import com.powerstot.travels.utils.CreateImageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller // = Controller+ResponseBody
@RequestMapping("user")
@CrossOrigin    // 允许跨域
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查找在线人数
     * @param request
     * @return
     */
    @RequestMapping("findScc")
    @ResponseBody
    public String findScc(HttpServletRequest request) {
        String scc = String.valueOf(request.getServletContext().getAttribute("scc"));
        System.out.println(scc);
        return scc;
    }

    /**
     * 通过用户id查找用户
     * @param userId
     * @param request
     * @return
     */
    @RequestMapping("findUserName")
    @ResponseBody
    public String findUserName(String userId, HttpServletRequest request) {
        User user = (User) request.getServletContext().getAttribute("USER");
        return user.getUsername();
    }

    /**
     * 用户登出
     * @param userId
     * @param request
     * @return
     */
    @RequestMapping("logout")
    @ResponseBody
    public Result logout(String userId, HttpServletRequest request) {
        Result result = new Result();
        try {
            request.getServletContext().removeAttribute("USER");
            result.setMsg("退出用户成功! ");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }


    /**
     * 用户登录
     * @param user
     * @param request
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public Result login(@RequestBody User user, HttpServletRequest request) {
        Result result = new Result();
        System.out.println("hello");
        log.info(user.getUsername() + "---" + user.getPassword());
        try {
            User userDB = userService.login(user);
            //因为前后端分离，所以登录信息不能存入session，最好存入redis，现在先存入 ServletContext应应急
            //用户id也传入前台，存入localStorage
            request.getServletContext().setAttribute("USER", userDB);
            result.setMsg("登录成功！").setUserId(userDB.getId());
        } catch (Exception e) {
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }


    /**
     * 用户注册
     * @param code
     * @param user
     * @return
     */
    @PostMapping("register")
    @ResponseBody
    public Result register(String code, String key, @RequestBody User user,HttpServletRequest request) {
        Result result = new Result();
        log.info("验证码："+ code);
        log.info("key:" + key);
        log.info("user对象：" + user);

        //验证验证码是否正确
        String keyCode = (String) request.getServletContext().getAttribute(key);

        //取到验证吗之后销毁该ServletContext对象
        request.getServletContext().removeAttribute(key);

        log.info(keyCode);
        try {
            if (code.equalsIgnoreCase(keyCode)) {
                // 存储用户
                userService.register(user);
                result.setMsg("注册成功，点击确定跳转到登录页面");
            } else {
                throw new RuntimeException("验证码错误！！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(e.getMessage()).setStatus(false);
        }
        return result;
    }

    /**
     * 生成验证码
     * @throws IOException
     */
    @GetMapping("getImage")
    @ResponseBody
    public Map<String, String> getImage(String oldKey, HttpServletRequest request) throws IOException {
        Map<String, String> result = new HashMap<>();
        CreateImageCode createImageCode = new CreateImageCode();
        //将老的图片key传过来，如果有的话就销毁
        if (oldKey != null) {
            request.getServletContext().removeAttribute(oldKey);
        }
        //生成验证码
        String checkCode = createImageCode.getCode();
        //验证码存入ServletContext
        String key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        request.getServletContext().setAttribute(key, checkCode);
        //生成验证码图片
        BufferedImage image = createImageCode.getBuffImg();
        //进行base64编码
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        String string = Base64Utils.encodeToString(bos.toByteArray());
        result.put("key", key);
        result.put("image", string);
        return result;
    }
}
