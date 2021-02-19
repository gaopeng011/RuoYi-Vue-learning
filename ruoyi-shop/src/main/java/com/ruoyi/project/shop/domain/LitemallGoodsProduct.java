package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品货品对象 litemall_goods_product
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallGoodsProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 商品表的商品ID */
    @Excel(name = "商品表的商品ID")
    private Long goodsId;

    /** 商品规格值列表，采用JSON数组格式 */
    @Excel(name = "商品规格值列表，采用JSON数组格式")
    private String[] specifications;

    /** 商品货品价格 */
    @Excel(name = "商品货品价格")
    private BigDecimal price;

    /** 商品货品数量 */
    @Excel(name = "商品货品数量")
    private Long number;

    /** 商品货品图片 */
    @Excel(name = "商品货品图片")
    private String url;

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
    public void setGoodsId(Long goodsId)
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId()
    {
        return goodsId;
    }
    public void setSpecifications(String[] specifications)
    {
        this.specifications = specifications;
    }

    public String[] getSpecifications()
    {
        return specifications;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setNumber(Long number)
    {
        this.number = number;
    }

    public Long getNumber()
    {
        return number;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
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
            .append("goodsId", getGoodsId())
            .append("specifications", getSpecifications())
            .append("price", getPrice())
            .append("number", getNumber())
            .append("url", getUrl())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
