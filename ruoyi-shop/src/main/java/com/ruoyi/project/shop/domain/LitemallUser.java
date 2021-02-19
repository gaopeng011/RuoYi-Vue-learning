package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 用户对象 litemall_user
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String username;

    /** 用户密码 */
    @Excel(name = "用户密码")
    private String password;

    /** 性别：0 未知， 1男， 1 女 */
    @Excel(name = "性别：0 未知， 1男， 1 女")
    private Integer gender;

    /** 生日 */
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 最近一次登录时间 */
    @Excel(name = "最近一次登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLoginTime;

    /** 最近一次登录IP地址 */
    @Excel(name = "最近一次登录IP地址")
    private String lastLoginIp;

    /** 0 普通用户，1 VIP用户，2 高级VIP用户 */
    @Excel(name = "0 普通用户，1 VIP用户，2 高级VIP用户")
    private Integer userLevel;

    /** 用户昵称或网络名称 */
    @Excel(name = "用户昵称或网络名称")
    private String nickname;

    /** 用户手机号码 */
    @Excel(name = "用户手机号码")
    private String mobile;

    /** 用户头像图片 */
    @Excel(name = "用户头像图片")
    private String avatar;

    /** 微信登录openid */
    @Excel(name = "微信登录openid")
    private String weixinOpenid;

    /** 微信登录会话KEY */
    @Excel(name = "微信登录会话KEY")
    private String sessionKey;

    /** 0 可用, 1 禁用, 2 注销 */
    @Excel(name = "0 可用, 1 禁用, 2 注销")
    private Integer status;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 逻辑删除 */
    @Excel(name = "逻辑删除")
    private Integer deleted;

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
    public void setGender(Integer gender)
    {
        this.gender = gender;
    }

    public Integer getGender()
    {
        return gender;
    }
    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public Date getBirthday()
    {
        return birthday;
    }
    public void setLastLoginTime(Date lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLoginTime()
    {
        return lastLoginTime;
    }
    public void setLastLoginIp(String lastLoginIp)
    {
        this.lastLoginIp = lastLoginIp;
    }

    public String getLastLoginIp()
    {
        return lastLoginIp;
    }
    public void setUserLevel(Integer userLevel)
    {
        this.userLevel = userLevel;
    }

    public Integer getUserLevel()
    {
        return userLevel;
    }
    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getNickname()
    {
        return nickname;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getMobile()
    {
        return mobile;
    }
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getAvatar()
    {
        return avatar;
    }
    public void setWeixinOpenid(String weixinOpenid)
    {
        this.weixinOpenid = weixinOpenid;
    }

    public String getWeixinOpenid()
    {
        return weixinOpenid;
    }
    public void setSessionKey(String sessionKey)
    {
        this.sessionKey = sessionKey;
    }

    public String getSessionKey()
    {
        return sessionKey;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("gender", getGender())
            .append("birthday", getBirthday())
            .append("lastLoginTime", getLastLoginTime())
            .append("lastLoginIp", getLastLoginIp())
            .append("userLevel", getUserLevel())
            .append("nickname", getNickname())
            .append("mobile", getMobile())
            .append("avatar", getAvatar())
            .append("weixinOpenid", getWeixinOpenid())
            .append("sessionKey", getSessionKey())
            .append("status", getStatus())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
