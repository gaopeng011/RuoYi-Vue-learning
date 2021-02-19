package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 专题对象 litemall_topic
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallTopic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 专题标题 */
    @Excel(name = "专题标题")
    private String title;

    /** 专题子标题 */
    @Excel(name = "专题子标题")
    private String subtitle;

    /** 专题内容，富文本格式 */
    @Excel(name = "专题内容，富文本格式")
    private String content;

    /** 专题相关商品最低价 */
    @Excel(name = "专题相关商品最低价")
    private Double price;

    /** 专题阅读量 */
    @Excel(name = "专题阅读量")
    private String readCount;

    /** 专题图片 */
    @Excel(name = "专题图片")
    private String picUrl;

    /** 排序 */
    @Excel(name = "排序")
    private Long sortOrder;

    /** 专题相关商品，采用JSON数组格式 */
    @Excel(name = "专题相关商品，采用JSON数组格式")
    private Integer[] goods;

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
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setSubtitle(String subtitle)
    {
        this.subtitle = subtitle;
    }

    public String getSubtitle()
    {
        return subtitle;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Double getPrice()
    {
        return price;
    }
    public void setReadCount(String readCount)
    {
        this.readCount = readCount;
    }

    public String getReadCount()
    {
        return readCount;
    }
    public void setPicUrl(String picUrl)
    {
        this.picUrl = picUrl;
    }

    public String getPicUrl()
    {
        return picUrl;
    }
    public void setSortOrder(Long sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public Long getSortOrder()
    {
        return sortOrder;
    }

    public Integer[] getGoods() {
        return goods;
    }

    public void setGoods(Integer[] goods) {
        this.goods = goods;
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
            .append("title", getTitle())
            .append("subtitle", getSubtitle())
            .append("content", getContent())
            .append("price", getPrice())
            .append("readCount", getReadCount())
            .append("picUrl", getPicUrl())
            .append("sortOrder", getSortOrder())
            .append("goods", getGoods())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
