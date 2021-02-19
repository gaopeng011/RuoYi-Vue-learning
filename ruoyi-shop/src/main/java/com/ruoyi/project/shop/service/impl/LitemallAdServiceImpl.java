package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallAd;
import com.ruoyi.project.shop.mapper.LitemallAdMapper;
import com.ruoyi.project.shop.service.ILitemallAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallAdServiceImpl implements ILitemallAdService 
{
    @Autowired
    private LitemallAdMapper litemallAdMapper;

    /**
     * 查询广告
     * 
     * @param id 广告ID
     * @return 广告
     */
    @Override
    public LitemallAd selectLitemallAdById(Long id)
    {
        return litemallAdMapper.selectLitemallAdById(id);
    }

    /**
     * 查询广告列表
     * 
     * @param litemallAd 广告
     * @return 广告
     */
    @Override
    public List<LitemallAd> selectLitemallAdList(LitemallAd litemallAd)
    {
        return litemallAdMapper.selectLitemallAdList(litemallAd);
    }

    /**
     * 新增广告
     * 
     * @param litemallAd 广告
     * @return 结果
     */
    @Override
    public int insertLitemallAd(LitemallAd litemallAd)
    {
        return litemallAdMapper.insertLitemallAd(litemallAd);
    }

    /**
     * 修改广告
     * 
     * @param litemallAd 广告
     * @return 结果
     */
    @Override
    public int updateLitemallAd(LitemallAd litemallAd)
    {
        litemallAd.setUpdateTime(DateUtils.getNowDate());
        return litemallAdMapper.updateLitemallAd(litemallAd);
    }

    /**
     * 批量删除广告
     * 
     * @param ids 需要删除的广告ID
     * @return 结果
     */
    @Override
    public int deleteLitemallAdByIds(Long[] ids)
    {
        return litemallAdMapper.deleteLitemallAdByIds(ids);
    }

    /**
     * 删除广告信息
     * 
     * @param id 广告ID
     * @return 结果
     */
    @Override
    public int deleteLitemallAdById(Long id)
    {
        return litemallAdMapper.deleteLitemallAdById(id);
    }
}
