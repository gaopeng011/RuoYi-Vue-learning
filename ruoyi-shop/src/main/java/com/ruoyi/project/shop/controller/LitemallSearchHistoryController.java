package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallSearchHistory;
import com.ruoyi.project.shop.service.ILitemallSearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 搜索历史Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/history")
public class LitemallSearchHistoryController extends BaseController
{
    @Autowired
    private ILitemallSearchHistoryService litemallSearchHistoryService;

    /**
     * 查询搜索历史列表
     */
    @PreAuthorize("@ss.hasPermi('shop:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallSearchHistory litemallSearchHistory)
    {
        startPage();
        List<LitemallSearchHistory> list = litemallSearchHistoryService.selectLitemallSearchHistoryList(litemallSearchHistory);
        return getDataTable(list);
    }

    /**
     * 导出搜索历史列表
     */
    @PreAuthorize("@ss.hasPermi('shop:history:export')")
    @Log(title = "搜索历史", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallSearchHistory litemallSearchHistory)
    {
        List<LitemallSearchHistory> list = litemallSearchHistoryService.selectLitemallSearchHistoryList(litemallSearchHistory);
        ExcelUtil<LitemallSearchHistory> util = new ExcelUtil<LitemallSearchHistory>(LitemallSearchHistory.class);
        return util.exportExcel(list, "history");
    }

    /**
     * 获取搜索历史详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:history:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallSearchHistoryService.selectLitemallSearchHistoryById(id));
    }

    /**
     * 新增搜索历史
     */
    @PreAuthorize("@ss.hasPermi('shop:history:add')")
    @Log(title = "搜索历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallSearchHistory litemallSearchHistory)
    {
        return toAjax(litemallSearchHistoryService.insertLitemallSearchHistory(litemallSearchHistory));
    }

    /**
     * 修改搜索历史
     */
    @PreAuthorize("@ss.hasPermi('shop:history:edit')")
    @Log(title = "搜索历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallSearchHistory litemallSearchHistory)
    {
        return toAjax(litemallSearchHistoryService.updateLitemallSearchHistory(litemallSearchHistory));
    }

    /**
     * 删除搜索历史
     */
    @PreAuthorize("@ss.hasPermi('shop:history:remove')")
    @Log(title = "搜索历史", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallSearchHistoryService.deleteLitemallSearchHistoryByIds(ids));
    }
}
