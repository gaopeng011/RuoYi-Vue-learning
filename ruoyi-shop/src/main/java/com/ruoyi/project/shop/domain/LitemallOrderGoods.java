package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 订单商品对象 litemall_order_goods
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallOrderGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 订单表的订单ID */
    @Excel(name = "订单表的订单ID")
    private Long orderId;

    /** 商品表的商品ID */
    @Excel(name = "商品表的商品ID")
    private Long goodsId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private String goodsSn;

    /** 商品货品表的货品ID */
    @Excel(name = "商品货品表的货品ID")
    private Long productId;

    /** 商品货品的购买数量 */
    @Excel(name = "商品货品的购买数量")
    private Integer number;

    /** 商品货品的售价 */
    @Excel(name = "商品货品的售价")
    private Double price;

    /** 商品货品的规格列表 */
    @Excel(name = "商品货品的规格列表")
    private String specifications;

    /** 商品货品图片或者商品图片 */
    @Excel(name = "商品货品图片或者商品图片")
    private String picUrl;

    /** 订单商品评论，如果是-1，则超期不能评价；如果是0，则可以评价；如果其他值，则是comment表里面的评论ID。 */
    @Excel(name = "订单商品评论，如果是-1，则超期不能评价；如果是0，则可以评价；如果其他值，则是comment表里面的评论ID。")
    private Long comment;

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
    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId()
    {
        return orderId;
    }
    public void setGoodsId(Long goodsId)
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId()
    {
        return goodsId;
    }
    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName()
    {
        return goodsName;
    }
    public void setGoodsSn(String goodsSn)
    {
        this.goodsSn = goodsSn;
    }

    public String getGoodsSn()
    {
        return goodsSn;
    }
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }
    public void setNumber(Integer number)
    {
        this.number = number;
    }

    public Integer getNumber()
    {
        return number;
    }
    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Double getPrice()
    {
        return price;
    }
    public void setSpecifications(String specifications)
    {
        this.specifications = specifications;
    }

    public String getSpecifications()
    {
        return specifications;
    }
    public void setPicUrl(String picUrl)
    {
        this.picUrl = picUrl;
    }

    public String getPicUrl()
    {
        return picUrl;
    }
    public void setComment(Long comment)
    {
        this.comment = comment;
    }

    public Long getComment()
    {
        return comment;
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
            .append("orderId", getOrderId())
            .append("goodsId", getGoodsId())
            .append("goodsName", getGoodsName())
            .append("goodsSn", getGoodsSn())
            .append("productId", getProductId())
            .append("number", getNumber())
            .append("price", getPrice())
            .append("specifications", getSpecifications())
            .append("picUrl", getPicUrl())
            .append("comment", getComment())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
