package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 行政区域对象 litemall_region
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallRegion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0 */
    @Excel(name = "行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0")
    private Long pid;


    private Long parentId;
    /** 行政区域名称 */
    @Excel(name = "行政区域名称")
    private String name;

    /** 行政区域类型，如如1则是省， 如果是2则是市，如果是3则是区县 */
    @Excel(name = "行政区域类型，如如1则是省， 如果是2则是市，如果是3则是区县")
    private Integer type;

    /** 行政区域编码 */
    @Excel(name = "行政区域编码")
    private Long code;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setPid(Long pid)
    {
        this.pid = pid;
    }

    public Long getPid()
    {
        return pid;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getType()
    {
        return type;
    }
    public void setCode(Long code)
    {
        this.code = code;
    }

    public Long getCode()
    {
        return code;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pid", getPid())
            .append("name", getName())
            .append("type", getType())
            .append("code", getCode())
            .toString();
    }
}
