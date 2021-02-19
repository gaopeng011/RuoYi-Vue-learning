package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallOrder;
import com.ruoyi.project.shop.mapper.LitemallOrderMapper;
import com.ruoyi.project.shop.service.ILitemallOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallOrderServiceImpl implements ILitemallOrderService 
{
    @Autowired
    private LitemallOrderMapper litemallOrderMapper;

    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    @Override
    public LitemallOrder selectLitemallOrderById(Long id)
    {
        return litemallOrderMapper.selectLitemallOrderById(id);
    }

    /**
     * 查询订单列表
     * 
     * @param litemallOrder 订单
     * @return 订单
     */
    @Override
    public List<LitemallOrder> selectLitemallOrderList(LitemallOrder litemallOrder)
    {
        return litemallOrderMapper.selectLitemallOrderList(litemallOrder);
    }

    /**
     * 新增订单
     * 
     * @param litemallOrder 订单
     * @return 结果
     */
    @Override
    public int insertLitemallOrder(LitemallOrder litemallOrder)
    {
        return litemallOrderMapper.insertLitemallOrder(litemallOrder);
    }

    /**
     * 修改订单
     * 
     * @param litemallOrder 订单
     * @return 结果
     */
    @Override
    public int updateLitemallOrder(LitemallOrder litemallOrder)
    {
        litemallOrder.setUpdateTime(DateUtils.getNowDate());
        return litemallOrderMapper.updateLitemallOrder(litemallOrder);
    }

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单ID
     * @return 结果
     */
    @Override
    public int deleteLitemallOrderByIds(Long[] ids)
    {
        return litemallOrderMapper.deleteLitemallOrderByIds(ids);
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    @Override
    public int deleteLitemallOrderById(Long id)
    {
        return litemallOrderMapper.deleteLitemallOrderById(id);
    }
}
