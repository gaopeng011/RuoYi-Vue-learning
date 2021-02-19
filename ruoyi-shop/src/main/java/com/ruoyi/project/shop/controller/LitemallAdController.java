package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallAd;
import com.ruoyi.project.shop.service.ILitemallAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 广告Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/ad")
public class LitemallAdController extends BaseController
{
    @Autowired
    private ILitemallAdService litemallAdService;

    /**
     * 查询广告列表
     */
    @PreAuthorize("@ss.hasPermi('shop:ad:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallAd litemallAd)
    {
        startPage();
        List<LitemallAd> list = litemallAdService.selectLitemallAdList(litemallAd);
        return getDataTable(list);
    }

    /**
     * 导出广告列表
     */
    @PreAuthorize("@ss.hasPermi('shop:ad:export')")
    @Log(title = "广告", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallAd litemallAd)
    {
        List<LitemallAd> list = litemallAdService.selectLitemallAdList(litemallAd);
        ExcelUtil<LitemallAd> util = new ExcelUtil<LitemallAd>(LitemallAd.class);
        return util.exportExcel(list, "ad");
    }

    /**
     * 获取广告详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:ad:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallAdService.selectLitemallAdById(id));
    }

    /**
     * 新增广告
     */
    @PreAuthorize("@ss.hasPermi('shop:ad:add')")
    @Log(title = "广告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallAd litemallAd)
    {
        return toAjax(litemallAdService.insertLitemallAd(litemallAd));
    }

    /**
     * 修改广告
     */
    @PreAuthorize("@ss.hasPermi('shop:ad:edit')")
    @Log(title = "广告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallAd litemallAd)
    {
        return toAjax(litemallAdService.updateLitemallAd(litemallAd));
    }

    /**
     * 删除广告
     */
    @PreAuthorize("@ss.hasPermi('shop:ad:remove')")
    @Log(title = "广告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallAdService.deleteLitemallAdByIds(ids));
    }
}
