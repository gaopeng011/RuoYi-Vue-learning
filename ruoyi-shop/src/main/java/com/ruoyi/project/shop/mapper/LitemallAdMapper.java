package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallAd;

import java.util.List;

/**
 * 广告Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallAdMapper 
{
    /**
     * 查询广告
     * 
     * @param id 广告ID
     * @return 广告
     */
    public LitemallAd selectLitemallAdById(Long id);

    /**
     * 查询广告列表
     * 
     * @param litemallAd 广告
     * @return 广告集合
     */
    public List<LitemallAd> selectLitemallAdList(LitemallAd litemallAd);

    /**
     * 新增广告
     * 
     * @param litemallAd 广告
     * @return 结果
     */
    public int insertLitemallAd(LitemallAd litemallAd);

    /**
     * 修改广告
     * 
     * @param litemallAd 广告
     * @return 结果
     */
    public int updateLitemallAd(LitemallAd litemallAd);

    /**
     * 删除广告
     * 
     * @param id 广告ID
     * @return 结果
     */
    public int deleteLitemallAdById(Long id);

    /**
     * 批量删除广告
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallAdByIds(Long[] ids);
}
