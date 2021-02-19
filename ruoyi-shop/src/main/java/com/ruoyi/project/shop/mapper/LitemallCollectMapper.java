package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallCollect;

import java.util.List;

/**
 * 收藏Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallCollectMapper 
{
    /**
     * 查询收藏
     * 
     * @param id 收藏ID
     * @return 收藏
     */
    public LitemallCollect selectLitemallCollectById(Long id);

    /**
     * 查询收藏列表
     * 
     * @param litemallCollect 收藏
     * @return 收藏集合
     */
    public List<LitemallCollect> selectLitemallCollectList(LitemallCollect litemallCollect);

    /**
     * 新增收藏
     * 
     * @param litemallCollect 收藏
     * @return 结果
     */
    public int insertLitemallCollect(LitemallCollect litemallCollect);

    /**
     * 修改收藏
     * 
     * @param litemallCollect 收藏
     * @return 结果
     */
    public int updateLitemallCollect(LitemallCollect litemallCollect);

    /**
     * 删除收藏
     * 
     * @param id 收藏ID
     * @return 结果
     */
    public int deleteLitemallCollectById(Long id);

    /**
     * 批量删除收藏
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallCollectByIds(Long[] ids);
}
