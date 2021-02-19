package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallBrand;

import java.util.List;

/**
 * 品牌商Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallBrandMapper 
{
    /**
     * 查询品牌商
     * 
     * @param id 品牌商ID
     * @return 品牌商
     */
    public LitemallBrand selectLitemallBrandById(Long id);

    /**
     * 查询品牌商列表
     * 
     * @param litemallBrand 品牌商
     * @return 品牌商集合
     */
    public List<LitemallBrand> selectLitemallBrandList(LitemallBrand litemallBrand);

    /**
     * 新增品牌商
     * 
     * @param litemallBrand 品牌商
     * @return 结果
     */
    public int insertLitemallBrand(LitemallBrand litemallBrand);

    /**
     * 修改品牌商
     * 
     * @param litemallBrand 品牌商
     * @return 结果
     */
    public int updateLitemallBrand(LitemallBrand litemallBrand);

    /**
     * 删除品牌商
     * 
     * @param id 品牌商ID
     * @return 结果
     */
    public int deleteLitemallBrandById(Long id);

    /**
     * 批量删除品牌商
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallBrandByIds(Long[] ids);
}
