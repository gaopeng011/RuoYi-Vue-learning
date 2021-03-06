package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallFootprint;

import java.util.List;

/**
 * 用户浏览足迹Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallFootprintMapper 
{
    /**
     * 查询用户浏览足迹
     * 
     * @param id 用户浏览足迹ID
     * @return 用户浏览足迹
     */
    public LitemallFootprint selectLitemallFootprintById(Long id);

    /**
     * 查询用户浏览足迹列表
     * 
     * @param litemallFootprint 用户浏览足迹
     * @return 用户浏览足迹集合
     */
    public List<LitemallFootprint> selectLitemallFootprintList(LitemallFootprint litemallFootprint);

    /**
     * 新增用户浏览足迹
     * 
     * @param litemallFootprint 用户浏览足迹
     * @return 结果
     */
    public int insertLitemallFootprint(LitemallFootprint litemallFootprint);

    /**
     * 修改用户浏览足迹
     * 
     * @param litemallFootprint 用户浏览足迹
     * @return 结果
     */
    public int updateLitemallFootprint(LitemallFootprint litemallFootprint);

    /**
     * 删除用户浏览足迹
     * 
     * @param id 用户浏览足迹ID
     * @return 结果
     */
    public int deleteLitemallFootprintById(Long id);

    /**
     * 批量删除用户浏览足迹
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallFootprintByIds(Long[] ids);
}
