package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallIssue;

import java.util.List;

/**
 * 常见问题Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallIssueMapper 
{
    /**
     * 查询常见问题
     * 
     * @param id 常见问题ID
     * @return 常见问题
     */
    public LitemallIssue selectLitemallIssueById(Long id);

    /**
     * 查询常见问题列表
     * 
     * @param litemallIssue 常见问题
     * @return 常见问题集合
     */
    public List<LitemallIssue> selectLitemallIssueList(LitemallIssue litemallIssue);

    /**
     * 新增常见问题
     * 
     * @param litemallIssue 常见问题
     * @return 结果
     */
    public int insertLitemallIssue(LitemallIssue litemallIssue);

    /**
     * 修改常见问题
     * 
     * @param litemallIssue 常见问题
     * @return 结果
     */
    public int updateLitemallIssue(LitemallIssue litemallIssue);

    /**
     * 删除常见问题
     * 
     * @param id 常见问题ID
     * @return 结果
     */
    public int deleteLitemallIssueById(Long id);

    /**
     * 批量删除常见问题
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallIssueByIds(Long[] ids);
}
