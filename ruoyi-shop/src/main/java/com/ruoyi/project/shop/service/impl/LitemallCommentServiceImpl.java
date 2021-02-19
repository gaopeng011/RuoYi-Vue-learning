package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallComment;
import com.ruoyi.project.shop.mapper.LitemallCommentMapper;
import com.ruoyi.project.shop.service.ILitemallCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallCommentServiceImpl implements ILitemallCommentService 
{
    @Autowired
    private LitemallCommentMapper litemallCommentMapper;

    /**
     * 查询评论
     * 
     * @param id 评论ID
     * @return 评论
     */
    @Override
    public LitemallComment selectLitemallCommentById(Long id)
    {
        return litemallCommentMapper.selectLitemallCommentById(id);
    }

    /**
     * 查询评论列表
     * 
     * @param litemallComment 评论
     * @return 评论
     */
    @Override
    public List<LitemallComment> selectLitemallCommentList(LitemallComment litemallComment)
    {
        return litemallCommentMapper.selectLitemallCommentList(litemallComment);
    }

    /**
     * 新增评论
     * 
     * @param litemallComment 评论
     * @return 结果
     */
    @Override
    public int insertLitemallComment(LitemallComment litemallComment)
    {
        return litemallCommentMapper.insertLitemallComment(litemallComment);
    }

    /**
     * 修改评论
     * 
     * @param litemallComment 评论
     * @return 结果
     */
    @Override
    public int updateLitemallComment(LitemallComment litemallComment)
    {
        litemallComment.setUpdateTime(DateUtils.getNowDate());
        return litemallCommentMapper.updateLitemallComment(litemallComment);
    }

    /**
     * 批量删除评论
     * 
     * @param ids 需要删除的评论ID
     * @return 结果
     */
    @Override
    public int deleteLitemallCommentByIds(Long[] ids)
    {
        return litemallCommentMapper.deleteLitemallCommentByIds(ids);
    }

    /**
     * 删除评论信息
     * 
     * @param id 评论ID
     * @return 结果
     */
    @Override
    public int deleteLitemallCommentById(Long id)
    {
        return litemallCommentMapper.deleteLitemallCommentById(id);
    }
}
