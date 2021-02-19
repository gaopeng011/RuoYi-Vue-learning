package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallSearchHistory;
import com.ruoyi.project.shop.mapper.LitemallSearchHistoryMapper;
import com.ruoyi.project.shop.service.ILitemallSearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 搜索历史Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallSearchHistoryServiceImpl implements ILitemallSearchHistoryService 
{
    @Autowired
    private LitemallSearchHistoryMapper litemallSearchHistoryMapper;

    /**
     * 查询搜索历史
     * 
     * @param id 搜索历史ID
     * @return 搜索历史
     */
    @Override
    public LitemallSearchHistory selectLitemallSearchHistoryById(Long id)
    {
        return litemallSearchHistoryMapper.selectLitemallSearchHistoryById(id);
    }

    /**
     * 查询搜索历史列表
     * 
     * @param litemallSearchHistory 搜索历史
     * @return 搜索历史
     */
    @Override
    public List<LitemallSearchHistory> selectLitemallSearchHistoryList(LitemallSearchHistory litemallSearchHistory)
    {
        return litemallSearchHistoryMapper.selectLitemallSearchHistoryList(litemallSearchHistory);
    }

    /**
     * 新增搜索历史
     * 
     * @param litemallSearchHistory 搜索历史
     * @return 结果
     */
    @Override
    public int insertLitemallSearchHistory(LitemallSearchHistory litemallSearchHistory)
    {
        return litemallSearchHistoryMapper.insertLitemallSearchHistory(litemallSearchHistory);
    }

    /**
     * 修改搜索历史
     * 
     * @param litemallSearchHistory 搜索历史
     * @return 结果
     */
    @Override
    public int updateLitemallSearchHistory(LitemallSearchHistory litemallSearchHistory)
    {
        litemallSearchHistory.setUpdateTime(DateUtils.getNowDate());
        return litemallSearchHistoryMapper.updateLitemallSearchHistory(litemallSearchHistory);
    }

    /**
     * 批量删除搜索历史
     * 
     * @param ids 需要删除的搜索历史ID
     * @return 结果
     */
    @Override
    public int deleteLitemallSearchHistoryByIds(Long[] ids)
    {
        return litemallSearchHistoryMapper.deleteLitemallSearchHistoryByIds(ids);
    }

    /**
     * 删除搜索历史信息
     * 
     * @param id 搜索历史ID
     * @return 结果
     */
    @Override
    public int deleteLitemallSearchHistoryById(Long id)
    {
        return litemallSearchHistoryMapper.deleteLitemallSearchHistoryById(id);
    }
}
