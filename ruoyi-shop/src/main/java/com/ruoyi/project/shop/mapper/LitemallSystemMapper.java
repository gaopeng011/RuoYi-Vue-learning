package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallSystem;

import java.util.List;

/**
 * 系统配置Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallSystemMapper 
{
    /**
     * 查询系统配置
     * 
     * @param id 系统配置ID
     * @return 系统配置
     */
    public LitemallSystem selectLitemallSystemById(Long id);

    /**
     * 查询系统配置列表
     * 
     * @param litemallSystem 系统配置
     * @return 系统配置集合
     */
    public List<LitemallSystem> selectLitemallSystemList(LitemallSystem litemallSystem);

    /**
     * 新增系统配置
     * 
     * @param litemallSystem 系统配置
     * @return 结果
     */
    public int insertLitemallSystem(LitemallSystem litemallSystem);

    /**
     * 修改系统配置
     * 
     * @param litemallSystem 系统配置
     * @return 结果
     */
    public int updateLitemallSystem(LitemallSystem litemallSystem);

    /**
     * 删除系统配置
     * 
     * @param id 系统配置ID
     * @return 结果
     */
    public int deleteLitemallSystemById(Long id);

    /**
     * 批量删除系统配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallSystemByIds(Long[] ids);
}
