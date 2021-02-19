package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallStorage;
import com.ruoyi.project.shop.mapper.LitemallStorageMapper;
import com.ruoyi.project.shop.service.ILitemallStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件存储Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallStorageServiceImpl implements ILitemallStorageService
{
    @Autowired
    private LitemallStorageMapper litemallStorageMapper;

    /**
     * 查询文件存储
     *
     * @param id 文件存储ID
     * @return 文件存储
     */
    @Override
    public LitemallStorage selectLitemallStorageById(Long id)
    {
        return litemallStorageMapper.selectLitemallStorageById(id);
    }

    /**
     * 查询文件存储列表
     *
     * @param litemallStorage 文件存储
     * @return 文件存储
     */
    @Override
    public List<LitemallStorage> selectLitemallStorageList(LitemallStorage litemallStorage)
    {
        return litemallStorageMapper.selectLitemallStorageList(litemallStorage);
    }

    @Override
    public LitemallStorage selectLitemallStorageByKey(LitemallStorage litemallStorage) {
        return litemallStorageMapper.selectLitemallStorageByKey(litemallStorage);
    }

    /**
     * 新增文件存储
     *
     * @param litemallStorage 文件存储
     * @return 结果
     */
    @Override
    public int insertLitemallStorage(LitemallStorage litemallStorage)
    {
        return litemallStorageMapper.insertLitemallStorage(litemallStorage);
    }

    /**
     * 修改文件存储
     *
     * @param litemallStorage 文件存储
     * @return 结果
     */
    @Override
    public int updateLitemallStorage(LitemallStorage litemallStorage)
    {
        litemallStorage.setUpdateTime(DateUtils.getNowDate());
        return litemallStorageMapper.updateLitemallStorage(litemallStorage);
    }

    /**
     * 批量删除文件存储
     *
     * @param ids 需要删除的文件存储ID
     * @return 结果
     */
    @Override
    public int deleteLitemallStorageByIds(Long[] ids)
    {
        return litemallStorageMapper.deleteLitemallStorageByIds(ids);
    }

    /**
     * 删除文件存储信息
     *
     * @param id 文件存储ID
     * @return 结果
     */
    @Override
    public int deleteLitemallStorageById(Long id)
    {
        return litemallStorageMapper.deleteLitemallStorageById(id);
    }
}
