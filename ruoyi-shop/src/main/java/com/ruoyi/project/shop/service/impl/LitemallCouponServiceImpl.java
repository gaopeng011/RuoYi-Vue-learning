package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallCoupon;
import com.ruoyi.project.shop.mapper.LitemallCouponMapper;
import com.ruoyi.project.shop.service.ILitemallCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优惠券信息及规则Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallCouponServiceImpl implements ILitemallCouponService 
{
    @Autowired
    private LitemallCouponMapper litemallCouponMapper;

    /**
     * 查询优惠券信息及规则
     * 
     * @param id 优惠券信息及规则ID
     * @return 优惠券信息及规则
     */
    @Override
    public LitemallCoupon selectLitemallCouponById(Long id)
    {
        return litemallCouponMapper.selectLitemallCouponById(id);
    }

    /**
     * 查询优惠券信息及规则列表
     * 
     * @param litemallCoupon 优惠券信息及规则
     * @return 优惠券信息及规则
     */
    @Override
    public List<LitemallCoupon> selectLitemallCouponList(LitemallCoupon litemallCoupon)
    {
        return litemallCouponMapper.selectLitemallCouponList(litemallCoupon);
    }

    /**
     * 新增优惠券信息及规则
     * 
     * @param litemallCoupon 优惠券信息及规则
     * @return 结果
     */
    @Override
    public int insertLitemallCoupon(LitemallCoupon litemallCoupon)
    {
        return litemallCouponMapper.insertLitemallCoupon(litemallCoupon);
    }

    /**
     * 修改优惠券信息及规则
     * 
     * @param litemallCoupon 优惠券信息及规则
     * @return 结果
     */
    @Override
    public int updateLitemallCoupon(LitemallCoupon litemallCoupon)
    {
        litemallCoupon.setUpdateTime(DateUtils.getNowDate());
        return litemallCouponMapper.updateLitemallCoupon(litemallCoupon);
    }

    /**
     * 批量删除优惠券信息及规则
     * 
     * @param ids 需要删除的优惠券信息及规则ID
     * @return 结果
     */
    @Override
    public int deleteLitemallCouponByIds(Long[] ids)
    {
        return litemallCouponMapper.deleteLitemallCouponByIds(ids);
    }

    /**
     * 删除优惠券信息及规则信息
     * 
     * @param id 优惠券信息及规则ID
     * @return 结果
     */
    @Override
    public int deleteLitemallCouponById(Long id)
    {
        return litemallCouponMapper.deleteLitemallCouponById(id);
    }
}
