package com.wyw.shiro.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 * @author wyw
 * @date 2018\3\2 0002 15:41
 */
@Entity
public class UserInfo implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    @Id @GeneratedValue
    private long userId;
    /**
     * 用户名
     */
    private String name;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐：加强密码安全性
     */
    private String salt;
    /**
     * 用户状态：0-创建用户；1-正常；2-用户被锁定了
     */
    private byte state;
    /**
     * 关联信息的配置(用户-角色：多对多关系)
     * 1个用户可以有多个角色，1个角色可以被多个用户拥有
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole",joinColumns = {@JoinColumn(name = "userId")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        //使用"用户名+salt"进行加密，安全性更高
        return (userName + salt);
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

}
