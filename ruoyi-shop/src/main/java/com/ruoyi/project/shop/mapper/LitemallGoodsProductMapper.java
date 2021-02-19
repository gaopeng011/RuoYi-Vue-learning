package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallGoodsProduct;

import java.util.List;

/**
 * 商品货品Mapper接口
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallGoodsProductMapper
{
    /**
     * 查询商品货品
     *
     * @param id 商品货品ID
     * @return 商品货品
     */
    public LitemallGoodsProduct selectLitemallGoodsProductById(Long id);

    /**
     * 查询商品货品列表
     *
     * @param litemallGoodsProduct 商品货品
     * @return 商品货品集合
     */
    public List<LitemallGoodsProduct> selectLitemallGoodsProductList(LitemallGoodsProduct litemallGoodsProduct);



    List<LitemallGoodsProduct>  queryByGid(Long goodsId);

    /**
     * 新增商品货品
     *
     * @param litemallGoodsProduct 商品货品
     * @return 结果
     */
    public int insertLitemallGoodsProduct(LitemallGoodsProduct litemallGoodsProduct);

    /**
     * 修改商品货品
     *
     * @param litemallGoodsProduct 商品货品
     * @return 结果
     */
    public int updateLitemallGoodsProduct(LitemallGoodsProduct litemallGoodsProduct);

    /**
     * 删除商品货品
     *
     * @param id 商品货品ID
     * @return 结果
     */
    public int deleteLitemallGoodsProductById(Long id);

    /**
     * 批量删除商品货品
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallGoodsProductByIds(Long[] ids);
}
