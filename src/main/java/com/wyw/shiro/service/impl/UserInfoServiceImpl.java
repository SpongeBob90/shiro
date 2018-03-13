package com.wyw.shiro.service.impl;

import com.wyw.shiro.pojo.UserInfo;
import com.wyw.shiro.repository.UserInfoRepository;
import com.wyw.shiro.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wyw
 * @date 2018\3\5 0005 10:30
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 通过用户名查找用户信息
     *
     * @param name 用户名
     * @return 用户信息
     */
    @Override
    public UserInfo findByUserName(String name) {
        return userInfoRepository.findByUserName(name);
    }

}
