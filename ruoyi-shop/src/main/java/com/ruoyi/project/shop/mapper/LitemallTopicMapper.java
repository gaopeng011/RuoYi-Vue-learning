package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallTopic;

import java.util.List;

/**
 * 专题Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallTopicMapper 
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
     * 删除专题
     * 
     * @param id 专题ID
     * @return 结果
     */
    public int deleteLitemallTopicById(Long id);

    /**
     * 批量删除专题
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallTopicByIds(Long[] ids);
}
