package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 管理员对象 litemall_admin
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallAdmin extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 管理员名称 */
    @Excel(name = "管理员名称")
    private String username;

    /** 管理员密码 */
    @Excel(name = "管理员密码")
    private String password;

    /** 最近一次登录IP地址 */
    @Excel(name = "最近一次登录IP地址")
    private String lastLoginIp;

    /** 最近一次登录时间 */
    @Excel(name = "最近一次登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLoginTime;

    /** 头像图片 */
    @Excel(name = "头像图片")
    private String avatar;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 逻辑删除 */
    @Excel(name = "逻辑删除")
    private Integer deleted;

    /** 角色列表 */
    @Excel(name = "角色列表")
    private String roleIds;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
    public void setLastLoginIp(String lastLoginIp)
    {
        this.lastLoginIp = lastLoginIp;
    }

    public String getLastLoginIp()
    {
        return lastLoginIp;
    }
    public void setLastLoginTime(Date lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLoginTime()
    {
        return lastLoginTime;
    }
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getAvatar()
    {
        return avatar;
    }
    public void setAddTime(Date addTime)
    {
        this.addTime = addTime;
    }

    public Date getAddTime()
    {
        return addTime;
    }
    public void setDeleted(Integer deleted)
    {
        this.deleted = deleted;
    }

    public Integer getDeleted()
    {
        return deleted;
    }
    public void setRoleIds(String roleIds)
    {
        this.roleIds = roleIds;
    }

    public String getRoleIds()
    {
        return roleIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("lastLoginIp", getLastLoginIp())
            .append("lastLoginTime", getLastLoginTime())
            .append("avatar", getAvatar())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .append("roleIds", getRoleIds())
            .toString();
    }
}
