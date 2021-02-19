package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 系统配置对象 litemall_system
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallSystem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 系统配置名 */
    @Excel(name = "系统配置名")
    private String keyName;

    /** 系统配置值 */
    @Excel(name = "系统配置值")
    private String keyValue;

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
    public void setKeyName(String keyName)
    {
        this.keyName = keyName;
    }

    public String getKeyName()
    {
        return keyName;
    }
    public void setKeyValue(String keyValue)
    {
        this.keyValue = keyValue;
    }

    public String getKeyValue()
    {
        return keyValue;
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
            .append("keyName", getKeyName())
            .append("keyValue", getKeyValue())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
