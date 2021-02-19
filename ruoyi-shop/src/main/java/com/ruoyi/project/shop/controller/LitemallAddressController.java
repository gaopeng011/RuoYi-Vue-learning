package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallAddress;
import com.ruoyi.project.shop.service.ILitemallAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收货地址Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/address")
public class LitemallAddressController extends BaseController
{
    @Autowired
    private ILitemallAddressService litemallAddressService;

    /**
     * 查询收货地址列表
     */
    @PreAuthorize("@ss.hasPermi('shop:address:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallAddress litemallAddress)
    {
        startPage();
        List<LitemallAddress> list = litemallAddressService.selectLitemallAddressList(litemallAddress);
        return getDataTable(list);
    }

    /**
     * 导出收货地址列表
     */
    @PreAuthorize("@ss.hasPermi('shop:address:export')")
    @Log(title = "收货地址", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallAddress litemallAddress)
    {
        List<LitemallAddress> list = litemallAddressService.selectLitemallAddressList(litemallAddress);
        ExcelUtil<LitemallAddress> util = new ExcelUtil<LitemallAddress>(LitemallAddress.class);
        return util.exportExcel(list, "address");
    }

    /**
     * 获取收货地址详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:address:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallAddressService.selectLitemallAddressById(id));
    }

    /**
     * 新增收货地址
     */
    @PreAuthorize("@ss.hasPermi('shop:address:add')")
    @Log(title = "收货地址", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallAddress litemallAddress)
    {
        litemallAddress.setAddTime(DateUtils.getNowDate());
        return toAjax(litemallAddressService.insertLitemallAddress(litemallAddress));
    }

    /**
     * 修改收货地址
     */
    @PreAuthorize("@ss.hasPermi('shop:address:edit')")
    @Log(title = "收货地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallAddress litemallAddress)
    {
        return toAjax(litemallAddressService.updateLitemallAddress(litemallAddress));
    }

    /**
     * 删除收货地址
     */
    @PreAuthorize("@ss.hasPermi('shop:address:remove')")
    @Log(title = "收货地址", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallAddressService.deleteLitemallAddressByIds(ids));
    }
}
