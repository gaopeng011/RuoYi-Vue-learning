package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallFeedback;
import com.ruoyi.project.shop.mapper.LitemallFeedbackMapper;
import com.ruoyi.project.shop.service.ILitemallFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 意见反馈Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallFeedbackServiceImpl implements ILitemallFeedbackService 
{
    @Autowired
    private LitemallFeedbackMapper litemallFeedbackMapper;

    /**
     * 查询意见反馈
     * 
     * @param id 意见反馈ID
     * @return 意见反馈
     */
    @Override
    public LitemallFeedback selectLitemallFeedbackById(Long id)
    {
        return litemallFeedbackMapper.selectLitemallFeedbackById(id);
    }

    /**
     * 查询意见反馈列表
     * 
     * @param litemallFeedback 意见反馈
     * @return 意见反馈
     */
    @Override
    public List<LitemallFeedback> selectLitemallFeedbackList(LitemallFeedback litemallFeedback)
    {
        return litemallFeedbackMapper.selectLitemallFeedbackList(litemallFeedback);
    }

    /**
     * 新增意见反馈
     * 
     * @param litemallFeedback 意见反馈
     * @return 结果
     */
    @Override
    public int insertLitemallFeedback(LitemallFeedback litemallFeedback)
    {
        return litemallFeedbackMapper.insertLitemallFeedback(litemallFeedback);
    }

    /**
     * 修改意见反馈
     * 
     * @param litemallFeedback 意见反馈
     * @return 结果
     */
    @Override
    public int updateLitemallFeedback(LitemallFeedback litemallFeedback)
    {
        litemallFeedback.setUpdateTime(DateUtils.getNowDate());
        return litemallFeedbackMapper.updateLitemallFeedback(litemallFeedback);
    }

    /**
     * 批量删除意见反馈
     * 
     * @param ids 需要删除的意见反馈ID
     * @return 结果
     */
    @Override
    public int deleteLitemallFeedbackByIds(Long[] ids)
    {
        return litemallFeedbackMapper.deleteLitemallFeedbackByIds(ids);
    }

    /**
     * 删除意见反馈信息
     * 
     * @param id 意见反馈ID
     * @return 结果
     */
    @Override
    public int deleteLitemallFeedbackById(Long id)
    {
        return litemallFeedbackMapper.deleteLitemallFeedbackById(id);
    }
}
