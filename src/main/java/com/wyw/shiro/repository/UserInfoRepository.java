package com.wyw.shiro.repository;

import com.wyw.shiro.pojo.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wyw
 * @date 2018\3\5 0005 10:21
 */
@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo,Long> {

    /**
     * 通过用户名查找用户信息
     * @param name 用户名
     * @return 用户信息
     */
    UserInfo findByUserName(String name);

}
