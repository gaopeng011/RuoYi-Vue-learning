package com.ruoyi.project.shop.service;

import com.ruoyi.project.shop.domain.LitemallBrand;

import java.util.List;

/**
 * 品牌商Service接口
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public interface ILitemallBrandService
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




    public List<LitemallBrand> selectLitemallBrandAll();

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
     * 批量删除品牌商
     *
     * @param ids 需要删除的品牌商ID
     * @return 结果
     */
    public int deleteLitemallBrandByIds(Long[] ids);

    /**
     * 删除品牌商信息
     *
     * @param id 品牌商ID
     * @return 结果
     */
    public int deleteLitemallBrandById(Long id);
}
