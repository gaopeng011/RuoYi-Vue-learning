package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallFeedback;
import com.ruoyi.project.shop.service.ILitemallFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 意见反馈Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/feedback")
public class LitemallFeedbackController extends BaseController
{
    @Autowired
    private ILitemallFeedbackService litemallFeedbackService;

    /**
     * 查询意见反馈列表
     */
    @PreAuthorize("@ss.hasPermi('shop:feedback:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallFeedback litemallFeedback)
    {
        startPage();
        List<LitemallFeedback> list = litemallFeedbackService.selectLitemallFeedbackList(litemallFeedback);
        return getDataTable(list);
    }

    /**
     * 导出意见反馈列表
     */
    @PreAuthorize("@ss.hasPermi('shop:feedback:export')")
    @Log(title = "意见反馈", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallFeedback litemallFeedback)
    {
        List<LitemallFeedback> list = litemallFeedbackService.selectLitemallFeedbackList(litemallFeedback);
        ExcelUtil<LitemallFeedback> util = new ExcelUtil<LitemallFeedback>(LitemallFeedback.class);
        return util.exportExcel(list, "feedback");
    }

    /**
     * 获取意见反馈详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:feedback:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallFeedbackService.selectLitemallFeedbackById(id));
    }

    /**
     * 新增意见反馈
     */
    @PreAuthorize("@ss.hasPermi('shop:feedback:add')")
    @Log(title = "意见反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallFeedback litemallFeedback)
    {
        return toAjax(litemallFeedbackService.insertLitemallFeedback(litemallFeedback));
    }

    /**
     * 修改意见反馈
     */
    @PreAuthorize("@ss.hasPermi('shop:feedback:edit')")
    @Log(title = "意见反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallFeedback litemallFeedback)
    {
        return toAjax(litemallFeedbackService.updateLitemallFeedback(litemallFeedback));
    }

    /**
     * 删除意见反馈
     */
    @PreAuthorize("@ss.hasPermi('shop:feedback:remove')")
    @Log(title = "意见反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallFeedbackService.deleteLitemallFeedbackByIds(ids));
    }
}
