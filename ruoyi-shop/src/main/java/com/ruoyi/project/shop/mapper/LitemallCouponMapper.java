package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallCoupon;

import java.util.List;

/**
 * 优惠券信息及规则Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallCouponMapper 
{
    /**
     * 查询优惠券信息及规则
     * 
     * @param id 优惠券信息及规则ID
     * @return 优惠券信息及规则
     */
    public LitemallCoupon selectLitemallCouponById(Long id);

    /**
     * 查询优惠券信息及规则列表
     * 
     * @param litemallCoupon 优惠券信息及规则
     * @return 优惠券信息及规则集合
     */
    public List<LitemallCoupon> selectLitemallCouponList(LitemallCoupon litemallCoupon);

    /**
     * 新增优惠券信息及规则
     * 
     * @param litemallCoupon 优惠券信息及规则
     * @return 结果
     */
    public int insertLitemallCoupon(LitemallCoupon litemallCoupon);

    /**
     * 修改优惠券信息及规则
     * 
     * @param litemallCoupon 优惠券信息及规则
     * @return 结果
     */
    public int updateLitemallCoupon(LitemallCoupon litemallCoupon);

    /**
     * 删除优惠券信息及规则
     * 
     * @param id 优惠券信息及规则ID
     * @return 结果
     */
    public int deleteLitemallCouponById(Long id);

    /**
     * 批量删除优惠券信息及规则
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallCouponByIds(Long[] ids);
}
