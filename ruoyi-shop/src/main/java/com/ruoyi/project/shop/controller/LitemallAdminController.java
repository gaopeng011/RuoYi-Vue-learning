package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallAdmin;
import com.ruoyi.project.shop.service.ILitemallAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/admin")
public class LitemallAdminController extends BaseController
{
    @Autowired
    private ILitemallAdminService litemallAdminService;

    /**
     * 查询管理员列表
     */
    @PreAuthorize("@ss.hasPermi('shop:admin:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallAdmin litemallAdmin)
    {
        startPage();
        List<LitemallAdmin> list = litemallAdminService.selectLitemallAdminList(litemallAdmin);
        return getDataTable(list);
    }

    /**
     * 导出管理员列表
     */
    @PreAuthorize("@ss.hasPermi('shop:admin:export')")
    @Log(title = "管理员", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallAdmin litemallAdmin)
    {
        List<LitemallAdmin> list = litemallAdminService.selectLitemallAdminList(litemallAdmin);
        ExcelUtil<LitemallAdmin> util = new ExcelUtil<LitemallAdmin>(LitemallAdmin.class);
        return util.exportExcel(list, "admin");
    }

    /**
     * 获取管理员详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:admin:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallAdminService.selectLitemallAdminById(id));
    }

    /**
     * 新增管理员
     */
    @PreAuthorize("@ss.hasPermi('shop:admin:add')")
    @Log(title = "管理员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallAdmin litemallAdmin)
    {
        return toAjax(litemallAdminService.insertLitemallAdmin(litemallAdmin));
    }

    /**
     * 修改管理员
     */
    @PreAuthorize("@ss.hasPermi('shop:admin:edit')")
    @Log(title = "管理员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallAdmin litemallAdmin)
    {
        return toAjax(litemallAdminService.updateLitemallAdmin(litemallAdmin));
    }

    /**
     * 删除管理员
     */
    @PreAuthorize("@ss.hasPermi('shop:admin:remove')")
    @Log(title = "管理员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallAdminService.deleteLitemallAdminByIds(ids));
    }
}
