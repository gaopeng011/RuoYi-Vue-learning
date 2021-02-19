package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallAdmin;
import com.ruoyi.project.shop.mapper.LitemallAdminMapper;
import com.ruoyi.project.shop.service.ILitemallAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallAdminServiceImpl implements ILitemallAdminService 
{
    @Autowired
    private LitemallAdminMapper litemallAdminMapper;

    /**
     * 查询管理员
     * 
     * @param id 管理员ID
     * @return 管理员
     */
    @Override
    public LitemallAdmin selectLitemallAdminById(Long id)
    {
        return litemallAdminMapper.selectLitemallAdminById(id);
    }

    /**
     * 查询管理员列表
     * 
     * @param litemallAdmin 管理员
     * @return 管理员
     */
    @Override
    public List<LitemallAdmin> selectLitemallAdminList(LitemallAdmin litemallAdmin)
    {
        return litemallAdminMapper.selectLitemallAdminList(litemallAdmin);
    }

    /**
     * 新增管理员
     * 
     * @param litemallAdmin 管理员
     * @return 结果
     */
    @Override
    public int insertLitemallAdmin(LitemallAdmin litemallAdmin)
    {
        return litemallAdminMapper.insertLitemallAdmin(litemallAdmin);
    }

    /**
     * 修改管理员
     * 
     * @param litemallAdmin 管理员
     * @return 结果
     */
    @Override
    public int updateLitemallAdmin(LitemallAdmin litemallAdmin)
    {
        litemallAdmin.setUpdateTime(DateUtils.getNowDate());
        return litemallAdminMapper.updateLitemallAdmin(litemallAdmin);
    }

    /**
     * 批量删除管理员
     * 
     * @param ids 需要删除的管理员ID
     * @return 结果
     */
    @Override
    public int deleteLitemallAdminByIds(Long[] ids)
    {
        return litemallAdminMapper.deleteLitemallAdminByIds(ids);
    }

    /**
     * 删除管理员信息
     * 
     * @param id 管理员ID
     * @return 结果
     */
    @Override
    public int deleteLitemallAdminById(Long id)
    {
        return litemallAdminMapper.deleteLitemallAdminById(id);
    }
}
