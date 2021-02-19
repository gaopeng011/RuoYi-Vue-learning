package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 优惠券用户使用对象 litemall_coupon_user
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallCouponUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 优惠券ID */
    @Excel(name = "优惠券ID")
    private Long couponId;

    /** 使用状态, 如果是0则未使用；如果是1则已使用；如果是2则已过期；如果是3则已经下架； */
    @Excel(name = "使用状态, 如果是0则未使用；如果是1则已使用；如果是2则已过期；如果是3则已经下架；")
    private Integer status;

    /** 使用时间 */
    @Excel(name = "使用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date usedTime;

    /** 有效期开始时间 */
    @Excel(name = "有效期开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;



    /** 订单ID */
    @Excel(name = "订单ID")
    private Long orderId;

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
    public void setCouponId(Long couponId)
    {
        this.couponId = couponId;
    }

    public Long getCouponId()
    {
        return couponId;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setUsedTime(Date usedTime)
    {
        this.usedTime = usedTime;
    }

    public Date getUsedTime()
    {
        return usedTime;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId()
    {
        return orderId;
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
            .append("couponId", getCouponId())
            .append("status", getStatus())
            .append("usedTime", getUsedTime())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("orderId", getOrderId())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
