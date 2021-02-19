package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallOrderGoods;
import com.ruoyi.project.shop.service.ILitemallOrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单商品Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/orderGood")
public class LitemallOrderGoodsController extends BaseController
{
    @Autowired
    private ILitemallOrderGoodsService litemallOrderGoodsService;

    /**
     * 查询订单商品列表
     */
    @PreAuthorize("@ss.hasPermi('shop:orderGood:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallOrderGoods litemallOrderGoods)
    {
        startPage();
        List<LitemallOrderGoods> list = litemallOrderGoodsService.selectLitemallOrderGoodsList(litemallOrderGoods);
        return getDataTable(list);
    }

    /**
     * 导出订单商品列表
     */
    @PreAuthorize("@ss.hasPermi('shop:orderGood:export')")
    @Log(title = "订单商品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallOrderGoods litemallOrderGoods)
    {
        List<LitemallOrderGoods> list = litemallOrderGoodsService.selectLitemallOrderGoodsList(litemallOrderGoods);
        ExcelUtil<LitemallOrderGoods> util = new ExcelUtil<LitemallOrderGoods>(LitemallOrderGoods.class);
        return util.exportExcel(list, "goods");
    }

    /**
     * 获取订单商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:orderGood:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallOrderGoodsService.selectLitemallOrderGoodsById(id));
    }

    /**
     * 新增订单商品
     */
    @PreAuthorize("@ss.hasPermi('shop:orderGood:add')")
    @Log(title = "订单商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallOrderGoods litemallOrderGoods)
    {
        return toAjax(litemallOrderGoodsService.insertLitemallOrderGoods(litemallOrderGoods));
    }

    /**
     * 修改订单商品
     */
    @PreAuthorize("@ss.hasPermi('shop:orderGood:edit')")
    @Log(title = "订单商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallOrderGoods litemallOrderGoods)
    {
        return toAjax(litemallOrderGoodsService.updateLitemallOrderGoods(litemallOrderGoods));
    }

    /**
     * 删除订单商品
     */
    @PreAuthorize("@ss.hasPermi('shop:orderGood:remove')")
    @Log(title = "订单商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallOrderGoodsService.deleteLitemallOrderGoodsByIds(ids));
    }
}
