package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallCouponUser;

import java.util.List;

/**
 * 优惠券用户使用Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallCouponUserMapper 
{
    /**
     * 查询优惠券用户使用
     * 
     * @param id 优惠券用户使用ID
     * @return 优惠券用户使用
     */
    public LitemallCouponUser selectLitemallCouponUserById(Long id);

    /**
     * 查询优惠券用户使用列表
     * 
     * @param litemallCouponUser 优惠券用户使用
     * @return 优惠券用户使用集合
     */
    public List<LitemallCouponUser> selectLitemallCouponUserList(LitemallCouponUser litemallCouponUser);

    /**
     * 新增优惠券用户使用
     * 
     * @param litemallCouponUser 优惠券用户使用
     * @return 结果
     */
    public int insertLitemallCouponUser(LitemallCouponUser litemallCouponUser);

    /**
     * 修改优惠券用户使用
     * 
     * @param litemallCouponUser 优惠券用户使用
     * @return 结果
     */
    public int updateLitemallCouponUser(LitemallCouponUser litemallCouponUser);

    /**
     * 删除优惠券用户使用
     * 
     * @param id 优惠券用户使用ID
     * @return 结果
     */
    public int deleteLitemallCouponUserById(Long id);

    /**
     * 批量删除优惠券用户使用
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallCouponUserByIds(Long[] ids);
}
