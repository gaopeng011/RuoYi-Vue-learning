package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallUser;
import com.ruoyi.project.shop.mapper.LitemallUserMapper;
import com.ruoyi.project.shop.service.ILitemallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallUserServiceImpl implements ILitemallUserService 
{
    @Autowired
    private LitemallUserMapper litemallUserMapper;

    /**
     * 查询用户
     * 
     * @param id 用户ID
     * @return 用户
     */
    @Override
    public LitemallUser selectLitemallUserById(Long id)
    {
        return litemallUserMapper.selectLitemallUserById(id);
    }

    /**
     * 查询用户列表
     * 
     * @param litemallUser 用户
     * @return 用户
     */
    @Override
    public List<LitemallUser> selectLitemallUserList(LitemallUser litemallUser)
    {
        return litemallUserMapper.selectLitemallUserList(litemallUser);
    }

    /**
     * 新增用户
     * 
     * @param litemallUser 用户
     * @return 结果
     */
    @Override
    public int insertLitemallUser(LitemallUser litemallUser)
    {
        return litemallUserMapper.insertLitemallUser(litemallUser);
    }

    /**
     * 修改用户
     * 
     * @param litemallUser 用户
     * @return 结果
     */
    @Override
    public int updateLitemallUser(LitemallUser litemallUser)
    {
        litemallUser.setUpdateTime(DateUtils.getNowDate());
        return litemallUserMapper.updateLitemallUser(litemallUser);
    }

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的用户ID
     * @return 结果
     */
    @Override
    public int deleteLitemallUserByIds(Long[] ids)
    {
        return litemallUserMapper.deleteLitemallUserByIds(ids);
    }

    /**
     * 删除用户信息
     * 
     * @param id 用户ID
     * @return 结果
     */
    @Override
    public int deleteLitemallUserById(Long id)
    {
        return litemallUserMapper.deleteLitemallUserById(id);
    }
}
