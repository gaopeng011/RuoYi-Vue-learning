package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallGroupon;
import com.ruoyi.project.shop.service.ILitemallGrouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 团购活动Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/groupon")
public class LitemallGrouponController extends BaseController
{
    @Autowired
    private ILitemallGrouponService litemallGrouponService;

    /**
     * 查询团购活动列表
     */
    @PreAuthorize("@ss.hasPermi('shop:groupon:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallGroupon litemallGroupon)
    {
        startPage();
        List<LitemallGroupon> list = litemallGrouponService.selectLitemallGrouponList(litemallGroupon);
        return getDataTable(list);
    }

    /**
     * 导出团购活动列表
     */
    @PreAuthorize("@ss.hasPermi('shop:groupon:export')")
    @Log(title = "团购活动", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallGroupon litemallGroupon)
    {
        List<LitemallGroupon> list = litemallGrouponService.selectLitemallGrouponList(litemallGroupon);
        ExcelUtil<LitemallGroupon> util = new ExcelUtil<LitemallGroupon>(LitemallGroupon.class);
        return util.exportExcel(list, "groupon");
    }

    /**
     * 获取团购活动详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:groupon:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallGrouponService.selectLitemallGrouponById(id));
    }

    /**
     * 新增团购活动
     */
    @PreAuthorize("@ss.hasPermi('shop:groupon:add')")
    @Log(title = "团购活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallGroupon litemallGroupon)
    {
        return toAjax(litemallGrouponService.insertLitemallGroupon(litemallGroupon));
    }

    /**
     * 修改团购活动
     */
    @PreAuthorize("@ss.hasPermi('shop:groupon:edit')")
    @Log(title = "团购活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallGroupon litemallGroupon)
    {
        return toAjax(litemallGrouponService.updateLitemallGroupon(litemallGroupon));
    }

    /**
     * 删除团购活动
     */
    @PreAuthorize("@ss.hasPermi('shop:groupon:remove')")
    @Log(title = "团购活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallGrouponService.deleteLitemallGrouponByIds(ids));
    }
}
