package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallCart;

import java.util.List;

/**
 * 购物车商品Mapper接口
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallCartMapper
{
    /**
     * 查询购物车商品
     *
     * @param id 购物车商品ID
     * @return 购物车商品
     */
    public LitemallCart selectLitemallCartById(Long id);

    /**
     * 查询购物车商品列表
     *
     * @param litemallCart 购物车商品
     * @return 购物车商品集合
     */
    public List<LitemallCart> selectLitemallCartList(LitemallCart litemallCart);

    /**
     * 新增购物车商品
     *
     * @param litemallCart 购物车商品
     * @return 结果
     */
    public int insertLitemallCart(LitemallCart litemallCart);

    /**
     * 修改购物车商品
     *
     * @param litemallCart 购物车商品
     * @return 结果
     */
    public int updateLitemallCart(LitemallCart litemallCart);



    /**
     * 修改购物车商品通过productId
     *
     * @param litemallCart 购物车商品
     * @return 结果
     */
    public int updateLitemallCartByProductId(LitemallCart litemallCart);


    /**
     * 删除购物车商品
     *
     * @param id 购物车商品ID
     * @return 结果
     */
    public int deleteLitemallCartById(Long id);

    /**
     * 批量删除购物车商品
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallCartByIds(Long[] ids);
}
