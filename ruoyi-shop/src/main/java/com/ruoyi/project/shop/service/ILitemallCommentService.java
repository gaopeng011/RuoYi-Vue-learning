package com.ruoyi.project.shop.service;

import com.ruoyi.project.shop.domain.LitemallComment;

import java.util.List;

/**
 * 评论Service接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface ILitemallCommentService 
{
    /**
     * 查询评论
     * 
     * @param id 评论ID
     * @return 评论
     */
    public LitemallComment selectLitemallCommentById(Long id);

    /**
     * 查询评论列表
     * 
     * @param litemallComment 评论
     * @return 评论集合
     */
    public List<LitemallComment> selectLitemallCommentList(LitemallComment litemallComment);

    /**
     * 新增评论
     * 
     * @param litemallComment 评论
     * @return 结果
     */
    public int insertLitemallComment(LitemallComment litemallComment);

    /**
     * 修改评论
     * 
     * @param litemallComment 评论
     * @return 结果
     */
    public int updateLitemallComment(LitemallComment litemallComment);

    /**
     * 批量删除评论
     * 
     * @param ids 需要删除的评论ID
     * @return 结果
     */
    public int deleteLitemallCommentByIds(Long[] ids);

    /**
     * 删除评论信息
     * 
     * @param id 评论ID
     * @return 结果
     */
    public int deleteLitemallCommentById(Long id);
}
