package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 订单对象 litemall_order
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户表的用户ID */
    @Excel(name = "用户表的用户ID")
    private Long userId;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderSn;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private Short orderStatus;

    /** 收货人名称 */
    @Excel(name = "收货人名称")
    private String consignee;

    /** 收货人手机号 */
    @Excel(name = "收货人手机号")
    private String mobile;

    /** 收货具体地址 */
    @Excel(name = "收货具体地址")
    private String address;

    /** 用户订单留言 */
    @Excel(name = "用户订单留言")
    private String message;

    /** 商品总费用 */
    @Excel(name = "商品总费用")
    private Double goodsPrice;

    /** 配送费用 */
    @Excel(name = "配送费用")
    private Double freightPrice;

    /** 优惠券减免 */
    @Excel(name = "优惠券减免")
    private Double couponPrice;

    /** 用户积分减免 */
    @Excel(name = "用户积分减免")
    private Double integralPrice;

    /** 团购优惠价减免 */
    @Excel(name = "团购优惠价减免")
    private Double grouponPrice;

    /** 订单费用， = goods_price + freight_price - coupon_price */
    @Excel(name = "订单费用， = goods_price + freight_price - coupon_price")
    private Double orderPrice;

    /** 实付费用， = order_price - integral_price */
    @Excel(name = "实付费用， = order_price - integral_price")
    private Double actualPrice;

    /** 微信付款编号 */
    @Excel(name = "微信付款编号")
    private String payId;

    /** 微信付款时间 */
    @Excel(name = "微信付款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payTime;

    /** 发货编号 */
    @Excel(name = "发货编号")
    private String shipSn;

    /** 发货快递公司 */
    @Excel(name = "发货快递公司")
    private String shipChannel;

    /** 发货开始时间 */
    @Excel(name = "发货开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shipTime;

    /** 实际退款金额，（有可能退款金额小于实际支付金额） */
    @Excel(name = "实际退款金额，", readConverterExp = "有=可能退款金额小于实际支付金额")
    private Double refundAmount;

    /** 退款方式 */
    @Excel(name = "退款方式")
    private String refundType;

    /** 退款备注 */
    @Excel(name = "退款备注")
    private String refundContent;

    /** 退款时间 */
    @Excel(name = "退款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date refundTime;

    /** 用户确认收货时间 */
    @Excel(name = "用户确认收货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date confirmTime;

    /** 待评价订单商品数量 */
    @Excel(name = "待评价订单商品数量")
    private Integer comments;

    /** 订单关闭时间 */
    @Excel(name = "订单关闭时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

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
    public void setOrderSn(String orderSn)
    {
        this.orderSn = orderSn;
    }

    public String getOrderSn()
    {
        return orderSn;
    }

    public Short getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Short orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setConsignee(String consignee)
    {
        this.consignee = consignee;
    }

    public String getConsignee()
    {
        return consignee;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getMobile()
    {
        return mobile;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
    public void setGoodsPrice(Double goodsPrice)
    {
        this.goodsPrice = goodsPrice;
    }

    public Double getGoodsPrice()
    {
        return goodsPrice;
    }
    public void setFreightPrice(Double freightPrice)
    {
        this.freightPrice = freightPrice;
    }

    public Double getFreightPrice()
    {
        return freightPrice;
    }
    public void setCouponPrice(Double couponPrice)
    {
        this.couponPrice = couponPrice;
    }

    public Double getCouponPrice()
    {
        return couponPrice;
    }
    public void setIntegralPrice(Double integralPrice)
    {
        this.integralPrice = integralPrice;
    }

    public Double getIntegralPrice()
    {
        return integralPrice;
    }
    public void setGrouponPrice(Double grouponPrice)
    {
        this.grouponPrice = grouponPrice;
    }

    public Double getGrouponPrice()
    {
        return grouponPrice;
    }
    public void setOrderPrice(Double orderPrice)
    {
        this.orderPrice = orderPrice;
    }

    public Double getOrderPrice()
    {
        return orderPrice;
    }
    public void setActualPrice(Double actualPrice)
    {
        this.actualPrice = actualPrice;
    }

    public Double getActualPrice()
    {
        return actualPrice;
    }
    public void setPayId(String payId)
    {
        this.payId = payId;
    }

    public String getPayId()
    {
        return payId;
    }
    public void setPayTime(Date payTime)
    {
        this.payTime = payTime;
    }

    public Date getPayTime()
    {
        return payTime;
    }
    public void setShipSn(String shipSn)
    {
        this.shipSn = shipSn;
    }

    public String getShipSn()
    {
        return shipSn;
    }
    public void setShipChannel(String shipChannel)
    {
        this.shipChannel = shipChannel;
    }

    public String getShipChannel()
    {
        return shipChannel;
    }
    public void setShipTime(Date shipTime)
    {
        this.shipTime = shipTime;
    }

    public Date getShipTime()
    {
        return shipTime;
    }
    public void setRefundAmount(Double refundAmount)
    {
        this.refundAmount = refundAmount;
    }

    public Double getRefundAmount()
    {
        return refundAmount;
    }
    public void setRefundType(String refundType)
    {
        this.refundType = refundType;
    }

    public String getRefundType()
    {
        return refundType;
    }
    public void setRefundContent(String refundContent)
    {
        this.refundContent = refundContent;
    }

    public String getRefundContent()
    {
        return refundContent;
    }
    public void setRefundTime(Date refundTime)
    {
        this.refundTime = refundTime;
    }

    public Date getRefundTime()
    {
        return refundTime;
    }
    public void setConfirmTime(Date confirmTime)
    {
        this.confirmTime = confirmTime;
    }

    public Date getConfirmTime()
    {
        return confirmTime;
    }
    public void setComments(Integer comments)
    {
        this.comments = comments;
    }

    public Integer getComments()
    {
        return comments;
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
            .append("orderSn", getOrderSn())
            .append("orderStatus", getOrderStatus())
            .append("consignee", getConsignee())
            .append("mobile", getMobile())
            .append("address", getAddress())
            .append("message", getMessage())
            .append("goodsPrice", getGoodsPrice())
            .append("freightPrice", getFreightPrice())
            .append("couponPrice", getCouponPrice())
            .append("integralPrice", getIntegralPrice())
            .append("grouponPrice", getGrouponPrice())
            .append("orderPrice", getOrderPrice())
            .append("actualPrice", getActualPrice())
            .append("payId", getPayId())
            .append("payTime", getPayTime())
            .append("shipSn", getShipSn())
            .append("shipChannel", getShipChannel())
            .append("shipTime", getShipTime())
            .append("refundAmount", getRefundAmount())
            .append("refundType", getRefundType())
            .append("refundContent", getRefundContent())
            .append("refundTime", getRefundTime())
            .append("confirmTime", getConfirmTime())
            .append("comments", getComments())
            .append("endTime", getEndTime())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
