package com.wyw.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wyw
 * @date 2018\3\13 0013 11:09
 */
@Controller
public class UserInfoController {

    @RequestMapping(value = "/userInfoAdd")
    @RequiresPermissions(value = "userInfo:add")
    public String userAdd(){
        return "/userInfoAdd";
    }

    @RequestMapping(value = "/userInfoDel")
    @RequiresPermissions(value = "userInfo:del")
    public String userDel(){
        return "/userInfoDel";
    }

}
