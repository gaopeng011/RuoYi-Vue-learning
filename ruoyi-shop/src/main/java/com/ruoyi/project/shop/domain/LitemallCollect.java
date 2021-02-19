package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 收藏对象 litemall_collect
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallCollect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户表的用户ID */
    @Excel(name = "用户表的用户ID")
    private Long userId;

    /** 如果type=0，则是商品ID；如果type=1，则是专题ID */
    @Excel(name = "如果type=0，则是商品ID；如果type=1，则是专题ID")
    private Long valueId;

    /** 收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID */
    @Excel(name = "收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID")
    private Integer type;

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
    public void setValueId(Long valueId)
    {
        this.valueId = valueId;
    }

    public Long getValueId()
    {
        return valueId;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getType()
    {
        return type;
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
            .append("valueId", getValueId())
            .append("type", getType())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
