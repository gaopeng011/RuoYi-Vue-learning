package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 评论对象 litemall_comment
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 如果type=0，则是商品评论；如果是type=1，则是专题评论。 */
    @Excel(name = "如果type=0，则是商品评论；如果是type=1，则是专题评论。")
    private Long valueId;

    /** 评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；如果type=3，则是订单商品评论。 */
    @Excel(name = "评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；如果type=3，则是订单商品评论。")
    private Integer type;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;

    /** 用户表的用户ID */
    @Excel(name = "用户表的用户ID")
    private Long userId;

    /** 是否含有图片 */
    @Excel(name = "是否含有图片")
    private Integer hasPicture;

    /** 图片地址列表，采用JSON数组格式 */
    @Excel(name = "图片地址列表，采用JSON数组格式")
    private String[] picUrls;

    /** 评分， 1-5 */
    @Excel(name = "评分， 1-5")
    private Integer star;

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
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setHasPicture(Integer hasPicture)
    {
        this.hasPicture = hasPicture;
    }

    public Integer getHasPicture()
    {
        return hasPicture;
    }
    public void setPicUrls(String[] picUrls)
    {
        this.picUrls = picUrls;
    }

    public String[] getPicUrls()
    {
        return picUrls;
    }
    public void setStar(Integer star)
    {
        this.star = star;
    }

    public Integer getStar()
    {
        return star;
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
            .append("valueId", getValueId())
            .append("type", getType())
            .append("content", getContent())
            .append("userId", getUserId())
            .append("hasPicture", getHasPicture())
            .append("picUrls", getPicUrls())
            .append("star", getStar())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
