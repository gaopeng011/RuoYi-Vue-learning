package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallCategory;
import com.ruoyi.project.shop.mapper.LitemallCategoryMapper;
import com.ruoyi.project.shop.service.ILitemallCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类目Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallCategoryServiceImpl implements ILitemallCategoryService
{
    @Autowired
    private LitemallCategoryMapper litemallCategoryMapper;

    /**
     * 查询类目
     *
     * @param id 类目ID
     * @return 类目
     */
    @Override
    public LitemallCategory selectLitemallCategoryById(Long id)
    {
        return litemallCategoryMapper.selectLitemallCategoryById(id);
    }

    /**
     * 查询类目列表
     *
     * @param litemallCategory 类目
     * @return 类目
     */
    @Override
    public List<LitemallCategory> selectLitemallCategoryList(LitemallCategory litemallCategory)
    {
        return litemallCategoryMapper.selectLitemallCategoryList(litemallCategory);
    }

    @Override
    public List<LitemallCategory> queryL1() {



        LitemallCategory litemallCategory=new LitemallCategory();
        litemallCategory.setLevel("L1");

        return litemallCategoryMapper.selectLitemallCategoryList(litemallCategory);
    }

    @Override
    public List<LitemallCategory> queryL2ByPid(long pid) {
        LitemallCategory litemallCategory=new LitemallCategory();
       litemallCategory.setParentId(pid);

        return litemallCategoryMapper.selectLitemallCategoryList(litemallCategory);
    }

    /**
     * 新增类目
     *
     * @param litemallCategory 类目
     * @return 结果
     */
    @Override
    public int insertLitemallCategory(LitemallCategory litemallCategory)
    {
        return litemallCategoryMapper.insertLitemallCategory(litemallCategory);
    }

    /**
     * 修改类目
     *
     * @param litemallCategory 类目
     * @return 结果
     */
    @Override
    public int updateLitemallCategory(LitemallCategory litemallCategory)
    {
        litemallCategory.setUpdateTime(DateUtils.getNowDate());
        return litemallCategoryMapper.updateLitemallCategory(litemallCategory);
    }

    /**
     * 批量删除类目
     *
     * @param ids 需要删除的类目ID
     * @return 结果
     */
    @Override
    public int deleteLitemallCategoryByIds(Long[] ids)
    {
        return litemallCategoryMapper.deleteLitemallCategoryByIds(ids);
    }

    /**
     * 删除类目信息
     *
     * @param id 类目ID
     * @return 结果
     */
    @Override
    public int deleteLitemallCategoryById(Long id)
    {
        return litemallCategoryMapper.deleteLitemallCategoryById(id);
    }
}
