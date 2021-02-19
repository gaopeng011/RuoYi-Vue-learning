package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallCart;
import com.ruoyi.project.shop.service.ILitemallCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车商品Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/cart")
public class LitemallCartController extends BaseController
{
    @Autowired
    private ILitemallCartService litemallCartService;

    /**
     * 查询购物车商品列表
     */
    @PreAuthorize("@ss.hasPermi('shop:cart:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallCart litemallCart)
    {
        startPage();
        List<LitemallCart> list = litemallCartService.selectLitemallCartList(litemallCart);
        return getDataTable(list);
    }

    /**
     * 导出购物车商品列表
     */
    @PreAuthorize("@ss.hasPermi('shop:cart:export')")
    @Log(title = "购物车商品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallCart litemallCart)
    {
        List<LitemallCart> list = litemallCartService.selectLitemallCartList(litemallCart);
        ExcelUtil<LitemallCart> util = new ExcelUtil<LitemallCart>(LitemallCart.class);
        return util.exportExcel(list, "cart");
    }

    /**
     * 获取购物车商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:cart:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallCartService.selectLitemallCartById(id));
    }

    /**
     * 新增购物车商品
     */
    @PreAuthorize("@ss.hasPermi('shop:cart:add')")
    @Log(title = "购物车商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallCart litemallCart)
    {
        return toAjax(litemallCartService.insertLitemallCart(litemallCart));
    }

    /**
     * 修改购物车商品
     */
    @PreAuthorize("@ss.hasPermi('shop:cart:edit')")
    @Log(title = "购物车商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallCart litemallCart)
    {
        return toAjax(litemallCartService.updateLitemallCart(litemallCart));
    }

    /**
     * 删除购物车商品
     */
    @PreAuthorize("@ss.hasPermi('shop:cart:remove')")
    @Log(title = "购物车商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallCartService.deleteLitemallCartByIds(ids));
    }
}
