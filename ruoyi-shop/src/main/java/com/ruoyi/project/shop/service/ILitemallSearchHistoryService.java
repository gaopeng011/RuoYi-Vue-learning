package com.ruoyi.project.shop.service;

import com.ruoyi.project.shop.domain.LitemallSearchHistory;

import java.util.List;

/**
 * 搜索历史Service接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface ILitemallSearchHistoryService 
{
    /**
     * 查询搜索历史
     * 
     * @param id 搜索历史ID
     * @return 搜索历史
     */
    public LitemallSearchHistory selectLitemallSearchHistoryById(Long id);

    /**
     * 查询搜索历史列表
     * 
     * @param litemallSearchHistory 搜索历史
     * @return 搜索历史集合
     */
    public List<LitemallSearchHistory> selectLitemallSearchHistoryList(LitemallSearchHistory litemallSearchHistory);

    /**
     * 新增搜索历史
     * 
     * @param litemallSearchHistory 搜索历史
     * @return 结果
     */
    public int insertLitemallSearchHistory(LitemallSearchHistory litemallSearchHistory);

    /**
     * 修改搜索历史
     * 
     * @param litemallSearchHistory 搜索历史
     * @return 结果
     */
    public int updateLitemallSearchHistory(LitemallSearchHistory litemallSearchHistory);

    /**
     * 批量删除搜索历史
     * 
     * @param ids 需要删除的搜索历史ID
     * @return 结果
     */
    public int deleteLitemallSearchHistoryByIds(Long[] ids);

    /**
     * 删除搜索历史信息
     * 
     * @param id 搜索历史ID
     * @return 结果
     */
    public int deleteLitemallSearchHistoryById(Long id);
}
