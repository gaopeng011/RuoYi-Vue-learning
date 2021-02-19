package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 类目对象 litemall_category
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 类目名称 */
    @Excel(name = "类目名称")
    private String name;

    /** 类目关键字，以JSON数组格式 */
    @Excel(name = "类目关键字，以JSON数组格式")
    private String keywords;

    /** 类目广告语介绍 */
    @Excel(name = "类目广告语介绍")
    private String desc;

    /** 父类目ID */
    @Excel(name = "父类目ID")
    private Long pid;


    private Long parentId;

    /** 类目图标 */
    @Excel(name = "类目图标")
    private String iconUrl;

    /** 类目图片 */
    @Excel(name = "类目图片")
    private String picUrl;

    /** $column.columnComment */
    @Excel(name = "类目图片")
    private String level;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sortOrder;

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
    public void setKeywords(String keywords)
    {
        this.keywords = keywords;
    }

    public String getKeywords()
    {
        return keywords;
    }
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getDesc()
    {
        return desc;
    }
    public void setPid(Long pid)
    {
        this.pid = pid;
    }

    public Long getPid()
    {
        return pid;
    }
    public void setIconUrl(String iconUrl)
    {
        this.iconUrl = iconUrl;
    }

    public String getIconUrl()
    {
        return iconUrl;
    }
    public void setPicUrl(String picUrl)
    {
        this.picUrl = picUrl;
    }

    public String getPicUrl()
    {
        return picUrl;
    }
    public void setLevel(String level)
    {
        this.level = level;
    }

    public String getLevel()
    {
        return level;
    }
    public void setSortOrder(Integer sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public Integer getSortOrder()
    {
        return sortOrder;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("keywords", getKeywords())
            .append("desc", getDesc())
            .append("pid", getPid())
            .append("iconUrl", getIconUrl())
            .append("picUrl", getPicUrl())
            .append("level", getLevel())
            .append("sortOrder", getSortOrder())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
