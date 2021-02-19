package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallGroupon;

import java.util.List;

/**
 * 团购活动Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallGrouponMapper 
{
    /**
     * 查询团购活动
     * 
     * @param id 团购活动ID
     * @return 团购活动
     */
    public LitemallGroupon selectLitemallGrouponById(Long id);

    /**
     * 查询团购活动列表
     * 
     * @param litemallGroupon 团购活动
     * @return 团购活动集合
     */
    public List<LitemallGroupon> selectLitemallGrouponList(LitemallGroupon litemallGroupon);

    /**
     * 新增团购活动
     * 
     * @param litemallGroupon 团购活动
     * @return 结果
     */
    public int insertLitemallGroupon(LitemallGroupon litemallGroupon);

    /**
     * 修改团购活动
     * 
     * @param litemallGroupon 团购活动
     * @return 结果
     */
    public int updateLitemallGroupon(LitemallGroupon litemallGroupon);

    /**
     * 删除团购活动
     * 
     * @param id 团购活动ID
     * @return 结果
     */
    public int deleteLitemallGrouponById(Long id);

    /**
     * 批量删除团购活动
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallGrouponByIds(Long[] ids);
}
