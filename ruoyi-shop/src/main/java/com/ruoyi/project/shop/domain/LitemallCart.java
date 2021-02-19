package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 购物车商品对象 litemall_cart
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallCart extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户表的用户ID */
    @Excel(name = "用户表的用户ID")
    private Long userId;

    /** 商品表的商品ID */
    @Excel(name = "商品表的商品ID")
    private Long goodsId;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private String goodsSn;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品货品表的货品ID */
    @Excel(name = "商品货品表的货品ID")
    private Long productId;

    /** 商品货品的价格 */
    @Excel(name = "商品货品的价格")
    private BigDecimal price;

    /** 商品货品的数量 */
    @Excel(name = "商品货品的数量")
    private Integer number;

    /** 商品规格值列表，采用JSON数组格式 */
    @Excel(name = "商品规格值列表，采用JSON数组格式")
    private String specifications;

    /** 购物车中商品是否选择状态 */
    @Excel(name = "购物车中商品是否选择状态")
    private Integer checked;

    /** 商品图片或者商品货品图片 */
    @Excel(name = "商品图片或者商品货品图片")
    private String picUrl;

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
    public void setGoodsId(Long goodsId)
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId()
    {
        return goodsId;
    }
    public void setGoodsSn(String goodsSn)
    {
        this.goodsSn = goodsSn;
    }

    public String getGoodsSn()
    {
        return goodsSn;
    }
    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName()
    {
        return goodsName;
    }
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setNumber(Integer number)
    {
        this.number = number;
    }

    public Integer getNumber()
    {
        return number;
    }
    public void setSpecifications(String specifications)
    {
        this.specifications = specifications;
    }

    public String getSpecifications()
    {
        return specifications;
    }
    public void setChecked(Integer checked)
    {
        this.checked = checked;
    }

    public Integer getChecked()
    {
        return checked;
    }
    public void setPicUrl(String picUrl)
    {
        this.picUrl = picUrl;
    }

    public String getPicUrl()
    {
        return picUrl;
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
            .append("goodsId", getGoodsId())
            .append("goodsSn", getGoodsSn())
            .append("goodsName", getGoodsName())
            .append("productId", getProductId())
            .append("price", getPrice())
            .append("number", getNumber())
            .append("specifications", getSpecifications())
            .append("checked", getChecked())
            .append("picUrl", getPicUrl())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
