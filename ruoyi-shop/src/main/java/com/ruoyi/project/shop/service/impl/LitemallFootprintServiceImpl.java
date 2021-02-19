package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallFootprint;
import com.ruoyi.project.shop.mapper.LitemallFootprintMapper;
import com.ruoyi.project.shop.service.ILitemallFootprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户浏览足迹Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallFootprintServiceImpl implements ILitemallFootprintService 
{
    @Autowired
    private LitemallFootprintMapper litemallFootprintMapper;

    /**
     * 查询用户浏览足迹
     * 
     * @param id 用户浏览足迹ID
     * @return 用户浏览足迹
     */
    @Override
    public LitemallFootprint selectLitemallFootprintById(Long id)
    {
        return litemallFootprintMapper.selectLitemallFootprintById(id);
    }

    /**
     * 查询用户浏览足迹列表
     * 
     * @param litemallFootprint 用户浏览足迹
     * @return 用户浏览足迹
     */
    @Override
    public List<LitemallFootprint> selectLitemallFootprintList(LitemallFootprint litemallFootprint)
    {
        return litemallFootprintMapper.selectLitemallFootprintList(litemallFootprint);
    }

    /**
     * 新增用户浏览足迹
     * 
     * @param litemallFootprint 用户浏览足迹
     * @return 结果
     */
    @Override
    public int insertLitemallFootprint(LitemallFootprint litemallFootprint)
    {
        return litemallFootprintMapper.insertLitemallFootprint(litemallFootprint);
    }

    /**
     * 修改用户浏览足迹
     * 
     * @param litemallFootprint 用户浏览足迹
     * @return 结果
     */
    @Override
    public int updateLitemallFootprint(LitemallFootprint litemallFootprint)
    {
        litemallFootprint.setUpdateTime(DateUtils.getNowDate());
        return litemallFootprintMapper.updateLitemallFootprint(litemallFootprint);
    }

    /**
     * 批量删除用户浏览足迹
     * 
     * @param ids 需要删除的用户浏览足迹ID
     * @return 结果
     */
    @Override
    public int deleteLitemallFootprintByIds(Long[] ids)
    {
        return litemallFootprintMapper.deleteLitemallFootprintByIds(ids);
    }

    /**
     * 删除用户浏览足迹信息
     * 
     * @param id 用户浏览足迹ID
     * @return 结果
     */
    @Override
    public int deleteLitemallFootprintById(Long id)
    {
        return litemallFootprintMapper.deleteLitemallFootprintById(id);
    }
}
