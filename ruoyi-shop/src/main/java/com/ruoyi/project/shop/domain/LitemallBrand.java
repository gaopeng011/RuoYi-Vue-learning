package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 品牌商对象 litemall_brand
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallBrand extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 品牌商名称 */
    @Excel(name = "品牌商名称")
    private String name;

    /** 品牌商简介 */
    @Excel(name = "品牌商简介")
    private String desc;

    /** 品牌商页的品牌商图片 */
    @Excel(name = "品牌商页的品牌商图片")
    private String picUrl;

    /** $column.columnComment */
    @Excel(name = "品牌商页的品牌商图片")
    private Integer sortOrder;

    /** 品牌商的商品低价，仅用于页面展示 */
    @Excel(name = "品牌商的商品低价，仅用于页面展示")
    private Double floorPrice;

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
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getDesc()
    {
        return desc;
    }
    public void setPicUrl(String picUrl)
    {
        this.picUrl = picUrl;
    }

    public String getPicUrl()
    {
        return picUrl;
    }
    public void setSortOrder(Integer sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public Integer getSortOrder()
    {
        return sortOrder;
    }
    public void setFloorPrice(Double floorPrice)
    {
        this.floorPrice = floorPrice;
    }

    public Double getFloorPrice()
    {
        return floorPrice;
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
            .append("desc", getDesc())
            .append("picUrl", getPicUrl())
            .append("sortOrder", getSortOrder())
            .append("floorPrice", getFloorPrice())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
