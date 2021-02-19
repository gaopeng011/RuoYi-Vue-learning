package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 优惠券信息及规则对象 litemall_coupon
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallCoupon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 优惠券名称 */
    @Excel(name = "优惠券名称")
    private String name;

    /** 优惠券介绍，通常是显示优惠券使用限制文字 */
    @Excel(name = "优惠券介绍，通常是显示优惠券使用限制文字")
    private String desc;

    /** 优惠券标签，例如新人专用 */
    @Excel(name = "优惠券标签，例如新人专用")
    private String tag;

    /** 优惠券数量，如果是0，则是无限量 */
    @Excel(name = "优惠券数量，如果是0，则是无限量")
    private Long total;

    /** 优惠金额， */
    @Excel(name = "优惠金额，")
    private Double discount;

    /** 最少消费金额才能使用优惠券。 */
    @Excel(name = "最少消费金额才能使用优惠券。")
    private Double min;

    /** 用户领券限制数量，如果是0，则是不限制；默认是1，限领一张. */
    @Excel(name = "用户领券限制数量，如果是0，则是不限制；默认是1，限领一张.")
    private Integer limit;

    /** 优惠券赠送类型，如果是0则通用券，用户领取；如果是1，则是注册赠券；如果是2，则是优惠券码兑换； */
    @Excel(name = "优惠券赠送类型，如果是0则通用券，用户领取；如果是1，则是注册赠券；如果是2，则是优惠券码兑换；")
    private Integer type;

    /** 优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架。 */
    @Excel(name = "优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架。")
    private Integer status;

    /** 商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。 */
    @Excel(name = "商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。")
    private Integer goodsType;

    /** 商品限制值，goods_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。 */
    @Excel(name = "商品限制值，goods_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。")
    private String goodsValue;

    /** 优惠券兑换码 */
    @Excel(name = "优惠券兑换码")
    private String code;

    /** 有效时间限制，如果是0，则基于领取时间的有效天数days；如果是1，则start_time和end_time是优惠券有效期； */
    @Excel(name = "有效时间限制，如果是0，则基于领取时间的有效天数days；如果是1，则start_time和end_time是优惠券有效期；")
    private Integer timeType;

    /** 基于领取时间的有效天数days。 */
    @Excel(name = "基于领取时间的有效天数days。")
    private Integer days;

    /** 使用券开始时间 */
    @Excel(name = "使用券开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;


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
    public void setTag(String tag)
    {
        this.tag = tag;
    }

    public String getTag()
    {
        return tag;
    }
    public void setTotal(Long total)
    {
        this.total = total;
    }

    public Long getTotal()
    {
        return total;
    }
    public void setDiscount(Double discount)
    {
        this.discount = discount;
    }

    public Double getDiscount()
    {
        return discount;
    }
    public void setMin(Double min)
    {
        this.min = min;
    }

    public Double getMin()
    {
        return min;
    }
    public void setLimit(Integer limit)
    {
        this.limit = limit;
    }

    public Integer getLimit()
    {
        return limit;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getType()
    {
        return type;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setGoodsType(Integer goodsType)
    {
        this.goodsType = goodsType;
    }

    public Integer getGoodsType()
    {
        return goodsType;
    }
    public void setGoodsValue(String goodsValue)
    {
        this.goodsValue = goodsValue;
    }

    public String getGoodsValue()
    {
        return goodsValue;
    }
    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }
    public void setTimeType(Integer timeType)
    {
        this.timeType = timeType;
    }

    public Integer getTimeType()
    {
        return timeType;
    }
    public void setDays(Integer days)
    {
        this.days = days;
    }

    public Integer getDays()
    {
        return days;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
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
            .append("tag", getTag())
            .append("total", getTotal())
            .append("discount", getDiscount())
            .append("min", getMin())
            .append("limit", getLimit())
            .append("type", getType())
            .append("status", getStatus())
            .append("goodsType", getGoodsType())
            .append("goodsValue", getGoodsValue())
            .append("code", getCode())
            .append("timeType", getTimeType())
            .append("days", getDays())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
