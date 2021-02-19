package com.ruoyi.project.shop.service;

import com.ruoyi.project.shop.domain.LitemallGoodsSpecification;

import java.util.List;

/**
 * 商品规格Service接口
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public interface ILitemallGoodsSpecificationService
{
    /**
     * 查询商品规格
     *
     * @param id 商品规格ID
     * @return 商品规格
     */
    public LitemallGoodsSpecification selectLitemallGoodsSpecificationById(Long id);




    List<LitemallGoodsSpecification>  queryByGid(Long goodsId);
    /**
     * 查询商品规格列表
     *
     * @param litemallGoodsSpecification 商品规格
     * @return 商品规格集合
     */
    public List<LitemallGoodsSpecification> selectLitemallGoodsSpecificationList(LitemallGoodsSpecification litemallGoodsSpecification);

    /**
     * 新增商品规格
     *
     * @param litemallGoodsSpecification 商品规格
     * @return 结果
     */
    public int insertLitemallGoodsSpecification(LitemallGoodsSpecification litemallGoodsSpecification);

    /**
     * 修改商品规格
     *
     * @param litemallGoodsSpecification 商品规格
     * @return 结果
     */
    public int updateLitemallGoodsSpecification(LitemallGoodsSpecification litemallGoodsSpecification);

    /**
     * 批量删除商品规格
     *
     * @param ids 需要删除的商品规格ID
     * @return 结果
     */
    public int deleteLitemallGoodsSpecificationByIds(Long[] ids);

    /**
     * 删除商品规格信息
     *
     * @param id 商品规格ID
     * @return 结果
     */
    public int deleteLitemallGoodsSpecificationById(Long id);
}
