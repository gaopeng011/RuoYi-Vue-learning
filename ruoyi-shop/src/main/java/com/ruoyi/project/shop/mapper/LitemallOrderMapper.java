package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallOrder;

import java.util.List;

/**
 * 订单Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallOrderMapper 
{
    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    public LitemallOrder selectLitemallOrderById(Long id);

    /**
     * 查询订单列表
     * 
     * @param litemallOrder 订单
     * @return 订单集合
     */
    public List<LitemallOrder> selectLitemallOrderList(LitemallOrder litemallOrder);

    /**
     * 新增订单
     * 
     * @param litemallOrder 订单
     * @return 结果
     */
    public int insertLitemallOrder(LitemallOrder litemallOrder);

    /**
     * 修改订单
     * 
     * @param litemallOrder 订单
     * @return 结果
     */
    public int updateLitemallOrder(LitemallOrder litemallOrder);

    /**
     * 删除订单
     * 
     * @param id 订单ID
     * @return 结果
     */
    public int deleteLitemallOrderById(Long id);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallOrderByIds(Long[] ids);
}
