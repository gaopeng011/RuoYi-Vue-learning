package com.ruoyi.project.shop.service;

import com.ruoyi.project.shop.domain.LitemallAdmin;

import java.util.List;

/**
 * 管理员Service接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface ILitemallAdminService 
{
    /**
     * 查询管理员
     * 
     * @param id 管理员ID
     * @return 管理员
     */
    public LitemallAdmin selectLitemallAdminById(Long id);

    /**
     * 查询管理员列表
     * 
     * @param litemallAdmin 管理员
     * @return 管理员集合
     */
    public List<LitemallAdmin> selectLitemallAdminList(LitemallAdmin litemallAdmin);

    /**
     * 新增管理员
     * 
     * @param litemallAdmin 管理员
     * @return 结果
     */
    public int insertLitemallAdmin(LitemallAdmin litemallAdmin);

    /**
     * 修改管理员
     * 
     * @param litemallAdmin 管理员
     * @return 结果
     */
    public int updateLitemallAdmin(LitemallAdmin litemallAdmin);

    /**
     * 批量删除管理员
     * 
     * @param ids 需要删除的管理员ID
     * @return 结果
     */
    public int deleteLitemallAdminByIds(Long[] ids);

    /**
     * 删除管理员信息
     * 
     * @param id 管理员ID
     * @return 结果
     */
    public int deleteLitemallAdminById(Long id);
}
