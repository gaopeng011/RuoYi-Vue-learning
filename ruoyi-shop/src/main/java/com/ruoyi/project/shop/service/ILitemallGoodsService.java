package com.ruoyi.project.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.project.shop.domain.LitemallGoods;
import com.ruoyi.project.shop.dto.GoodsAllinone;

import java.util.List;
import java.util.Map;


/**
 * 商品基本信息Service接口
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public interface ILitemallGoodsService
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

    /**
     * 新增商品基本信息
     *
     * @param litemallGoods 商品基本信息
     * @return 结果
     */
    public int insertLitemallGoods(LitemallGoods litemallGoods);


    public long checkExistGoodsByName(LitemallGoods litemallGoods);


    public AjaxResult createLitemallGoods(GoodsAllinone goodsAllinone);


    /**
     * 修改商品基本信息
     *
     * @param litemallGoods 商品基本信息
     * @return 结果
     */
    public AjaxResult updateLitemallGoods(GoodsAllinone goodsAllinone);

    /**
     * 批量删除商品基本信息
     *
     * @param ids 需要删除的商品基本信息ID
     * @return 结果
     */
    public int deleteLitemallGoodsByIds(Long[] ids);


    public List<LitemallGoods> selectLitemallGoodsByIds(Integer[] ids);

    /**
     * 删除商品基本信息信息
     *
     * @param id 商品基本信息ID
     * @return 结果
     */
    public int deleteLitemallGoodsById(Long id);



    //扩展的方法,获取商品的分类和商品的品牌

    public Map<String, Object> listCateAndBrand();



    public JSONObject goodsDetail(Long id);


    public AjaxResult delete(Long id);


}
