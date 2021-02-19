package com.ruoyi.project.shop.service;

import com.ruoyi.project.shop.domain.LitemallCategory;

import java.util.List;

/**
 * 类目Service接口
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public interface ILitemallCategoryService
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
 * @Author hanguanglei
 * @Description //获取一级分类
 * @Date 11:37 2020/3/12
 * @Param []
 * @return java.util.List<com.ruoyi.project.shop.domain.LitemallCategory>
 **/
    public List<LitemallCategory> queryL1();



    public List<LitemallCategory> queryL2ByPid(long pid);

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
     * 批量删除类目
     *
     * @param ids 需要删除的类目ID
     * @return 结果
     */
    public int deleteLitemallCategoryByIds(Long[] ids);

    /**
     * 删除类目信息
     *
     * @param id 类目ID
     * @return 结果
     */
    public int deleteLitemallCategoryById(Long id);
}
