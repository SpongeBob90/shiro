package com.wyw.shiro.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author wyw
 * @date 2018\3\2 0002 17:05
 */
@Entity
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 权限ID
     */
    @Id @GeneratedValue
    private long permissionId;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 资源类型(menu|button)
     */
    @Column(columnDefinition = "enum('menu','button')")
    private String resouceType;
    /**
     * 资源路径
     */
    private String url;
    /**
     * 权限字符串
     * menu --> role:*
     * button --> role:create,role:update,role:delete,role:view
     */
    private String permission;
    /**
     * 父节点ID
     */
    private Long parentId;
    /**
     * 父节点编号列表
     */
    private String parentIds;
    /**
     * 是否可用
     */
    private Boolean available = false;
    /**
     * “权限-角色”关系：多对多
     */
    @ManyToMany
    @JoinTable(name = "SysRolePermission",joinColumns = {@JoinColumn(name = "permissionId")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roles;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(long permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResouceType() {
        return resouceType;
    }

    public void setResouceType(String resouceType) {
        this.resouceType = resouceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

}
