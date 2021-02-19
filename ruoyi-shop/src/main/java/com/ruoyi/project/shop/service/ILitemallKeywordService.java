package com.ruoyi.project.shop.service;

import com.ruoyi.project.shop.domain.LitemallKeyword;

import java.util.List;

/**
 * 关键字Service接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface ILitemallKeywordService 
{
    /**
     * 查询关键字
     * 
     * @param id 关键字ID
     * @return 关键字
     */
    public LitemallKeyword selectLitemallKeywordById(Long id);

    /**
     * 查询关键字列表
     * 
     * @param litemallKeyword 关键字
     * @return 关键字集合
     */
    public List<LitemallKeyword> selectLitemallKeywordList(LitemallKeyword litemallKeyword);

    /**
     * 新增关键字
     * 
     * @param litemallKeyword 关键字
     * @return 结果
     */
    public int insertLitemallKeyword(LitemallKeyword litemallKeyword);

    /**
     * 修改关键字
     * 
     * @param litemallKeyword 关键字
     * @return 结果
     */
    public int updateLitemallKeyword(LitemallKeyword litemallKeyword);

    /**
     * 批量删除关键字
     * 
     * @param ids 需要删除的关键字ID
     * @return 结果
     */
    public int deleteLitemallKeywordByIds(Long[] ids);

    /**
     * 删除关键字信息
     * 
     * @param id 关键字ID
     * @return 结果
     */
    public int deleteLitemallKeywordById(Long id);
}
