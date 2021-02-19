package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallSystem;
import com.ruoyi.project.shop.mapper.LitemallSystemMapper;
import com.ruoyi.project.shop.service.ILitemallSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallSystemServiceImpl implements ILitemallSystemService 
{
    @Autowired
    private LitemallSystemMapper litemallSystemMapper;

    /**
     * 查询系统配置
     * 
     * @param id 系统配置ID
     * @return 系统配置
     */
    @Override
    public LitemallSystem selectLitemallSystemById(Long id)
    {
        return litemallSystemMapper.selectLitemallSystemById(id);
    }

    /**
     * 查询系统配置列表
     * 
     * @param litemallSystem 系统配置
     * @return 系统配置
     */
    @Override
    public List<LitemallSystem> selectLitemallSystemList(LitemallSystem litemallSystem)
    {
        return litemallSystemMapper.selectLitemallSystemList(litemallSystem);
    }

    /**
     * 新增系统配置
     * 
     * @param litemallSystem 系统配置
     * @return 结果
     */
    @Override
    public int insertLitemallSystem(LitemallSystem litemallSystem)
    {
        return litemallSystemMapper.insertLitemallSystem(litemallSystem);
    }

    /**
     * 修改系统配置
     * 
     * @param litemallSystem 系统配置
     * @return 结果
     */
    @Override
    public int updateLitemallSystem(LitemallSystem litemallSystem)
    {
        litemallSystem.setUpdateTime(DateUtils.getNowDate());
        return litemallSystemMapper.updateLitemallSystem(litemallSystem);
    }

    /**
     * 批量删除系统配置
     * 
     * @param ids 需要删除的系统配置ID
     * @return 结果
     */
    @Override
    public int deleteLitemallSystemByIds(Long[] ids)
    {
        return litemallSystemMapper.deleteLitemallSystemByIds(ids);
    }

    /**
     * 删除系统配置信息
     * 
     * @param id 系统配置ID
     * @return 结果
     */
    @Override
    public int deleteLitemallSystemById(Long id)
    {
        return litemallSystemMapper.deleteLitemallSystemById(id);
    }
}
