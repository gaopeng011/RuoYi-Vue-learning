package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 意见反馈对象 litemall_feedback
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallFeedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户表的用户ID */
    @Excel(name = "用户表的用户ID")
    private Long userId;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String username;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    /** 反馈类型 */
    @Excel(name = "反馈类型")
    private String feedType;

    /** 反馈内容 */
    @Excel(name = "反馈内容")
    private String content;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 是否含有图片 */
    @Excel(name = "是否含有图片")
    private Integer hasPicture;

    /** 图片地址列表，采用JSON数组格式 */
    @Excel(name = "图片地址列表，采用JSON数组格式")
    private String picUrls;

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
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getMobile()
    {
        return mobile;
    }
    public void setFeedType(String feedType)
    {
        this.feedType = feedType;
    }

    public String getFeedType()
    {
        return feedType;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setHasPicture(Integer hasPicture)
    {
        this.hasPicture = hasPicture;
    }

    public Integer getHasPicture()
    {
        return hasPicture;
    }
    public void setPicUrls(String picUrls)
    {
        this.picUrls = picUrls;
    }

    public String getPicUrls()
    {
        return picUrls;
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
            .append("userId", getUserId())
            .append("username", getUsername())
            .append("mobile", getMobile())
            .append("feedType", getFeedType())
            .append("content", getContent())
            .append("status", getStatus())
            .append("hasPicture", getHasPicture())
            .append("picUrls", getPicUrls())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
