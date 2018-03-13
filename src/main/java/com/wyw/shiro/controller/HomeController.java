package com.wyw.shiro.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.omg.CORBA.UnknownUserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wyw
 * @date 2018\3\1 0001 13:51
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/index")
    public String index(){
        return "/index";
    }

    @RequestMapping(value = {"/", "/login"})
    public String login(HttpServletRequest request, Map<String, Object> map){
        //1.从shiro中获取登陆异常信息
        String exception = (String) request.getAttribute("shiroLoginFailure");
        //2.异常信息
        String msg = "";
        //3.登陆异常
        if (exception != null){
            if (UnknownAccountException.class.getName().equals(exception)){
                msg = "用户不存在";
            } else if(IncorrectCredentialsException.class.getName().equals(exception)){
                msg = "密码错误";
            } else{
                msg = exception;
            }
        }
        map.put("msg", msg);
        return "/login";
    }

}
