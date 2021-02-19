package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallCategory;

import java.util.List;

/**
 * 类目Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallCategoryMapper 
{
    /**
     * 查询类目
     * 
     * @param id 类目ID
     * @return 类目
     */
    public LitemallCategory selectLitemallCategoryById(Long id);

    /**
     * 查询类目列表
     * 
     * @param litemallCategory 类目
     * @return 类目集合
     */
    public List<LitemallCategory> selectLitemallCategoryList(LitemallCategory litemallCategory);

    /**
     * 新增类目
     * 
     * @param litemallCategory 类目
     * @return 结果
     */
    public int insertLitemallCategory(LitemallCategory litemallCategory);

    /**
     * 修改类目
     * 
     * @param litemallCategory 类目
     * @return 结果
     */
    public int updateLitemallCategory(LitemallCategory litemallCategory);

    /**
     * 删除类目
     * 
     * @param id 类目ID
     * @return 结果
     */
    public int deleteLitemallCategoryById(Long id);

    /**
     * 批量删除类目
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallCategoryByIds(Long[] ids);
}
