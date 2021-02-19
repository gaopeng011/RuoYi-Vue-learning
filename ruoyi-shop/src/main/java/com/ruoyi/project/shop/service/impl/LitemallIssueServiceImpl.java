package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallIssue;
import com.ruoyi.project.shop.mapper.LitemallIssueMapper;
import com.ruoyi.project.shop.service.ILitemallIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 常见问题Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallIssueServiceImpl implements ILitemallIssueService 
{
    @Autowired
    private LitemallIssueMapper litemallIssueMapper;

    /**
     * 查询常见问题
     * 
     * @param id 常见问题ID
     * @return 常见问题
     */
    @Override
    public LitemallIssue selectLitemallIssueById(Long id)
    {
        return litemallIssueMapper.selectLitemallIssueById(id);
    }

    /**
     * 查询常见问题列表
     * 
     * @param litemallIssue 常见问题
     * @return 常见问题
     */
    @Override
    public List<LitemallIssue> selectLitemallIssueList(LitemallIssue litemallIssue)
    {
        return litemallIssueMapper.selectLitemallIssueList(litemallIssue);
    }

    /**
     * 新增常见问题
     * 
     * @param litemallIssue 常见问题
     * @return 结果
     */
    @Override
    public int insertLitemallIssue(LitemallIssue litemallIssue)
    {
        return litemallIssueMapper.insertLitemallIssue(litemallIssue);
    }

    /**
     * 修改常见问题
     * 
     * @param litemallIssue 常见问题
     * @return 结果
     */
    @Override
    public int updateLitemallIssue(LitemallIssue litemallIssue)
    {
        litemallIssue.setUpdateTime(DateUtils.getNowDate());
        return litemallIssueMapper.updateLitemallIssue(litemallIssue);
    }

    /**
     * 批量删除常见问题
     * 
     * @param ids 需要删除的常见问题ID
     * @return 结果
     */
    @Override
    public int deleteLitemallIssueByIds(Long[] ids)
    {
        return litemallIssueMapper.deleteLitemallIssueByIds(ids);
    }

    /**
     * 删除常见问题信息
     * 
     * @param id 常见问题ID
     * @return 结果
     */
    @Override
    public int deleteLitemallIssueById(Long id)
    {
        return litemallIssueMapper.deleteLitemallIssueById(id);
    }
}
