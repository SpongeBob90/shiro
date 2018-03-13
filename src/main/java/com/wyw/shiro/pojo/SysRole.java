package com.wyw.shiro.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 角色
 * @author wyw
 * @date 2018\3\2 0002 16:48
 */
@Entity
public class SysRole implements Serializable{
    private  static final long serialVersionUID = 1L;
    /**
     * 角色ID
     */
    @Id @GeneratedValue
    private long roleId;
    /**
     * 角色标识(如：admin|vip)
     */
    private String role;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 是否可用
     */
    private Boolean available = false;
    /**
     * “用户-角色”关系
     */
    @ManyToMany
    @JoinTable(name = "SysUserRole",joinColumns = {@JoinColumn(name = "roleId")},inverseJoinColumns = {@JoinColumn(name = "userId")})
    private List<UserInfo> userInfos;
    /**
     * “角色-权限”关系
     * fetch = FetchType.EAGER:当获取SysRole的时候，立即从数据库加载permission信息
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysRolePermission",joinColumns = {@JoinColumn(name = "roleId")},inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<SysPermission> permissions;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

}
