package com.ruoyi.project.shop.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shop.domain.LitemallKeyword;
import com.ruoyi.project.shop.mapper.LitemallKeywordMapper;
import com.ruoyi.project.shop.service.ILitemallKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 关键字Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallKeywordServiceImpl implements ILitemallKeywordService 
{
    @Autowired
    private LitemallKeywordMapper litemallKeywordMapper;

    /**
     * 查询关键字
     * 
     * @param id 关键字ID
     * @return 关键字
     */
    @Override
    public LitemallKeyword selectLitemallKeywordById(Long id)
    {
        return litemallKeywordMapper.selectLitemallKeywordById(id);
    }

    /**
     * 查询关键字列表
     * 
     * @param litemallKeyword 关键字
     * @return 关键字
     */
    @Override
    public List<LitemallKeyword> selectLitemallKeywordList(LitemallKeyword litemallKeyword)
    {
        return litemallKeywordMapper.selectLitemallKeywordList(litemallKeyword);
    }

    /**
     * 新增关键字
     * 
     * @param litemallKeyword 关键字
     * @return 结果
     */
    @Override
    public int insertLitemallKeyword(LitemallKeyword litemallKeyword)
    {
        return litemallKeywordMapper.insertLitemallKeyword(litemallKeyword);
    }

    /**
     * 修改关键字
     * 
     * @param litemallKeyword 关键字
     * @return 结果
     */
    @Override
    public int updateLitemallKeyword(LitemallKeyword litemallKeyword)
    {
        litemallKeyword.setUpdateTime(DateUtils.getNowDate());
        return litemallKeywordMapper.updateLitemallKeyword(litemallKeyword);
    }

    /**
     * 批量删除关键字
     * 
     * @param ids 需要删除的关键字ID
     * @return 结果
     */
    @Override
    public int deleteLitemallKeywordByIds(Long[] ids)
    {
        return litemallKeywordMapper.deleteLitemallKeywordByIds(ids);
    }

    /**
     * 删除关键字信息
     * 
     * @param id 关键字ID
     * @return 结果
     */
    @Override
    public int deleteLitemallKeywordById(Long id)
    {
        return litemallKeywordMapper.deleteLitemallKeywordById(id);
    }
}
