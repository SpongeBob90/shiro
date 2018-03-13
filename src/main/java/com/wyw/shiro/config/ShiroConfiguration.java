package com.wyw.shiro.config;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wyw
 * @date 2018\3\1 0001 14:03
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        //1.定义ShiroFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //2.设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //3.配置拦截器：使用LinkedHashMap配置，LinkedHashMap是有序的，shiro会根据添加的顺序进行拦截
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        //3.1配置退出过滤器：logout
        filterChainMap.put("/logout","logout");
        //3.7配置登陆后记住用户
        filterChainMap.put("/index","user");
        filterChainMap.put("/","user");
        //3.8允许匿名访问特定资源
        filterChainMap.put("/favicon.ico","anon");
        //3.2配置登陆认证：authc（所有URL都必须通过认证才可以访问）
        filterChainMap.put("/**","authc");
        //3.3设置默认登陆的URL
        shiroFilterFactoryBean.setLoginUrl("/login");
        //3.4设置成功之后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //3.5设置登陆失败页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        //3.6将规则绑定到过滤器
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        //4.返回ShiroFactoryBean
        return shiroFilterFactoryBean;
    }

    /**
     * 定义shiro的安全管理器
     */
    @Bean
    public SecurityManager securityManager(ShiroRealm shiroRealm, EhCacheManager ehCacheManager, CookieRememberMeManager cookieRememberMeManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        securityManager.setCacheManager(ehCacheManager);
        securityManager.setRememberMeManager(cookieRememberMeManager);
        return securityManager;
    }

    /**
     * 定义Realm
     */
    @Bean
    public ShiroRealm shiroRealm(CredentialsMatcher credentialsMatcher){
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(credentialsMatcher);
        return shiroRealm;
    }

    /**
     * 定义加密器
     */
    @Bean
    public CredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //设置散列次数
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * 开启Shiro的aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        //设置安全管理器
        attributeSourceAdvisor.setSecurityManager(securityManager);
        return attributeSourceAdvisor;
    }

    /**
     * 注入Ehcache缓存
     */
    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        //配置缓存文件
        ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return ehCacheManager;
    }

    @Bean
    public SimpleCookie rememberMeCookie(){
        //cookie的名称
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //配置cookie的生效时间，单位为秒。（可选）
        simpleCookie.setMaxAge(60*30);
        return simpleCookie;
    }

    /**
     * cookie的管理对象
     */
    @Bean
    public CookieRememberMeManager cookieRememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

}
