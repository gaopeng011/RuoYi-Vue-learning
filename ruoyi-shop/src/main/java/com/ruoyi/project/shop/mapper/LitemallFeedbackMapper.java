package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallFeedback;

import java.util.List;

/**
 * 意见反馈Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallFeedbackMapper 
{
    /**
     * 查询意见反馈
     * 
     * @param id 意见反馈ID
     * @return 意见反馈
     */
    public LitemallFeedback selectLitemallFeedbackById(Long id);

    /**
     * 查询意见反馈列表
     * 
     * @param litemallFeedback 意见反馈
     * @return 意见反馈集合
     */
    public List<LitemallFeedback> selectLitemallFeedbackList(LitemallFeedback litemallFeedback);

    /**
     * 新增意见反馈
     * 
     * @param litemallFeedback 意见反馈
     * @return 结果
     */
    public int insertLitemallFeedback(LitemallFeedback litemallFeedback);

    /**
     * 修改意见反馈
     * 
     * @param litemallFeedback 意见反馈
     * @return 结果
     */
    public int updateLitemallFeedback(LitemallFeedback litemallFeedback);

    /**
     * 删除意见反馈
     * 
     * @param id 意见反馈ID
     * @return 结果
     */
    public int deleteLitemallFeedbackById(Long id);

    /**
     * 批量删除意见反馈
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallFeedbackByIds(Long[] ids);
}
