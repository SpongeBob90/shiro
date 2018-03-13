package com.wyw.shiro.config;

import com.wyw.shiro.pojo.SysPermission;
import com.wyw.shiro.pojo.SysRole;
import com.wyw.shiro.pojo.UserInfo;
import com.wyw.shiro.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wyw
 * @date 2018\3\5 0005 13:42
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    UserInfoService userInfoService;

    /**
     * 身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取用户输入的账号
        String userName = (String) authenticationToken.getPrincipal();
        //2.通过username从数据库中进行查找，获取UserInfo对象
        UserInfo userInfo = userInfoService.findByUserName(userName);
        if (userInfo == null) {
            return null;
        }
        //3.加密，使用SimpleAuthentication进行身份处理
        SimpleAuthenticationInfo simpleAuthenticationInfo
                = new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), ByteSource.Util.bytes( userInfo.getSalt()), userInfo.getName());
        //4.返回身份处理对象
        return simpleAuthenticationInfo;
    }

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.创建授权信息对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //2.获取用户的权限信息
        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();
        for (SysRole sysRole : userInfo.getRoles()){
            //2.1添加角色
            simpleAuthorizationInfo.addRole(sysRole.getRole());
            //2.2添加权限
            for (SysPermission sysPermission : sysRole.getPermissions()){
                simpleAuthorizationInfo.addStringPermission(sysPermission.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }

}
