package com.ruoyi.project.shop.service.impl;

import com.ruoyi.project.shop.domain.LitemallRegion;
import com.ruoyi.project.shop.mapper.LitemallRegionMapper;
import com.ruoyi.project.shop.service.ILitemallRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 行政区域Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallRegionServiceImpl implements ILitemallRegionService 
{
    @Autowired
    private LitemallRegionMapper litemallRegionMapper;

    /**
     * 查询行政区域
     * 
     * @param id 行政区域ID
     * @return 行政区域
     */
    @Override
    public LitemallRegion selectLitemallRegionById(Long id)
    {
        return litemallRegionMapper.selectLitemallRegionById(id);
    }

    /**
     * 查询行政区域列表
     * 
     * @param litemallRegion 行政区域
     * @return 行政区域
     */
    @Override
    public List<LitemallRegion> selectLitemallRegionList(LitemallRegion litemallRegion)
    {
        return litemallRegionMapper.selectLitemallRegionList(litemallRegion);
    }

    /**
     * 新增行政区域
     * 
     * @param litemallRegion 行政区域
     * @return 结果
     */
    @Override
    public int insertLitemallRegion(LitemallRegion litemallRegion)
    {
        return litemallRegionMapper.insertLitemallRegion(litemallRegion);
    }

    /**
     * 修改行政区域
     * 
     * @param litemallRegion 行政区域
     * @return 结果
     */
    @Override
    public int updateLitemallRegion(LitemallRegion litemallRegion)
    {
        return litemallRegionMapper.updateLitemallRegion(litemallRegion);
    }

    /**
     * 批量删除行政区域
     * 
     * @param ids 需要删除的行政区域ID
     * @return 结果
     */
    @Override
    public int deleteLitemallRegionByIds(Long[] ids)
    {
        return litemallRegionMapper.deleteLitemallRegionByIds(ids);
    }

    /**
     * 删除行政区域信息
     * 
     * @param id 行政区域ID
     * @return 结果
     */
    @Override
    public int deleteLitemallRegionById(Long id)
    {
        return litemallRegionMapper.deleteLitemallRegionById(id);
    }
}
