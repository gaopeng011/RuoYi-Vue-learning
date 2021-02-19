package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallGrouponRules;
import com.ruoyi.project.shop.mapper.LitemallGrouponRulesMapper;
import com.ruoyi.project.shop.service.ILitemallGrouponRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 团购规则Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallGrouponRulesServiceImpl implements ILitemallGrouponRulesService
{
    @Autowired
    private LitemallGrouponRulesMapper litemallGrouponRulesMapper;

    /**
     * 查询团购规则
     *
     * @param id 团购规则ID
     * @return 团购规则
     */
    @Override
    public LitemallGrouponRules selectLitemallGrouponRulesById(Long id)
    {
        return litemallGrouponRulesMapper.selectLitemallGrouponRulesById(id);
    }

    /**
     * 查询团购规则列表
     *
     * @param litemallGrouponRules 团购规则
     * @return 团购规则
     */
    @Override
    public List<LitemallGrouponRules> selectLitemallGrouponRulesList(LitemallGrouponRules litemallGrouponRules)
    {
        return litemallGrouponRulesMapper.selectLitemallGrouponRulesList(litemallGrouponRules);
    }

    /**
     * 新增团购规则
     *
     * @param litemallGrouponRules 团购规则
     * @return 结果
     */
    @Override
    public int insertLitemallGrouponRules(LitemallGrouponRules litemallGrouponRules)
    {
        litemallGrouponRules.setAddTime(DateUtils.getNowDate());
        litemallGrouponRules.setUpdateTime(DateUtils.getNowDate());
        return litemallGrouponRulesMapper.insertLitemallGrouponRules(litemallGrouponRules);
    }

    /**
     * 修改团购规则
     *
     * @param litemallGrouponRules 团购规则
     * @return 结果
     */
    @Override
    public int updateLitemallGrouponRules(LitemallGrouponRules litemallGrouponRules)
    {
        litemallGrouponRules.setUpdateTime(DateUtils.getNowDate());
        return litemallGrouponRulesMapper.updateLitemallGrouponRules(litemallGrouponRules);
    }

    /**
     * 批量删除团购规则
     *
     * @param ids 需要删除的团购规则ID
     * @return 结果
     */
    @Override
    public int deleteLitemallGrouponRulesByIds(Long[] ids)
    {
        return litemallGrouponRulesMapper.deleteLitemallGrouponRulesByIds(ids);
    }

    /**
     * 删除团购规则信息
     *
     * @param id 团购规则ID
     * @return 结果
     */
    @Override
    public int deleteLitemallGrouponRulesById(Long id)
    {
        return litemallGrouponRulesMapper.deleteLitemallGrouponRulesById(id);
    }
}
