package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品基本信息对象 litemall_goods
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private String goodsSn;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    /** 商品所属类目ID */
    @Excel(name = "商品所属类目ID")
    private Long categoryId;

    /** $column.columnComment */
    @Excel(name = "商品所属类目ID")
    private Long brandId;

    /** 商品宣传图片列表，采用JSON数组格式 */
    @Excel(name = "商品宣传图片列表，采用JSON数组格式")
    private String[] gallery;

    /** 商品关键字，采用逗号间隔 */
    @Excel(name = "商品关键字，采用逗号间隔")
    private String keywords;

    /** 商品简介 */
    @Excel(name = "商品简介")
    private String brief;

    /** 是否上架 */
    @Excel(name = "是否上架")
    private Integer isOnSale;

    /** $column.columnComment */
    @Excel(name = "是否上架")
    private Integer sortOrder;

    /** 商品页面商品图片 */
    @Excel(name = "商品页面商品图片")
    private String picUrl;

    /** 商品分享朋友圈图片 */
    @Excel(name = "商品分享朋友圈图片")
    private String shareUrl;

    /** 是否新品首发，如果设置则可以在新品首发页面展示 */
    @Excel(name = "是否新品首发，如果设置则可以在新品首发页面展示")
    private Integer isNew;

    /** 是否人气推荐，如果设置则可以在人气推荐页面展示 */
    @Excel(name = "是否人气推荐，如果设置则可以在人气推荐页面展示")
    private Integer isHot;

    /** 商品单位，例如件、盒 */
    @Excel(name = "商品单位，例如件、盒")
    private String unit;

    /** 专柜价格 */
    @Excel(name = "专柜价格")
    private BigDecimal counterPrice;

    /** 零售价格 */
    @Excel(name = "零售价格")
    private BigDecimal retailPrice;

    /** 商品详细介绍，是富文本格式 */
    @Excel(name = "商品详细介绍，是富文本格式")
    private String detail;

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
    public void setGoodsSn(String goodsSn)
    {
        this.goodsSn = goodsSn;
    }

    public String getGoodsSn()
    {
        return goodsSn;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }
    public void setBrandId(Long brandId)
    {
        this.brandId = brandId;
    }

    public Long getBrandId()
    {
        return brandId;
    }

    public String[] getGallery() {
        return gallery;
    }

    public void setGallery(String[] gallery) {
        this.gallery = gallery;
    }

    public void setKeywords(String keywords)
    {
        this.keywords = keywords;
    }

    public String getKeywords()
    {
        return keywords;
    }
    public void setBrief(String brief)
    {
        this.brief = brief;
    }

    public String getBrief()
    {
        return brief;
    }
    public void setIsOnSale(Integer isOnSale)
    {
        this.isOnSale = isOnSale;
    }

    public Integer getIsOnSale()
    {
        return isOnSale;
    }
    public void setSortOrder(Integer sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public Integer getSortOrder()
    {
        return sortOrder;
    }
    public void setPicUrl(String picUrl)
    {
        this.picUrl = picUrl;
    }

    public String getPicUrl()
    {
        return picUrl;
    }
    public void setShareUrl(String shareUrl)
    {
        this.shareUrl = shareUrl;
    }

    public String getShareUrl()
    {
        return shareUrl;
    }
    public void setIsNew(Integer isNew)
    {
        this.isNew = isNew;
    }

    public Integer getIsNew()
    {
        return isNew;
    }
    public void setIsHot(Integer isHot)
    {
        this.isHot = isHot;
    }

    public Integer getIsHot()
    {
        return isHot;
    }
    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
    }
    public void setCounterPrice(BigDecimal counterPrice)
    {
        this.counterPrice = counterPrice;
    }

    public BigDecimal getCounterPrice()
    {
        return counterPrice;
    }
    public void setRetailPrice(BigDecimal retailPrice)
    {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getRetailPrice()
    {
        return retailPrice;
    }
    public void setDetail(String detail)
    {
        System.out.println(detail);
        this.detail = detail;
    }

    public String getDetail()
    {
        return detail;
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
            .append("goodsSn", getGoodsSn())
            .append("name", getName())
            .append("categoryId", getCategoryId())
            .append("brandId", getBrandId())
            .append("gallery", getGallery())
            .append("keywords", getKeywords())
            .append("brief", getBrief())
            .append("isOnSale", getIsOnSale())
            .append("sortOrder", getSortOrder())
            .append("picUrl", getPicUrl())
            .append("shareUrl", getShareUrl())
            .append("isNew", getIsNew())
            .append("isHot", getIsHot())
            .append("unit", getUnit())
            .append("counterPrice", getCounterPrice())
            .append("retailPrice", getRetailPrice())
            .append("detail", getDetail())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
