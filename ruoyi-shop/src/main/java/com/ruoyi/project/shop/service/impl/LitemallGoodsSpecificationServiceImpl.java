package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallGoodsSpecification;
import com.ruoyi.project.shop.mapper.LitemallGoodsSpecificationMapper;
import com.ruoyi.project.shop.service.ILitemallGoodsSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品规格Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallGoodsSpecificationServiceImpl implements ILitemallGoodsSpecificationService
{
    @Autowired
    private LitemallGoodsSpecificationMapper litemallGoodsSpecificationMapper;

    /**
     * 查询商品规格
     *
     * @param id 商品规格ID
     * @return 商品规格
     */
    @Override
    public LitemallGoodsSpecification selectLitemallGoodsSpecificationById(Long id)
    {
        return litemallGoodsSpecificationMapper.selectLitemallGoodsSpecificationById(id);
    }

    @Override
    public List<LitemallGoodsSpecification> queryByGid(Long goodsId) {
        return litemallGoodsSpecificationMapper.queryByGid(goodsId);
    }

    /**
     * 查询商品规格列表
     *
     * @param litemallGoodsSpecification 商品规格
     * @return 商品规格
     */
    @Override
    public List<LitemallGoodsSpecification> selectLitemallGoodsSpecificationList(LitemallGoodsSpecification litemallGoodsSpecification)
    {
        return litemallGoodsSpecificationMapper.selectLitemallGoodsSpecificationList(litemallGoodsSpecification);
    }

    /**
     * 新增商品规格
     *
     * @param litemallGoodsSpecification 商品规格
     * @return 结果
     */
    @Override
    public int insertLitemallGoodsSpecification(LitemallGoodsSpecification litemallGoodsSpecification)
    {
        return litemallGoodsSpecificationMapper.insertLitemallGoodsSpecification(litemallGoodsSpecification);
    }

    /**
     * 修改商品规格
     *
     * @param litemallGoodsSpecification 商品规格
     * @return 结果
     */
    @Override
    public int updateLitemallGoodsSpecification(LitemallGoodsSpecification litemallGoodsSpecification)
    {
        litemallGoodsSpecification.setUpdateTime(DateUtils.getNowDate());
        return litemallGoodsSpecificationMapper.updateLitemallGoodsSpecification(litemallGoodsSpecification);
    }

    /**
     * 批量删除商品规格
     *
     * @param ids 需要删除的商品规格ID
     * @return 结果
     */
    @Override
    public int deleteLitemallGoodsSpecificationByIds(Long[] ids)
    {
        return litemallGoodsSpecificationMapper.deleteLitemallGoodsSpecificationByIds(ids);
    }

    /**
     * 删除商品规格信息
     *
     * @param id 商品规格ID
     * @return 结果
     */
    @Override
    public int deleteLitemallGoodsSpecificationById(Long id)
    {
        return litemallGoodsSpecificationMapper.deleteLitemallGoodsSpecificationById(id);
    }
}
