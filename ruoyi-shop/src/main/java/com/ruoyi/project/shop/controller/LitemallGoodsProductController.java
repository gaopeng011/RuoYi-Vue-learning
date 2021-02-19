package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallGoodsProduct;
import com.ruoyi.project.shop.service.ILitemallGoodsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 商品货品Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/product")
public class LitemallGoodsProductController extends BaseController
{
    @Autowired
    private ILitemallGoodsProductService litemallGoodsProductService;

    /**
     * 查询商品货品列表
     */
    @PreAuthorize("@ss.hasPermi('shop:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallGoodsProduct litemallGoodsProduct)
    {
        startPage();
        List<LitemallGoodsProduct> list = litemallGoodsProductService.selectLitemallGoodsProductList(litemallGoodsProduct);
        return getDataTable(list);
    }

    /**
     * 导出商品货品列表
     */
    @PreAuthorize("@ss.hasPermi('shop:product:export')")
    @Log(title = "商品货品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallGoodsProduct litemallGoodsProduct)
    {
        List<LitemallGoodsProduct> list = litemallGoodsProductService.selectLitemallGoodsProductList(litemallGoodsProduct);
        ExcelUtil<LitemallGoodsProduct> util = new ExcelUtil<LitemallGoodsProduct>(LitemallGoodsProduct.class);
        return util.exportExcel(list, "product");
    }

    /**
     * 获取商品货品详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:product:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallGoodsProductService.selectLitemallGoodsProductById(id));
    }

    /**
     * 新增商品货品
     */
    @PreAuthorize("@ss.hasPermi('shop:product:add')")
    @Log(title = "商品货品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallGoodsProduct litemallGoodsProduct)
    {
        return toAjax(litemallGoodsProductService.insertLitemallGoodsProduct(litemallGoodsProduct));
    }

    /**
     * 修改商品货品
     */
    @PreAuthorize("@ss.hasPermi('shop:product:edit')")
    @Log(title = "商品货品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallGoodsProduct litemallGoodsProduct)
    {
        return toAjax(litemallGoodsProductService.updateLitemallGoodsProduct(litemallGoodsProduct));
    }

    /**
     * 删除商品货品
     */
    @PreAuthorize("@ss.hasPermi('shop:product:remove')")
    @Log(title = "商品货品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallGoodsProductService.deleteLitemallGoodsProductByIds(ids));
    }
}
