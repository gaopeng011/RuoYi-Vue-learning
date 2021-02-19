package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallCouponUser;
import com.ruoyi.project.shop.mapper.LitemallCouponUserMapper;
import com.ruoyi.project.shop.service.ILitemallCouponUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优惠券用户使用Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallCouponUserServiceImpl implements ILitemallCouponUserService 
{
    @Autowired
    private LitemallCouponUserMapper litemallCouponUserMapper;

    /**
     * 查询优惠券用户使用
     * 
     * @param id 优惠券用户使用ID
     * @return 优惠券用户使用
     */
    @Override
    public LitemallCouponUser selectLitemallCouponUserById(Long id)
    {
        return litemallCouponUserMapper.selectLitemallCouponUserById(id);
    }

    /**
     * 查询优惠券用户使用列表
     * 
     * @param litemallCouponUser 优惠券用户使用
     * @return 优惠券用户使用
     */
    @Override
    public List<LitemallCouponUser> selectLitemallCouponUserList(LitemallCouponUser litemallCouponUser)
    {
        return litemallCouponUserMapper.selectLitemallCouponUserList(litemallCouponUser);
    }

    /**
     * 新增优惠券用户使用
     * 
     * @param litemallCouponUser 优惠券用户使用
     * @return 结果
     */
    @Override
    public int insertLitemallCouponUser(LitemallCouponUser litemallCouponUser)
    {
        return litemallCouponUserMapper.insertLitemallCouponUser(litemallCouponUser);
    }

    /**
     * 修改优惠券用户使用
     * 
     * @param litemallCouponUser 优惠券用户使用
     * @return 结果
     */
    @Override
    public int updateLitemallCouponUser(LitemallCouponUser litemallCouponUser)
    {
        litemallCouponUser.setUpdateTime(DateUtils.getNowDate());
        return litemallCouponUserMapper.updateLitemallCouponUser(litemallCouponUser);
    }

    /**
     * 批量删除优惠券用户使用
     * 
     * @param ids 需要删除的优惠券用户使用ID
     * @return 结果
     */
    @Override
    public int deleteLitemallCouponUserByIds(Long[] ids)
    {
        return litemallCouponUserMapper.deleteLitemallCouponUserByIds(ids);
    }

    /**
     * 删除优惠券用户使用信息
     * 
     * @param id 优惠券用户使用ID
     * @return 结果
     */
    @Override
    public int deleteLitemallCouponUserById(Long id)
    {
        return litemallCouponUserMapper.deleteLitemallCouponUserById(id);
    }
}
