package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallGoods;

import java.util.List;

/**
 * 商品基本信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallGoodsMapper
{
    /**
     * 查询商品基本信息
     *
     * @param id 商品基本信息ID
     * @return 商品基本信息
     */
    public LitemallGoods selectLitemallGoodsById(Long id);

    /**
     * 查询商品基本信息列表
     *
     * @param litemallGoods 商品基本信息
     * @return 商品基本信息集合
     */
    public List<LitemallGoods> selectLitemallGoodsList(LitemallGoods litemallGoods);


    public List<LitemallGoods> selectLitemallGoodsByIds(Integer[] ids);

    public long checkExistGoodsByName(LitemallGoods litemallGoods);
    /**
     * 新增商品基本信息
     *
     * @param litemallGoods 商品基本信息
     * @return 结果
     */
    public int insertLitemallGoods(LitemallGoods litemallGoods);

    /**
     * 修改商品基本信息
     *
     * @param litemallGoods 商品基本信息
     * @return 结果
     */
    public int updateLitemallGoods(LitemallGoods litemallGoods);

    /**
     * 删除商品基本信息
     *
     * @param id 商品基本信息ID
     * @return 结果
     */
    public int deleteLitemallGoodsById(Long id);

    /**
     * 批量删除商品基本信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallGoodsByIds(Long[] ids);
}
