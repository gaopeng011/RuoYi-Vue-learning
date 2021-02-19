package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallIssue;
import com.ruoyi.project.shop.service.ILitemallIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 常见问题Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/issue")
public class LitemallIssueController extends BaseController
{
    @Autowired
    private ILitemallIssueService litemallIssueService;

    /**
     * 查询常见问题列表
     */
    @PreAuthorize("@ss.hasPermi('shop:issue:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallIssue litemallIssue)
    {
        startPage();
        List<LitemallIssue> list = litemallIssueService.selectLitemallIssueList(litemallIssue);
        return getDataTable(list);
    }

    /**
     * 导出常见问题列表
     */
    @PreAuthorize("@ss.hasPermi('shop:issue:export')")
    @Log(title = "常见问题", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallIssue litemallIssue)
    {
        List<LitemallIssue> list = litemallIssueService.selectLitemallIssueList(litemallIssue);
        ExcelUtil<LitemallIssue> util = new ExcelUtil<LitemallIssue>(LitemallIssue.class);
        return util.exportExcel(list, "issue");
    }

    /**
     * 获取常见问题详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:issue:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallIssueService.selectLitemallIssueById(id));
    }

    /**
     * 新增常见问题
     */
    @PreAuthorize("@ss.hasPermi('shop:issue:add')")
    @Log(title = "常见问题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallIssue litemallIssue)
    {
        return toAjax(litemallIssueService.insertLitemallIssue(litemallIssue));
    }

    /**
     * 修改常见问题
     */
    @PreAuthorize("@ss.hasPermi('shop:issue:edit')")
    @Log(title = "常见问题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallIssue litemallIssue)
    {
        return toAjax(litemallIssueService.updateLitemallIssue(litemallIssue));
    }

    /**
     * 删除常见问题
     */
    @PreAuthorize("@ss.hasPermi('shop:issue:remove')")
    @Log(title = "常见问题", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallIssueService.deleteLitemallIssueByIds(ids));
    }
}
