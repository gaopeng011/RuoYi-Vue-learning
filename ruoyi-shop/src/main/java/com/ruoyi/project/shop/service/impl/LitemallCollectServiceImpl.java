package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallCollect;
import com.ruoyi.project.shop.mapper.LitemallCollectMapper;
import com.ruoyi.project.shop.service.ILitemallCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收藏Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallCollectServiceImpl implements ILitemallCollectService 
{
    @Autowired
    private LitemallCollectMapper litemallCollectMapper;

    /**
     * 查询收藏
     * 
     * @param id 收藏ID
     * @return 收藏
     */
    @Override
    public LitemallCollect selectLitemallCollectById(Long id)
    {
        return litemallCollectMapper.selectLitemallCollectById(id);
    }

    /**
     * 查询收藏列表
     * 
     * @param litemallCollect 收藏
     * @return 收藏
     */
    @Override
    public List<LitemallCollect> selectLitemallCollectList(LitemallCollect litemallCollect)
    {
        return litemallCollectMapper.selectLitemallCollectList(litemallCollect);
    }

    /**
     * 新增收藏
     * 
     * @param litemallCollect 收藏
     * @return 结果
     */
    @Override
    public int insertLitemallCollect(LitemallCollect litemallCollect)
    {
        return litemallCollectMapper.insertLitemallCollect(litemallCollect);
    }

    /**
     * 修改收藏
     * 
     * @param litemallCollect 收藏
     * @return 结果
     */
    @Override
    public int updateLitemallCollect(LitemallCollect litemallCollect)
    {
        litemallCollect.setUpdateTime(DateUtils.getNowDate());
        return litemallCollectMapper.updateLitemallCollect(litemallCollect);
    }

    /**
     * 批量删除收藏
     * 
     * @param ids 需要删除的收藏ID
     * @return 结果
     */
    @Override
    public int deleteLitemallCollectByIds(Long[] ids)
    {
        return litemallCollectMapper.deleteLitemallCollectByIds(ids);
    }

    /**
     * 删除收藏信息
     * 
     * @param id 收藏ID
     * @return 结果
     */
    @Override
    public int deleteLitemallCollectById(Long id)
    {
        return litemallCollectMapper.deleteLitemallCollectById(id);
    }
}
