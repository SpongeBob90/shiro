package com.wyw.shiro.service;

import com.wyw.shiro.pojo.UserInfo;

/**
 * @author wyw
 * @date 2018\3\5 0005 10:29
 */
public interface UserInfoService {

    /**
     * 通过用户名查找用户信息
     * @param name 用户名
     * @return 用户信息
     */
    UserInfo findByUserName(String name);

}
