package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallCoupon;
import com.ruoyi.project.shop.service.ILitemallCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 优惠券信息及规则Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/coupon")
public class LitemallCouponController extends BaseController
{
    @Autowired
    private ILitemallCouponService litemallCouponService;

    /**
     * 查询优惠券信息及规则列表
     */
    @PreAuthorize("@ss.hasPermi('shop:coupon:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallCoupon litemallCoupon)
    {
        startPage();
        List<LitemallCoupon> list = litemallCouponService.selectLitemallCouponList(litemallCoupon);
        return getDataTable(list);
    }

    /**
     * 导出优惠券信息及规则列表
     */
    @PreAuthorize("@ss.hasPermi('shop:coupon:export')")
    @Log(title = "优惠券信息及规则", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallCoupon litemallCoupon)
    {
        List<LitemallCoupon> list = litemallCouponService.selectLitemallCouponList(litemallCoupon);
        ExcelUtil<LitemallCoupon> util = new ExcelUtil<LitemallCoupon>(LitemallCoupon.class);
        return util.exportExcel(list, "coupon");
    }

    /**
     * 获取优惠券信息及规则详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:coupon:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallCouponService.selectLitemallCouponById(id));
    }

    /**
     * 新增优惠券信息及规则
     */
    @PreAuthorize("@ss.hasPermi('shop:coupon:add')")
    @Log(title = "优惠券信息及规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallCoupon litemallCoupon)
    {
        litemallCoupon.setAddTime(DateUtils.getNowDate());
        return toAjax(litemallCouponService.insertLitemallCoupon(litemallCoupon));
    }

    /**
     * 修改优惠券信息及规则
     */
    @PreAuthorize("@ss.hasPermi('shop:coupon:edit')")
    @Log(title = "优惠券信息及规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallCoupon litemallCoupon)
    {
        return toAjax(litemallCouponService.updateLitemallCoupon(litemallCoupon));
    }





    /**
     * 删除优惠券信息及规则
     */
    @PreAuthorize("@ss.hasPermi('shop:coupon:remove')")
    @Log(title = "优惠券信息及规则", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallCouponService.deleteLitemallCouponByIds(ids));
    }
}
