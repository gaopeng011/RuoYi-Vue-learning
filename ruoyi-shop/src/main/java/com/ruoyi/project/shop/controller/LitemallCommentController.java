package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallComment;
import com.ruoyi.project.shop.service.ILitemallCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/comment")
public class LitemallCommentController extends BaseController
{
    @Autowired
    private ILitemallCommentService litemallCommentService;

    /**
     * 查询评论列表
     */
    @PreAuthorize("@ss.hasPermi('shop:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallComment litemallComment)
    {
        startPage();
        List<LitemallComment> list = litemallCommentService.selectLitemallCommentList(litemallComment);
        return getDataTable(list);
    }

    /**
     * 导出评论列表
     */
    @PreAuthorize("@ss.hasPermi('shop:comment:export')")
    @Log(title = "评论", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallComment litemallComment)
    {
        List<LitemallComment> list = litemallCommentService.selectLitemallCommentList(litemallComment);
        ExcelUtil<LitemallComment> util = new ExcelUtil<LitemallComment>(LitemallComment.class);
        return util.exportExcel(list, "comment");
    }

    /**
     * 获取评论详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallCommentService.selectLitemallCommentById(id));
    }

    /**
     * 新增评论
     */
    @PreAuthorize("@ss.hasPermi('shop:comment:add')")
    @Log(title = "评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallComment litemallComment)
    {
        return toAjax(litemallCommentService.insertLitemallComment(litemallComment));
    }

    /**
     * 修改评论
     */
    @PreAuthorize("@ss.hasPermi('shop:comment:edit')")
    @Log(title = "评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallComment litemallComment)
    {
        return toAjax(litemallCommentService.updateLitemallComment(litemallComment));
    }

    /**
     * 删除评论
     */
    @PreAuthorize("@ss.hasPermi('shop:comment:remove')")
    @Log(title = "评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallCommentService.deleteLitemallCommentByIds(ids));
    }
}
