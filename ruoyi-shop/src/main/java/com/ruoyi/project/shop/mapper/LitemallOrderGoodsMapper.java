package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallOrderGoods;

import java.util.List;

/**
 * 订单商品Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallOrderGoodsMapper 
{
    /**
     * 查询订单商品
     * 
     * @param id 订单商品ID
     * @return 订单商品
     */
    public LitemallOrderGoods selectLitemallOrderGoodsById(Long id);

    /**
     * 查询订单商品列表
     * 
     * @param litemallOrderGoods 订单商品
     * @return 订单商品集合
     */
    public List<LitemallOrderGoods> selectLitemallOrderGoodsList(LitemallOrderGoods litemallOrderGoods);

    /**
     * 新增订单商品
     * 
     * @param litemallOrderGoods 订单商品
     * @return 结果
     */
    public int insertLitemallOrderGoods(LitemallOrderGoods litemallOrderGoods);

    /**
     * 修改订单商品
     * 
     * @param litemallOrderGoods 订单商品
     * @return 结果
     */
    public int updateLitemallOrderGoods(LitemallOrderGoods litemallOrderGoods);

    /**
     * 删除订单商品
     * 
     * @param id 订单商品ID
     * @return 结果
     */
    public int deleteLitemallOrderGoodsById(Long id);

    /**
     * 批量删除订单商品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallOrderGoodsByIds(Long[] ids);
}
