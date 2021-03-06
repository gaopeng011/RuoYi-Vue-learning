package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallUser;

import java.util.List;

/**
 * 用户Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallUserMapper 
{
    /**
     * 查询用户
     * 
     * @param id 用户ID
     * @return 用户
     */
    public LitemallUser selectLitemallUserById(Long id);

    /**
     * 查询用户列表
     * 
     * @param litemallUser 用户
     * @return 用户集合
     */
    public List<LitemallUser> selectLitemallUserList(LitemallUser litemallUser);

    /**
     * 新增用户
     * 
     * @param litemallUser 用户
     * @return 结果
     */
    public int insertLitemallUser(LitemallUser litemallUser);

    /**
     * 修改用户
     * 
     * @param litemallUser 用户
     * @return 结果
     */
    public int updateLitemallUser(LitemallUser litemallUser);

    /**
     * 删除用户
     * 
     * @param id 用户ID
     * @return 结果
     */
    public int deleteLitemallUserById(Long id);

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallUserByIds(Long[] ids);
}
