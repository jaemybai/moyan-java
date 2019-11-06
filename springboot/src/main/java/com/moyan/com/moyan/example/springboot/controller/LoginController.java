package com.moyan.com.moyan.example.springboot.controller;

import com.moyan.com.moyan.example.springboot.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public String login(User user) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getPassword()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return "没有权限";
        }
        return "login success";
    }

    @RequestMapping("/logout")
    @ResponseBody
    public String loginOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "logout success!";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello world!";
    }

    @RequestMapping("/error2")
    @ResponseBody
    public String error() {
        return "error page!";
    }

    //注解验角色和权限BasicErrorController
    @RequiresRoles("admin")
    @RequiresPermissions("add")
    @RequestMapping("/index")
    public String index() {
        return "index!";
    }
}