package com.ruoyi.project.shop.service;

import com.ruoyi.project.shop.domain.LitemallAddress;

import java.util.List;

/**
 * 收货地址Service接口
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
public interface ILitemallAddressService 
{
    /**
     * 查询收货地址
     * 
     * @param id 收货地址ID
     * @return 收货地址
     */
    public LitemallAddress selectLitemallAddressById(Long id);

    /**
     * 查询收货地址列表
     * 
     * @param litemallAddress 收货地址
     * @return 收货地址集合
     */
    public List<LitemallAddress> selectLitemallAddressList(LitemallAddress litemallAddress);

    /**
     * 新增收货地址
     * 
     * @param litemallAddress 收货地址
     * @return 结果
     */
    public int insertLitemallAddress(LitemallAddress litemallAddress);

    /**
     * 修改收货地址
     * 
     * @param litemallAddress 收货地址
     * @return 结果
     */
    public int updateLitemallAddress(LitemallAddress litemallAddress);

    /**
     * 批量删除收货地址
     * 
     * @param ids 需要删除的收货地址ID
     * @return 结果
     */
    public int deleteLitemallAddressByIds(Long[] ids);

    /**
     * 删除收货地址信息
     * 
     * @param id 收货地址ID
     * @return 结果
     */
    public int deleteLitemallAddressById(Long id);
}
