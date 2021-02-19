package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallStorage;

import java.util.List;

/**
 * 文件存储Mapper接口
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallStorageMapper
{
    /**
     * 查询文件存储
     *
     * @param id 文件存储ID
     * @return 文件存储
     */
    public LitemallStorage selectLitemallStorageById(Long id);


    public  LitemallStorage selectLitemallStorageByKey(LitemallStorage litemallStorage);

    /**
     * 查询文件存储列表
     *
     * @param litemallStorage 文件存储
     * @return 文件存储集合
     */
    public List<LitemallStorage> selectLitemallStorageList(LitemallStorage litemallStorage);

    /**
     * 新增文件存储
     *
     * @param litemallStorage 文件存储
     * @return 结果
     */
    public int insertLitemallStorage(LitemallStorage litemallStorage);

    /**
     * 修改文件存储
     *
     * @param litemallStorage 文件存储
     * @return 结果
     */
    public int updateLitemallStorage(LitemallStorage litemallStorage);

    /**
     * 删除文件存储
     *
     * @param id 文件存储ID
     * @return 结果
     */
    public int deleteLitemallStorageById(Long id);

    /**
     * 批量删除文件存储
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallStorageByIds(Long[] ids);
}
