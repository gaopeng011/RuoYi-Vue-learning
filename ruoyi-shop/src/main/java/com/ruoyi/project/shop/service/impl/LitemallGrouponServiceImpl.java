package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallGroupon;
import com.ruoyi.project.shop.mapper.LitemallGrouponMapper;
import com.ruoyi.project.shop.service.ILitemallGrouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 团购活动Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallGrouponServiceImpl implements ILitemallGrouponService 
{
    @Autowired
    private LitemallGrouponMapper litemallGrouponMapper;

    /**
     * 查询团购活动
     * 
     * @param id 团购活动ID
     * @return 团购活动
     */
    @Override
    public LitemallGroupon selectLitemallGrouponById(Long id)
    {
        return litemallGrouponMapper.selectLitemallGrouponById(id);
    }

    /**
     * 查询团购活动列表
     * 
     * @param litemallGroupon 团购活动
     * @return 团购活动
     */
    @Override
    public List<LitemallGroupon> selectLitemallGrouponList(LitemallGroupon litemallGroupon)
    {
        return litemallGrouponMapper.selectLitemallGrouponList(litemallGroupon);
    }

    /**
     * 新增团购活动
     * 
     * @param litemallGroupon 团购活动
     * @return 结果
     */
    @Override
    public int insertLitemallGroupon(LitemallGroupon litemallGroupon)
    {
        return litemallGrouponMapper.insertLitemallGroupon(litemallGroupon);
    }

    /**
     * 修改团购活动
     * 
     * @param litemallGroupon 团购活动
     * @return 结果
     */
    @Override
    public int updateLitemallGroupon(LitemallGroupon litemallGroupon)
    {
        litemallGroupon.setUpdateTime(DateUtils.getNowDate());
        return litemallGrouponMapper.updateLitemallGroupon(litemallGroupon);
    }

    /**
     * 批量删除团购活动
     * 
     * @param ids 需要删除的团购活动ID
     * @return 结果
     */
    @Override
    public int deleteLitemallGrouponByIds(Long[] ids)
    {
        return litemallGrouponMapper.deleteLitemallGrouponByIds(ids);
    }

    /**
     * 删除团购活动信息
     * 
     * @param id 团购活动ID
     * @return 结果
     */
    @Override
    public int deleteLitemallGrouponById(Long id)
    {
        return litemallGrouponMapper.deleteLitemallGrouponById(id);
    }
}
