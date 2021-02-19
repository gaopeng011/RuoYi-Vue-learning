package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallOrder;
import com.ruoyi.project.shop.service.ILitemallOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/order")
public class LitemallOrderController extends BaseController
{
    @Autowired
    private ILitemallOrderService litemallOrderService;

    /**
     * 查询订单列表
     */
    @PreAuthorize("@ss.hasPermi('shop:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallOrder litemallOrder)
    {
        startPage();
        List<LitemallOrder> list = litemallOrderService.selectLitemallOrderList(litemallOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @PreAuthorize("@ss.hasPermi('shop:order:export')")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallOrder litemallOrder)
    {
        List<LitemallOrder> list = litemallOrderService.selectLitemallOrderList(litemallOrder);
        ExcelUtil<LitemallOrder> util = new ExcelUtil<LitemallOrder>(LitemallOrder.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallOrderService.selectLitemallOrderById(id));
    }

    /**
     * 新增订单
     */
    @PreAuthorize("@ss.hasPermi('shop:order:add')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallOrder litemallOrder)
    {
        return toAjax(litemallOrderService.insertLitemallOrder(litemallOrder));
    }

    /**
     * 修改订单
     */
    @PreAuthorize("@ss.hasPermi('shop:order:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallOrder litemallOrder)
    {
        return toAjax(litemallOrderService.updateLitemallOrder(litemallOrder));
    }

    /**
     * 删除订单
     */
    @PreAuthorize("@ss.hasPermi('shop:order:remove')")
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallOrderService.deleteLitemallOrderByIds(ids));
    }
}
