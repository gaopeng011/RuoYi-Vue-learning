package com.ruoyi.project.shop.service;

import com.ruoyi.project.shop.domain.LitemallGoodsAttribute;

import java.util.List;

/**
 * 商品参数Service接口
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public interface ILitemallGoodsAttributeService
{
    /**
     * 查询商品参数
     *
     * @param id 商品参数ID
     * @return 商品参数
     */
    public LitemallGoodsAttribute selectLitemallGoodsAttributeById(Long id);

    /**
     * 查询商品参数列表
     *
     * @param litemallGoodsAttribute 商品参数
     * @return 商品参数集合
     */
    public List<LitemallGoodsAttribute> selectLitemallGoodsAttributeList(LitemallGoodsAttribute litemallGoodsAttribute);

    public List<LitemallGoodsAttribute> queryByGid(Long goodsid);

    /**
     * 新增商品参数
     *
     * @param litemallGoodsAttribute 商品参数
     * @return 结果
     */
    public int insertLitemallGoodsAttribute(LitemallGoodsAttribute litemallGoodsAttribute);

    /**
     * 修改商品参数
     *
     * @param litemallGoodsAttribute 商品参数
     * @return 结果
     */
    public int updateLitemallGoodsAttribute(LitemallGoodsAttribute litemallGoodsAttribute);

    /**
     * 批量删除商品参数
     *
     * @param ids 需要删除的商品参数ID
     * @return 结果
     */
    public int deleteLitemallGoodsAttributeByIds(Long[] ids);

    /**
     * 删除商品参数信息
     *
     * @param id 商品参数ID
     * @return 结果
     */
    public int deleteLitemallGoodsAttributeById(Long id);
}
