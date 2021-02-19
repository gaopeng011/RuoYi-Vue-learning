package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallCart;
import com.ruoyi.project.shop.mapper.LitemallCartMapper;
import com.ruoyi.project.shop.service.ILitemallCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车商品Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallCartServiceImpl implements ILitemallCartService
{
    @Autowired
    private LitemallCartMapper litemallCartMapper;

    /**
     * 查询购物车商品
     *
     * @param id 购物车商品ID
     * @return 购物车商品
     */
    @Override
    public LitemallCart selectLitemallCartById(Long id)
    {
        return litemallCartMapper.selectLitemallCartById(id);
    }

    /**
     * 查询购物车商品列表
     *
     * @param litemallCart 购物车商品
     * @return 购物车商品
     */
    @Override
    public List<LitemallCart> selectLitemallCartList(LitemallCart litemallCart)
    {
        return litemallCartMapper.selectLitemallCartList(litemallCart);
    }

    /**
     * 新增购物车商品
     *
     * @param litemallCart 购物车商品
     * @return 结果
     */
    @Override
    public int insertLitemallCart(LitemallCart litemallCart)
    {
        return litemallCartMapper.insertLitemallCart(litemallCart);
    }

    /**
     * 修改购物车商品
     *
     * @param litemallCart 购物车商品
     * @return 结果
     */
    @Override
    public int updateLitemallCart(LitemallCart litemallCart)
    {
        litemallCart.setUpdateTime(DateUtils.getNowDate());
        return litemallCartMapper.updateLitemallCart(litemallCart);
    }

    @Override
    public int updateLitemallCartByProductId(Long id, String goodsSn, String goodsName, BigDecimal price, String url) {

        LitemallCart litemallCart = new LitemallCart();
        litemallCart.setPrice(price);
        litemallCart.setPicUrl(url);
        litemallCart.setGoodsSn(goodsSn);
        litemallCart.setGoodsName(goodsName);
        litemallCart.setProductId(id);

        return litemallCartMapper.updateLitemallCartByProductId(litemallCart);
    }

    /**
     * 批量删除购物车商品
     *
     * @param ids 需要删除的购物车商品ID
     * @return 结果
     */
    @Override
    public int deleteLitemallCartByIds(Long[] ids)
    {
        return litemallCartMapper.deleteLitemallCartByIds(ids);
    }

    /**
     * 删除购物车商品信息
     *
     * @param id 购物车商品ID
     * @return 结果
     */
    @Override
    public int deleteLitemallCartById(Long id)
    {
        return litemallCartMapper.deleteLitemallCartById(id);
    }
}
