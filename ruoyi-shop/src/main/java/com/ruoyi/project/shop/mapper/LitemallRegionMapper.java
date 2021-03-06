package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallRegion;

import java.util.List;

/**
 * 行政区域Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallRegionMapper 
{
    /**
     * 查询行政区域
     * 
     * @param id 行政区域ID
     * @return 行政区域
     */
    public LitemallRegion selectLitemallRegionById(Long id);

    /**
     * 查询行政区域列表
     * 
     * @param litemallRegion 行政区域
     * @return 行政区域集合
     */
    public List<LitemallRegion> selectLitemallRegionList(LitemallRegion litemallRegion);

    /**
     * 新增行政区域
     * 
     * @param litemallRegion 行政区域
     * @return 结果
     */
    public int insertLitemallRegion(LitemallRegion litemallRegion);

    /**
     * 修改行政区域
     * 
     * @param litemallRegion 行政区域
     * @return 结果
     */
    public int updateLitemallRegion(LitemallRegion litemallRegion);

    /**
     * 删除行政区域
     * 
     * @param id 行政区域ID
     * @return 结果
     */
    public int deleteLitemallRegionById(Long id);

    /**
     * 批量删除行政区域
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallRegionByIds(Long[] ids);
}
