package com.ruoyi.project.shop.service;

import com.ruoyi.project.shop.domain.LitemallGrouponRules;

import java.util.List;

/**
 * 团购规则Service接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface ILitemallGrouponRulesService 
{
    /**
     * 查询团购规则
     * 
     * @param id 团购规则ID
     * @return 团购规则
     */
    public LitemallGrouponRules selectLitemallGrouponRulesById(Long id);

    /**
     * 查询团购规则列表
     * 
     * @param litemallGrouponRules 团购规则
     * @return 团购规则集合
     */
    public List<LitemallGrouponRules> selectLitemallGrouponRulesList(LitemallGrouponRules litemallGrouponRules);

    /**
     * 新增团购规则
     * 
     * @param litemallGrouponRules 团购规则
     * @return 结果
     */
    public int insertLitemallGrouponRules(LitemallGrouponRules litemallGrouponRules);

    /**
     * 修改团购规则
     * 
     * @param litemallGrouponRules 团购规则
     * @return 结果
     */
    public int updateLitemallGrouponRules(LitemallGrouponRules litemallGrouponRules);

    /**
     * 批量删除团购规则
     * 
     * @param ids 需要删除的团购规则ID
     * @return 结果
     */
    public int deleteLitemallGrouponRulesByIds(Long[] ids);

    /**
     * 删除团购规则信息
     * 
     * @param id 团购规则ID
     * @return 结果
     */
    public int deleteLitemallGrouponRulesById(Long id);
}
