package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 关键字对象 litemall_keyword
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallKeyword extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 关键字 */
    @Excel(name = "关键字")
    private String keyword;

    /** 关键字的跳转链接 */
    @Excel(name = "关键字的跳转链接")
    private String url;

    /** 是否是热门关键字 */
    @Excel(name = "是否是热门关键字")
    private Integer isHot;

    /** 是否是默认关键字 */
    @Excel(name = "是否是默认关键字")
    private Integer isDefault;

    /** 排序 */
    @Excel(name = "排序")
    private Long sortOrder;

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
    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }

    public String getKeyword()
    {
        return keyword;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setIsHot(Integer isHot)
    {
        this.isHot = isHot;
    }

    public Integer getIsHot()
    {
        return isHot;
    }
    public void setIsDefault(Integer isDefault)
    {
        this.isDefault = isDefault;
    }

    public Integer getIsDefault()
    {
        return isDefault;
    }
    public void setSortOrder(Long sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public Long getSortOrder()
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("keyword", getKeyword())
            .append("url", getUrl())
            .append("isHot", getIsHot())
            .append("isDefault", getIsDefault())
            .append("sortOrder", getSortOrder())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
