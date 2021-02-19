package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallGoodsSpecification;
import com.ruoyi.project.shop.service.ILitemallGoodsSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品规格Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/specification")
public class LitemallGoodsSpecificationController extends BaseController
{
    @Autowired
    private ILitemallGoodsSpecificationService litemallGoodsSpecificationService;

    /**
     * 查询商品规格列表
     */
    @PreAuthorize("@ss.hasPermi('shop:specification:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallGoodsSpecification litemallGoodsSpecification)
    {
        startPage();
        List<LitemallGoodsSpecification> list = litemallGoodsSpecificationService.selectLitemallGoodsSpecificationList(litemallGoodsSpecification);
        return getDataTable(list);
    }

    /**
     * 导出商品规格列表
     */
    @PreAuthorize("@ss.hasPermi('shop:specification:export')")
    @Log(title = "商品规格", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallGoodsSpecification litemallGoodsSpecification)
    {
        List<LitemallGoodsSpecification> list = litemallGoodsSpecificationService.selectLitemallGoodsSpecificationList(litemallGoodsSpecification);
        ExcelUtil<LitemallGoodsSpecification> util = new ExcelUtil<LitemallGoodsSpecification>(LitemallGoodsSpecification.class);
        return util.exportExcel(list, "specification");
    }

    /**
     * 获取商品规格详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:specification:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallGoodsSpecificationService.selectLitemallGoodsSpecificationById(id));
    }

    /**
     * 新增商品规格
     */
    @PreAuthorize("@ss.hasPermi('shop:specification:add')")
    @Log(title = "商品规格", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallGoodsSpecification litemallGoodsSpecification)
    {
        return toAjax(litemallGoodsSpecificationService.insertLitemallGoodsSpecification(litemallGoodsSpecification));
    }

    /**
     * 修改商品规格
     */
    @PreAuthorize("@ss.hasPermi('shop:specification:edit')")
    @Log(title = "商品规格", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallGoodsSpecification litemallGoodsSpecification)
    {
        return toAjax(litemallGoodsSpecificationService.updateLitemallGoodsSpecification(litemallGoodsSpecification));
    }

    /**
     * 删除商品规格
     */
    @PreAuthorize("@ss.hasPermi('shop:specification:remove')")
    @Log(title = "商品规格", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallGoodsSpecificationService.deleteLitemallGoodsSpecificationByIds(ids));
    }
}
