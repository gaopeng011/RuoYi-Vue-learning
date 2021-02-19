package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallCategory;
import com.ruoyi.project.shop.service.ILitemallCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 类目Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/category")
public class LitemallCategoryController extends BaseController
{
    @Autowired
    private ILitemallCategoryService litemallCategoryService;

    /**
     * 查询类目列表
     */
    @PreAuthorize("@ss.hasPermi('shop:category:list')")
    @GetMapping("/list")
    public AjaxResult list(LitemallCategory litemallCategory)
    {
        List<LitemallCategory> list = litemallCategoryService.selectLitemallCategoryList(litemallCategory);
        return AjaxResult.success(list);
    }

    @GetMapping("/l1")
    @PreAuthorize("@ss.hasPermi('shop:category:list')")
    public AjaxResult catL1() {
        // 所有一级分类目录
        List<LitemallCategory> l1CatList = litemallCategoryService.queryL1();
        AjaxResult ajaxResult=AjaxResult.success();
        ajaxResult.put("cataL1", l1CatList);
        return ajaxResult;
    }

    /**
     * 导出类目列表
     */
    @PreAuthorize("@ss.hasPermi('shop:category:export')")
    @Log(title = "类目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallCategory litemallCategory)
    {
        List<LitemallCategory> list = litemallCategoryService.selectLitemallCategoryList(litemallCategory);
        ExcelUtil<LitemallCategory> util = new ExcelUtil<LitemallCategory>(LitemallCategory.class);
        return util.exportExcel(list, "category");
    }

    /**
     * 获取类目详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallCategoryService.selectLitemallCategoryById(id));
    }

    /**
     * 新增类目
     */
    @PreAuthorize("@ss.hasPermi('shop:category:add')")
    @Log(title = "类目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallCategory litemallCategory)
    {
        litemallCategory.setPid(litemallCategory.getParentId());
        litemallCategory.setAddTime(new Date());

        return toAjax(litemallCategoryService.insertLitemallCategory(litemallCategory));
    }

    /**
     * 修改类目
     */
    @PreAuthorize("@ss.hasPermi('shop:category:edit')")
    @Log(title = "类目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallCategory litemallCategory)
    {
        return toAjax(litemallCategoryService.updateLitemallCategory(litemallCategory));
    }

    /**
     * 删除类目
     */
    @PreAuthorize("@ss.hasPermi('shop:category:remove')")
    @Log(title = "类目", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long ids)
    {
        LitemallCategory litemallCategory=  litemallCategoryService.selectLitemallCategoryById(ids);
        if ("L1".equals(litemallCategory.getLevel())){
            LitemallCategory litemallCategory1=new LitemallCategory();
            litemallCategory1.setPid(litemallCategory.getId());
            litemallCategory1.setParentId(litemallCategory.getId());
            List<LitemallCategory> list = litemallCategoryService.selectLitemallCategoryList(litemallCategory1);

            if (list.size()>0){
                return AjaxResult.error("删除的是父分类存在子类别！");
            }

        }


        return toAjax(litemallCategoryService.deleteLitemallCategoryById(ids));
    }
}
