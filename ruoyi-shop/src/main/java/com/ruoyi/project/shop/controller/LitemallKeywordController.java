package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallKeyword;
import com.ruoyi.project.shop.service.ILitemallKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 关键字Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/keyword")
public class LitemallKeywordController extends BaseController
{
    @Autowired
    private ILitemallKeywordService litemallKeywordService;

    /**
     * 查询关键字列表
     */
    @PreAuthorize("@ss.hasPermi('shop:keyword:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallKeyword litemallKeyword)
    {
        startPage();
        List<LitemallKeyword> list = litemallKeywordService.selectLitemallKeywordList(litemallKeyword);
        return getDataTable(list);
    }

    /**
     * 导出关键字列表
     */
    @PreAuthorize("@ss.hasPermi('shop:keyword:export')")
    @Log(title = "关键字", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallKeyword litemallKeyword)
    {
        List<LitemallKeyword> list = litemallKeywordService.selectLitemallKeywordList(litemallKeyword);
        ExcelUtil<LitemallKeyword> util = new ExcelUtil<LitemallKeyword>(LitemallKeyword.class);
        return util.exportExcel(list, "keyword");
    }

    /**
     * 获取关键字详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:keyword:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallKeywordService.selectLitemallKeywordById(id));
    }

    /**
     * 新增关键字
     */
    @PreAuthorize("@ss.hasPermi('shop:keyword:add')")
    @Log(title = "关键字", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallKeyword litemallKeyword)
    {
        return toAjax(litemallKeywordService.insertLitemallKeyword(litemallKeyword));
    }

    /**
     * 修改关键字
     */
    @PreAuthorize("@ss.hasPermi('shop:keyword:edit')")
    @Log(title = "关键字", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallKeyword litemallKeyword)
    {
        return toAjax(litemallKeywordService.updateLitemallKeyword(litemallKeyword));
    }

    /**
     * 删除关键字
     */
    @PreAuthorize("@ss.hasPermi('shop:keyword:remove')")
    @Log(title = "关键字", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallKeywordService.deleteLitemallKeywordByIds(ids));
    }
}
