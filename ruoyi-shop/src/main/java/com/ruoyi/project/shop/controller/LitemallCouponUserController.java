package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallCouponUser;
import com.ruoyi.project.shop.service.ILitemallCouponUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 优惠券用户使用Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/couponUser")
public class LitemallCouponUserController extends BaseController
{
    @Autowired
    private ILitemallCouponUserService litemallCouponUserService;

    /**
     * 查询优惠券用户使用列表
     */
    @PreAuthorize("@ss.hasPermi('shop:couponUser:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallCouponUser litemallCouponUser)
    {
        startPage();
        List<LitemallCouponUser> list = litemallCouponUserService.selectLitemallCouponUserList(litemallCouponUser);
        return getDataTable(list);
    }

    /**
     * 导出优惠券用户使用列表
     */
    @PreAuthorize("@ss.hasPermi('shop:couponUser:export')")
    @Log(title = "优惠券用户使用", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallCouponUser litemallCouponUser)
    {
        List<LitemallCouponUser> list = litemallCouponUserService.selectLitemallCouponUserList(litemallCouponUser);
        ExcelUtil<LitemallCouponUser> util = new ExcelUtil<LitemallCouponUser>(LitemallCouponUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 获取优惠券用户使用详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:couponUser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallCouponUserService.selectLitemallCouponUserById(id));
    }

    /**
     * 新增优惠券用户使用
     */
    @PreAuthorize("@ss.hasPermi('shop:couponUser:add')")
    @Log(title = "优惠券用户使用", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallCouponUser litemallCouponUser)
    {
        return toAjax(litemallCouponUserService.insertLitemallCouponUser(litemallCouponUser));
    }

    /**
     * 修改优惠券用户使用
     */
    @PreAuthorize("@ss.hasPermi('shop:couponUser:edit')")
    @Log(title = "优惠券用户使用", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallCouponUser litemallCouponUser)
    {
        return toAjax(litemallCouponUserService.updateLitemallCouponUser(litemallCouponUser));
    }

    /**
     * 删除优惠券用户使用
     */
    @PreAuthorize("@ss.hasPermi('shop:couponUser:remove')")
    @Log(title = "优惠券用户使用", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallCouponUserService.deleteLitemallCouponUserByIds(ids));
    }
}
