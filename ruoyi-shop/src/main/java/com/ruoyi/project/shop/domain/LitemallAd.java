package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 广告对象 litemall_ad
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallAd extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 广告标题 */
    @Excel(name = "广告标题")
    private String name;

    /** 所广告的商品页面或者活动页面链接地址 */
    @Excel(name = "所广告的商品页面或者活动页面链接地址")
    private String link;

    /** 广告宣传图片 */
    @Excel(name = "广告宣传图片")
    private String url;

    /** 广告位置：1则是首页 */
    @Excel(name = "广告位置：1则是首页")
    private Integer position;

    /** 活动内容 */
    @Excel(name = "活动内容")
    private String content;

    /** 广告开始时间 */
    @Excel(name = "广告开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;



    /** 是否启动 */
    @Excel(name = "是否启动")
    private Integer enabled;

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
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setLink(String link)
    {
        this.link = link;
    }

    public String getLink()
    {
        return link;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setPosition(Integer position)
    {
        this.position = position;
    }

    public Integer getPosition()
    {
        return position;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }


    public Integer getEnabled()
    {
        return enabled;
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
            .append("name", getName())
            .append("link", getLink())
            .append("url", getUrl())
            .append("position", getPosition())
            .append("content", getContent())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("enabled", getEnabled())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
