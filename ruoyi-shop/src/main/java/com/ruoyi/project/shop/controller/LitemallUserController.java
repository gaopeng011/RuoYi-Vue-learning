package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallUser;
import com.ruoyi.project.shop.service.ILitemallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/user")
public class LitemallUserController extends BaseController
{
    @Autowired
    private ILitemallUserService litemallUserService;

    /**
     * 查询用户列表
     */
    @PreAuthorize("@ss.hasPermi('shop:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallUser litemallUser)
    {
        startPage();
        List<LitemallUser> list = litemallUserService.selectLitemallUserList(litemallUser);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @PreAuthorize("@ss.hasPermi('shop:user:export')")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallUser litemallUser)
    {
        List<LitemallUser> list = litemallUserService.selectLitemallUserList(litemallUser);
        ExcelUtil<LitemallUser> util = new ExcelUtil<LitemallUser>(LitemallUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 获取用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallUserService.selectLitemallUserById(id));
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('shop:user:add')")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallUser litemallUser)
    {
        return toAjax(litemallUserService.insertLitemallUser(litemallUser));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('shop:user:edit')")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallUser litemallUser)
    {
        return toAjax(litemallUserService.updateLitemallUser(litemallUser));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('shop:user:remove')")
    @Log(title = "用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallUserService.deleteLitemallUserByIds(ids));
    }
}
