package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallGoodsProduct;
import com.ruoyi.project.shop.mapper.LitemallGoodsProductMapper;
import com.ruoyi.project.shop.service.ILitemallGoodsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品货品Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallGoodsProductServiceImpl implements ILitemallGoodsProductService
{
    @Autowired
    private LitemallGoodsProductMapper litemallGoodsProductMapper;

    /**
     * 查询商品货品
     *
     * @param id 商品货品ID
     * @return 商品货品
     */
    @Override
    public LitemallGoodsProduct selectLitemallGoodsProductById(Long id)
    {
        return litemallGoodsProductMapper.selectLitemallGoodsProductById(id);
    }

    /**
     * 查询商品货品列表
     *
     * @param litemallGoodsProduct 商品货品
     * @return 商品货品
     */
    @Override
    public List<LitemallGoodsProduct> selectLitemallGoodsProductList(LitemallGoodsProduct litemallGoodsProduct)
    {
        return litemallGoodsProductMapper.selectLitemallGoodsProductList(litemallGoodsProduct);
    }

    @Override
    public List<LitemallGoodsProduct> queryByGid(Long goodsId) {
        return litemallGoodsProductMapper.queryByGid(goodsId);
    }

    /**
     * 新增商品货品
     *
     * @param litemallGoodsProduct 商品货品
     * @return 结果
     */
    @Override
    public int insertLitemallGoodsProduct(LitemallGoodsProduct litemallGoodsProduct)
    {
        return litemallGoodsProductMapper.insertLitemallGoodsProduct(litemallGoodsProduct);
    }

    /**
     * 修改商品货品
     *
     * @param litemallGoodsProduct 商品货品
     * @return 结果
     */
    @Override
    public int updateLitemallGoodsProduct(LitemallGoodsProduct litemallGoodsProduct)
    {
        litemallGoodsProduct.setUpdateTime(DateUtils.getNowDate());
        return litemallGoodsProductMapper.updateLitemallGoodsProduct(litemallGoodsProduct);
    }

    /**
     * 批量删除商品货品
     *
     * @param ids 需要删除的商品货品ID
     * @return 结果
     */
    @Override
    public int deleteLitemallGoodsProductByIds(Long[] ids)
    {
        return litemallGoodsProductMapper.deleteLitemallGoodsProductByIds(ids);
    }

    /**
     * 删除商品货品信息
     *
     * @param id 商品货品ID
     * @return 结果
     */
    @Override
    public int deleteLitemallGoodsProductById(Long id)
    {
        return litemallGoodsProductMapper.deleteLitemallGoodsProductById(id);
    }
}
