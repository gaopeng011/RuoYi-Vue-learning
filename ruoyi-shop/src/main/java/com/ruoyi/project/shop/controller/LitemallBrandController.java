package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallBrand;
import com.ruoyi.project.shop.service.ILitemallBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌商Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/brand")
public class LitemallBrandController extends BaseController
{
    @Autowired
    private ILitemallBrandService litemallBrandService;

    /**
     * 查询品牌商列表
     */
    @PreAuthorize("@ss.hasPermi('shop:brand:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallBrand litemallBrand)
    {
        startPage();
        List<LitemallBrand> list = litemallBrandService.selectLitemallBrandList(litemallBrand);
        return getDataTable(list);
    }

    /**
     * 导出品牌商列表
     */
    @PreAuthorize("@ss.hasPermi('shop:brand:export')")
    @Log(title = "品牌商", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallBrand litemallBrand)
    {
        List<LitemallBrand> list = litemallBrandService.selectLitemallBrandList(litemallBrand);
        ExcelUtil<LitemallBrand> util = new ExcelUtil<LitemallBrand>(LitemallBrand.class);
        return util.exportExcel(list, "brand");
    }

    /**
     * 获取品牌商详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:brand:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallBrandService.selectLitemallBrandById(id));
    }

    /**
     * 新增品牌商
     */
    @PreAuthorize("@ss.hasPermi('shop:brand:add')")
    @Log(title = "品牌商", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallBrand litemallBrand)
    {
        return toAjax(litemallBrandService.insertLitemallBrand(litemallBrand));
    }

    /**
     * 修改品牌商
     */
    @PreAuthorize("@ss.hasPermi('shop:brand:edit')")
    @Log(title = "品牌商", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallBrand litemallBrand)
    {
        return toAjax(litemallBrandService.updateLitemallBrand(litemallBrand));
    }

    /**
     * 删除品牌商
     */
    @PreAuthorize("@ss.hasPermi('shop:brand:remove')")
    @Log(title = "品牌商", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return toAjax(litemallBrandService.deleteLitemallBrandById(id));
    }
}
