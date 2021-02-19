package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallRegion;
import com.ruoyi.project.shop.service.ILitemallRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 行政区域Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/region")
public class LitemallRegionController extends BaseController
{
    @Autowired
    private ILitemallRegionService litemallRegionService;

    /**
     * 查询行政区域列表
     */
    @PreAuthorize("@ss.hasPermi('shop:region:list')")
    @GetMapping("/list")
    public AjaxResult list(LitemallRegion litemallRegion)
    {
        List<LitemallRegion> list = litemallRegionService.selectLitemallRegionList(litemallRegion);
        return AjaxResult.success(list);
    }



    //获取行政区域级联
    @PreAuthorize("@ss.hasPermi('shop:region:list')")
    @GetMapping("/listRegoin")
    public AjaxResult listRegoin(LitemallRegion litemallRegion)
    {
        List<LitemallRegion> list = litemallRegionService.selectLitemallRegionList(litemallRegion);
        return AjaxResult.success(list);
    }
    /**
     * 导出行政区域列表
     */
    @PreAuthorize("@ss.hasPermi('shop:region:export')")
    @Log(title = "行政区域", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallRegion litemallRegion)
    {
        List<LitemallRegion> list = litemallRegionService.selectLitemallRegionList(litemallRegion);
        ExcelUtil<LitemallRegion> util = new ExcelUtil<LitemallRegion>(LitemallRegion.class);
        return util.exportExcel(list, "region");
    }

    /**
     * 获取行政区域详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:region:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallRegionService.selectLitemallRegionById(id));
    }

    /**
     * 新增行政区域
     */
    @PreAuthorize("@ss.hasPermi('shop:region:add')")
    @Log(title = "行政区域", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallRegion litemallRegion)
    {
        return toAjax(litemallRegionService.insertLitemallRegion(litemallRegion));
    }

    /**
     * 修改行政区域
     */
    @PreAuthorize("@ss.hasPermi('shop:region:edit')")
    @Log(title = "行政区域", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallRegion litemallRegion)
    {
        return toAjax(litemallRegionService.updateLitemallRegion(litemallRegion));
    }

    /**
     * 删除行政区域
     */
    @PreAuthorize("@ss.hasPermi('shop:region:remove')")
    @Log(title = "行政区域", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallRegionService.deleteLitemallRegionByIds(ids));
    }
}
