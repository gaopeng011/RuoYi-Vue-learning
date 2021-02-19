package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallGoodsAttribute;
import com.ruoyi.project.shop.mapper.LitemallGoodsAttributeMapper;
import com.ruoyi.project.shop.service.ILitemallGoodsAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品参数Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallGoodsAttributeServiceImpl implements ILitemallGoodsAttributeService
{
    @Autowired
    private LitemallGoodsAttributeMapper litemallGoodsAttributeMapper;

    /**
     * 查询商品参数
     *
     * @param id 商品参数ID
     * @return 商品参数
     */
    @Override
    public LitemallGoodsAttribute selectLitemallGoodsAttributeById(Long id)
    {
        return litemallGoodsAttributeMapper.selectLitemallGoodsAttributeById(id);
    }

    /**
     * 查询商品参数列表
     *
     * @param litemallGoodsAttribute 商品参数
     * @return 商品参数
     */
    @Override
    public List<LitemallGoodsAttribute> selectLitemallGoodsAttributeList(LitemallGoodsAttribute litemallGoodsAttribute)
    {
        return litemallGoodsAttributeMapper.selectLitemallGoodsAttributeList(litemallGoodsAttribute);
    }

    @Override
    public List<LitemallGoodsAttribute> queryByGid(Long goodsid) {
        return litemallGoodsAttributeMapper.queryByGid(goodsid);
    }

    /**
     * 新增商品参数
     *
     * @param litemallGoodsAttribute 商品参数
     * @return 结果
     */
    @Override
    public int insertLitemallGoodsAttribute(LitemallGoodsAttribute litemallGoodsAttribute)
    {
        return litemallGoodsAttributeMapper.insertLitemallGoodsAttribute(litemallGoodsAttribute);
    }

    /**
     * 修改商品参数
     *
     * @param litemallGoodsAttribute 商品参数
     * @return 结果
     */
    @Override
    public int updateLitemallGoodsAttribute(LitemallGoodsAttribute litemallGoodsAttribute)
    {
        litemallGoodsAttribute.setUpdateTime(DateUtils.getNowDate());
        return litemallGoodsAttributeMapper.updateLitemallGoodsAttribute(litemallGoodsAttribute);
    }

    /**
     * 批量删除商品参数
     *
     * @param ids 需要删除的商品参数ID
     * @return 结果
     */
    @Override
    public int deleteLitemallGoodsAttributeByIds(Long[] ids)
    {
        return litemallGoodsAttributeMapper.deleteLitemallGoodsAttributeByIds(ids);
    }

    /**
     * 删除商品参数信息
     *
     * @param id 商品参数ID
     * @return 结果
     */
    @Override
    public int deleteLitemallGoodsAttributeById(Long id)
    {
        return litemallGoodsAttributeMapper.deleteLitemallGoodsAttributeById(id);
    }
}
