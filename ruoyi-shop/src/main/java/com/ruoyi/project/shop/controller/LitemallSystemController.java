package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallSystem;
import com.ruoyi.project.shop.service.ILitemallSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统配置Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/config")
public class LitemallSystemController extends BaseController
{
    @Autowired
    private ILitemallSystemService litemallSystemService;

    /**
     * 查询系统配置列表
     */
    @PreAuthorize("@ss.hasPermi('shop:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallSystem litemallSystem)
    {
        startPage();
        List<LitemallSystem> list = litemallSystemService.selectLitemallSystemList(litemallSystem);
        return getDataTable(list);
    }

    /**
     * 导出系统配置列表
     */
    @PreAuthorize("@ss.hasPermi('shop:config:export')")
    @Log(title = "系统配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallSystem litemallSystem)
    {
        List<LitemallSystem> list = litemallSystemService.selectLitemallSystemList(litemallSystem);
        ExcelUtil<LitemallSystem> util = new ExcelUtil<LitemallSystem>(LitemallSystem.class);
        return util.exportExcel(list, "config");
    }

    /**
     * 获取系统配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:config:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallSystemService.selectLitemallSystemById(id));
    }

    /**
     * 新增系统配置
     */
    @PreAuthorize("@ss.hasPermi('shop:config:add')")
    @Log(title = "系统配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallSystem litemallSystem)
    {
        return toAjax(litemallSystemService.insertLitemallSystem(litemallSystem));
    }

    /**
     * 修改系统配置
     */
    @PreAuthorize("@ss.hasPermi('shop:config:edit')")
    @Log(title = "系统配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallSystem litemallSystem)
    {
        return toAjax(litemallSystemService.updateLitemallSystem(litemallSystem));
    }

    /**
     * 删除系统配置
     */
    @PreAuthorize("@ss.hasPermi('shop:config:remove')")
    @Log(title = "系统配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallSystemService.deleteLitemallSystemByIds(ids));
    }
}
