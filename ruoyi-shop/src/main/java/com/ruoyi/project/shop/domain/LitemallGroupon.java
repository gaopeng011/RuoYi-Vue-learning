package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 团购活动对象 litemall_groupon
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallGroupon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 关联的订单ID */
    @Excel(name = "关联的订单ID")
    private Long orderId;

    /** 如果是开团用户，则groupon_id是0；如果是参团用户，则groupon_id是团购活动ID */
    @Excel(name = "如果是开团用户，则groupon_id是0；如果是参团用户，则groupon_id是团购活动ID")
    private Long grouponId;

    /** 团购规则ID，关联litemall_groupon_rules表ID字段 */
    @Excel(name = "团购规则ID，关联litemall_groupon_rules表ID字段")
    private Long rulesId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 团购分享图片地址 */
    @Excel(name = "团购分享图片地址")
    private String shareUrl;

    /** 开团用户ID */
    @Excel(name = "开团用户ID")
    private Long creatorUserId;

    /** 开团时间 */
    @Excel(name = "开团时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatorUserTime;

    /** 团购活动状态，开团未支付则0，开团中则1，开团失败则2 */
    @Excel(name = "团购活动状态，开团未支付则0，开团中则1，开团失败则2")
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
    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId()
    {
        return orderId;
    }
    public void setGrouponId(Long grouponId)
    {
        this.grouponId = grouponId;
    }

    public Long getGrouponId()
    {
        return grouponId;
    }
    public void setRulesId(Long rulesId)
    {
        this.rulesId = rulesId;
    }

    public Long getRulesId()
    {
        return rulesId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setShareUrl(String shareUrl)
    {
        this.shareUrl = shareUrl;
    }

    public String getShareUrl()
    {
        return shareUrl;
    }
    public void setCreatorUserId(Long creatorUserId)
    {
        this.creatorUserId = creatorUserId;
    }

    public Long getCreatorUserId()
    {
        return creatorUserId;
    }
    public void setCreatorUserTime(Date creatorUserTime)
    {
        this.creatorUserTime = creatorUserTime;
    }

    public Date getCreatorUserTime()
    {
        return creatorUserTime;
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
            .append("orderId", getOrderId())
            .append("grouponId", getGrouponId())
            .append("rulesId", getRulesId())
            .append("userId", getUserId())
            .append("shareUrl", getShareUrl())
            .append("creatorUserId", getCreatorUserId())
            .append("creatorUserTime", getCreatorUserTime())
            .append("status", getStatus())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
