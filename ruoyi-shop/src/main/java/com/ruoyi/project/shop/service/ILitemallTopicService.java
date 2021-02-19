package com.ruoyi.project.shop.service;

import com.ruoyi.project.shop.domain.LitemallTopic;

import java.util.List;

/**
 * 专题Service接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface ILitemallTopicService 
{
    /**
     * 查询专题
     * 
     * @param id 专题ID
     * @return 专题
     */
    public LitemallTopic selectLitemallTopicById(Long id);

    /**
     * 查询专题列表
     * 
     * @param litemallTopic 专题
     * @return 专题集合
     */
    public List<LitemallTopic> selectLitemallTopicList(LitemallTopic litemallTopic);

    /**
     * 新增专题
     * 
     * @param litemallTopic 专题
     * @return 结果
     */
    public int insertLitemallTopic(LitemallTopic litemallTopic);

    /**
     * 修改专题
     * 
     * @param litemallTopic 专题
     * @return 结果
     */
    public int updateLitemallTopic(LitemallTopic litemallTopic);

    /**
     * 批量删除专题
     * 
     * @param ids 需要删除的专题ID
     * @return 结果
     */
    public int deleteLitemallTopicByIds(Long[] ids);

    /**
     * 删除专题信息
     * 
     * @param id 专题ID
     * @return 结果
     */
    public int deleteLitemallTopicById(Long id);
}
