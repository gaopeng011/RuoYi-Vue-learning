package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 团购规则对象 litemall_groupon_rules
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallGrouponRules extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 商品表的商品ID */
    @Excel(name = "商品表的商品ID")
    private Long goodsId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品图片或者商品货品图片 */
    @Excel(name = "商品图片或者商品货品图片")
    private String picUrl;

    /** 优惠金额 */
    @Excel(name = "优惠金额")
    private BigDecimal discount;

    /** 达到优惠条件的人数 */
    @Excel(name = "达到优惠条件的人数")
    private Long discountMember;

    /** 团购过期时间 */
    @Excel(name = "团购过期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime expireTime;

    /** 团购规则状态，正常上线则0，到期自动下线则1，管理手动下线则2 */
    @Excel(name = "团购规则状态，正常上线则0，到期自动下线则1，管理手动下线则2")
    private Short status;

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
    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName()
    {
        return goodsName;
    }
    public void setPicUrl(String picUrl)
    {
        this.picUrl = picUrl;
    }

    public String getPicUrl()
    {
        return picUrl;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setDiscountMember(Long discountMember)
    {
        this.discountMember = discountMember;
    }

    public Long getDiscountMember()
    {
        return discountMember;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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
            .append("goodsName", getGoodsName())
            .append("picUrl", getPicUrl())
            .append("discount", getDiscount())
            .append("discountMember", getDiscountMember())
            .append("expireTime", getExpireTime())
            .append("status", getStatus())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
