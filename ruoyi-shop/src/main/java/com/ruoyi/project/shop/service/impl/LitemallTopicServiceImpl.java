package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallTopic;
import com.ruoyi.project.shop.mapper.LitemallTopicMapper;
import com.ruoyi.project.shop.service.ILitemallTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 专题Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallTopicServiceImpl implements ILitemallTopicService 
{
    @Autowired
    private LitemallTopicMapper litemallTopicMapper;

    /**
     * 查询专题
     * 
     * @param id 专题ID
     * @return 专题
     */
    @Override
    public LitemallTopic selectLitemallTopicById(Long id)
    {
        return litemallTopicMapper.selectLitemallTopicById(id);
    }

    /**
     * 查询专题列表
     * 
     * @param litemallTopic 专题
     * @return 专题
     */
    @Override
    public List<LitemallTopic> selectLitemallTopicList(LitemallTopic litemallTopic)
    {
        return litemallTopicMapper.selectLitemallTopicList(litemallTopic);
    }

    /**
     * 新增专题
     * 
     * @param litemallTopic 专题
     * @return 结果
     */
    @Override
    public int insertLitemallTopic(LitemallTopic litemallTopic)
    {
        return litemallTopicMapper.insertLitemallTopic(litemallTopic);
    }

    /**
     * 修改专题
     * 
     * @param litemallTopic 专题
     * @return 结果
     */
    @Override
    public int updateLitemallTopic(LitemallTopic litemallTopic)
    {
        litemallTopic.setUpdateTime(DateUtils.getNowDate());
        return litemallTopicMapper.updateLitemallTopic(litemallTopic);
    }

    /**
     * 批量删除专题
     * 
     * @param ids 需要删除的专题ID
     * @return 结果
     */
    @Override
    public int deleteLitemallTopicByIds(Long[] ids)
    {
        return litemallTopicMapper.deleteLitemallTopicByIds(ids);
    }

    /**
     * 删除专题信息
     * 
     * @param id 专题ID
     * @return 结果
     */
    @Override
    public int deleteLitemallTopicById(Long id)
    {
        return litemallTopicMapper.deleteLitemallTopicById(id);
    }
}
