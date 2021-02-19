package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallBrand;
import com.ruoyi.project.shop.mapper.LitemallBrandMapper;
import com.ruoyi.project.shop.service.ILitemallBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌商Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallBrandServiceImpl implements ILitemallBrandService
{
    @Autowired
    private LitemallBrandMapper litemallBrandMapper;

    /**
     * 查询品牌商
     *
     * @param id 品牌商ID
     * @return 品牌商
     */
    @Override
    public LitemallBrand selectLitemallBrandById(Long id)
    {
        return litemallBrandMapper.selectLitemallBrandById(id);
    }

    /**
     * 查询品牌商列表
     *
     * @param litemallBrand 品牌商
     * @return 品牌商
     */
    @Override
    public List<LitemallBrand> selectLitemallBrandList(LitemallBrand litemallBrand)
    {
        return litemallBrandMapper.selectLitemallBrandList(litemallBrand);
    }

    @Override
    public List<LitemallBrand> selectLitemallBrandAll() {

        LitemallBrand litemallBrand=new LitemallBrand();
        return litemallBrandMapper.selectLitemallBrandList(litemallBrand);
    }

    /**
     * 新增品牌商
     *
     * @param litemallBrand 品牌商
     * @return 结果
     */
    @Override
    public int insertLitemallBrand(LitemallBrand litemallBrand)
    {
        litemallBrand.setAddTime(DateUtils.getNowDate());
        return litemallBrandMapper.insertLitemallBrand(litemallBrand);
    }

    /**
     * 修改品牌商
     *
     * @param litemallBrand 品牌商
     * @return 结果
     */
    @Override
    public int updateLitemallBrand(LitemallBrand litemallBrand)
    {
        litemallBrand.setUpdateTime(DateUtils.getNowDate());
        return litemallBrandMapper.updateLitemallBrand(litemallBrand);
    }

    /**
     * 批量删除品牌商
     *
     * @param ids 需要删除的品牌商ID
     * @return 结果
     */
    @Override
    public int deleteLitemallBrandByIds(Long[] ids)
    {
        return litemallBrandMapper.deleteLitemallBrandByIds(ids);
    }

    /**
     * 删除品牌商信息,逻辑删除
     *
     * @param id 品牌商ID
     * @return 结果
     */
    @Override
    public int deleteLitemallBrandById(Long id)
    {
        LitemallBrand litemallBrand = litemallBrandMapper.selectLitemallBrandById(id);

        if (litemallBrand.getDeleted()==1){
            litemallBrand.setDeleted(0);
            litemallBrand.setUpdateTime(DateUtils.getNowDate());
            return litemallBrandMapper.updateLitemallBrand(litemallBrand);
        }
        return litemallBrandMapper.deleteLitemallBrandById(id);
    }
}
