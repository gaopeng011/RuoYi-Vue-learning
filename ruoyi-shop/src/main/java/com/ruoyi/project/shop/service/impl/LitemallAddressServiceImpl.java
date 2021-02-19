package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallAddress;
import com.ruoyi.project.shop.mapper.LitemallAddressMapper;
import com.ruoyi.project.shop.service.ILitemallAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallAddressServiceImpl implements ILitemallAddressService 
{
    @Autowired
    private LitemallAddressMapper litemallAddressMapper;

    /**
     * 查询收货地址
     * 
     * @param id 收货地址ID
     * @return 收货地址
     */
    @Override
    public LitemallAddress selectLitemallAddressById(Long id)
    {
        return litemallAddressMapper.selectLitemallAddressById(id);
    }

    /**
     * 查询收货地址列表
     * 
     * @param litemallAddress 收货地址
     * @return 收货地址
     */
    @Override
    public List<LitemallAddress> selectLitemallAddressList(LitemallAddress litemallAddress)
    {
        return litemallAddressMapper.selectLitemallAddressList(litemallAddress);
    }

    /**
     * 新增收货地址
     * 
     * @param litemallAddress 收货地址
     * @return 结果
     */
    @Override
    public int insertLitemallAddress(LitemallAddress litemallAddress)
    {
        return litemallAddressMapper.insertLitemallAddress(litemallAddress);
    }

    /**
     * 修改收货地址
     * 
     * @param litemallAddress 收货地址
     * @return 结果
     */
    @Override
    public int updateLitemallAddress(LitemallAddress litemallAddress)
    {
        litemallAddress.setUpdateTime(DateUtils.getNowDate());
        return litemallAddressMapper.updateLitemallAddress(litemallAddress);
    }

    /**
     * 批量删除收货地址
     * 
     * @param ids 需要删除的收货地址ID
     * @return 结果
     */
    @Override
    public int deleteLitemallAddressByIds(Long[] ids)
    {
        return litemallAddressMapper.deleteLitemallAddressByIds(ids);
    }

    /**
     * 删除收货地址信息
     * 
     * @param id 收货地址ID
     * @return 结果
     */
    @Override
    public int deleteLitemallAddressById(Long id)
    {
        return litemallAddressMapper.deleteLitemallAddressById(id);
    }
}
