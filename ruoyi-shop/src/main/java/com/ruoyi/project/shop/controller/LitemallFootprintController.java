package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallFootprint;
import com.ruoyi.project.shop.service.ILitemallFootprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户浏览足迹Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/footprint")
public class LitemallFootprintController extends BaseController
{
    @Autowired
    private ILitemallFootprintService litemallFootprintService;

    /**
     * 查询用户浏览足迹列表
     */
    @PreAuthorize("@ss.hasPermi('shop:footprint:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallFootprint litemallFootprint)
    {
        startPage();
        List<LitemallFootprint> list = litemallFootprintService.selectLitemallFootprintList(litemallFootprint);
        return getDataTable(list);
    }

    /**
     * 导出用户浏览足迹列表
     */
    @PreAuthorize("@ss.hasPermi('shop:footprint:export')")
    @Log(title = "用户浏览足迹", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallFootprint litemallFootprint)
    {
        List<LitemallFootprint> list = litemallFootprintService.selectLitemallFootprintList(litemallFootprint);
        ExcelUtil<LitemallFootprint> util = new ExcelUtil<LitemallFootprint>(LitemallFootprint.class);
        return util.exportExcel(list, "footprint");
    }

    /**
     * 获取用户浏览足迹详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:footprint:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallFootprintService.selectLitemallFootprintById(id));
    }

    /**
     * 新增用户浏览足迹
     */
    @PreAuthorize("@ss.hasPermi('shop:footprint:add')")
    @Log(title = "用户浏览足迹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallFootprint litemallFootprint)
    {
        return toAjax(litemallFootprintService.insertLitemallFootprint(litemallFootprint));
    }

    /**
     * 修改用户浏览足迹
     */
    @PreAuthorize("@ss.hasPermi('shop:footprint:edit')")
    @Log(title = "用户浏览足迹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallFootprint litemallFootprint)
    {
        return toAjax(litemallFootprintService.updateLitemallFootprint(litemallFootprint));
    }

    /**
     * 删除用户浏览足迹
     */
    @PreAuthorize("@ss.hasPermi('shop:footprint:remove')")
    @Log(title = "用户浏览足迹", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallFootprintService.deleteLitemallFootprintByIds(ids));
    }
}
