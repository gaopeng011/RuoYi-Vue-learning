package com.ruoyi.project.shop.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 收货地址对象 litemall_address
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public class LitemallAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 收货人名称 */
    @Excel(name = "收货人名称")
    private String name;

    /** 用户表的用户ID */
    @Excel(name = "用户表的用户ID")
    private Long userId;

    /** 行政区域表的省ID */
    @Excel(name = "行政区域表的省ID")
    private String province;

    private String provinceName;

    private String cityName;

    private String countyName;

    private String addressName;

    /** 行政区域表的市ID */
    @Excel(name = "行政区域表的市ID")
    private String city;

    /** 行政区域表的区县ID */
    @Excel(name = "行政区域表的区县ID")
    private String county;

    /** 详细收货地址 */
    @Excel(name = "详细收货地址")
    private String addressDetail;

    /** 地区编码 */
    @Excel(name = "地区编码")
    private String areaCode;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String postalCode;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String tel;

    /** 是否默认地址 */
    @Excel(name = "是否默认地址")
    private Integer isDefault;

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
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getProvince()
    {
        return province;
    }
    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCity()
    {
        return city;
    }
    public void setCounty(String county)
    {
        this.county = county;
    }

    public String getCounty()
    {
        return county;
    }
    public void setAddressDetail(String addressDetail)
    {
        this.addressDetail = addressDetail;
    }

    public String getAddressDetail()
    {
        return addressDetail;
    }
    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }

    public String getAreaCode()
    {
        return areaCode;
    }
    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getPostalCode()
    {
        return postalCode;
    }
    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public String getTel()
    {
        return tel;
    }
    public void setIsDefault(Integer isDefault)
    {
        this.isDefault = isDefault;
    }

    public Integer getIsDefault()
    {
        return isDefault;
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

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("userId", getUserId())
            .append("province", getProvince())
            .append("city", getCity())
            .append("county", getCounty())
            .append("addressDetail", getAddressDetail())
            .append("areaCode", getAreaCode())
            .append("postalCode", getPostalCode())
            .append("tel", getTel())
            .append("isDefault", getIsDefault())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
