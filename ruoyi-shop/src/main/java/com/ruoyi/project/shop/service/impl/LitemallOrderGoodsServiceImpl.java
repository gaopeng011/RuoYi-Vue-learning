package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallOrderGoods;
import com.ruoyi.project.shop.mapper.LitemallOrderGoodsMapper;
import com.ruoyi.project.shop.service.ILitemallOrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单商品Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallOrderGoodsServiceImpl implements ILitemallOrderGoodsService 
{
    @Autowired
    private LitemallOrderGoodsMapper litemallOrderGoodsMapper;

    /**
     * 查询订单商品
     * 
     * @param id 订单商品ID
     * @return 订单商品
     */
    @Override
    public LitemallOrderGoods selectLitemallOrderGoodsById(Long id)
    {
        return litemallOrderGoodsMapper.selectLitemallOrderGoodsById(id);
    }

    /**
     * 查询订单商品列表
     * 
     * @param litemallOrderGoods 订单商品
     * @return 订单商品
     */
    @Override
    public List<LitemallOrderGoods> selectLitemallOrderGoodsList(LitemallOrderGoods litemallOrderGoods)
    {
        return litemallOrderGoodsMapper.selectLitemallOrderGoodsList(litemallOrderGoods);
    }

    /**
     * 新增订单商品
     * 
     * @param litemallOrderGoods 订单商品
     * @return 结果
     */
    @Override
    public int insertLitemallOrderGoods(LitemallOrderGoods litemallOrderGoods)
    {
        return litemallOrderGoodsMapper.insertLitemallOrderGoods(litemallOrderGoods);
    }

    /**
     * 修改订单商品
     * 
     * @param litemallOrderGoods 订单商品
     * @return 结果
     */
    @Override
    public int updateLitemallOrderGoods(LitemallOrderGoods litemallOrderGoods)
    {
        litemallOrderGoods.setUpdateTime(DateUtils.getNowDate());
        return litemallOrderGoodsMapper.updateLitemallOrderGoods(litemallOrderGoods);
    }

    /**
     * 批量删除订单商品
     * 
     * @param ids 需要删除的订单商品ID
     * @return 结果
     */
    @Override
    public int deleteLitemallOrderGoodsByIds(Long[] ids)
    {
        return litemallOrderGoodsMapper.deleteLitemallOrderGoodsByIds(ids);
    }

    /**
     * 删除订单商品信息
     * 
     * @param id 订单商品ID
     * @return 结果
     */
    @Override
    public int deleteLitemallOrderGoodsById(Long id)
    {
        return litemallOrderGoodsMapper.deleteLitemallOrderGoodsById(id);
    }
}
