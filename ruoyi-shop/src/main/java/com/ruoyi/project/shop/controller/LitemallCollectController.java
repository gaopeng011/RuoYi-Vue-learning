package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallCollect;
import com.ruoyi.project.shop.service.ILitemallCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收藏Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/collect")
public class LitemallCollectController extends BaseController
{
    @Autowired
    private ILitemallCollectService litemallCollectService;

    /**
     * 查询收藏列表
     */
    @PreAuthorize("@ss.hasPermi('shop:collect:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallCollect litemallCollect)
    {
        startPage();
        List<LitemallCollect> list = litemallCollectService.selectLitemallCollectList(litemallCollect);
        return getDataTable(list);
    }

    /**
     * 导出收藏列表
     */
    @PreAuthorize("@ss.hasPermi('shop:collect:export')")
    @Log(title = "收藏", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallCollect litemallCollect)
    {
        List<LitemallCollect> list = litemallCollectService.selectLitemallCollectList(litemallCollect);
        ExcelUtil<LitemallCollect> util = new ExcelUtil<LitemallCollect>(LitemallCollect.class);
        return util.exportExcel(list, "collect");
    }

    /**
     * 获取收藏详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:collect:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallCollectService.selectLitemallCollectById(id));
    }

    /**
     * 新增收藏
     */
    @PreAuthorize("@ss.hasPermi('shop:collect:add')")
    @Log(title = "收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallCollect litemallCollect)
    {
        return toAjax(litemallCollectService.insertLitemallCollect(litemallCollect));
    }

    /**
     * 修改收藏
     */
    @PreAuthorize("@ss.hasPermi('shop:collect:edit')")
    @Log(title = "收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallCollect litemallCollect)
    {
        return toAjax(litemallCollectService.updateLitemallCollect(litemallCollect));
    }

    /**
     * 删除收藏
     */
    @PreAuthorize("@ss.hasPermi('shop:collect:remove')")
    @Log(title = "收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallCollectService.deleteLitemallCollectByIds(ids));
    }
}
