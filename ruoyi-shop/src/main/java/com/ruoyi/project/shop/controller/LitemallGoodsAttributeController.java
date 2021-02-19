package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallGoodsAttribute;
import com.ruoyi.project.shop.service.ILitemallGoodsAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品参数Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/attribute")
public class LitemallGoodsAttributeController extends BaseController
{
    @Autowired
    private ILitemallGoodsAttributeService litemallGoodsAttributeService;

    /**
     * 查询商品参数列表
     */
    @PreAuthorize("@ss.hasPermi('shop:attribute:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallGoodsAttribute litemallGoodsAttribute)
    {
        startPage();
        List<LitemallGoodsAttribute> list = litemallGoodsAttributeService.selectLitemallGoodsAttributeList(litemallGoodsAttribute);
        return getDataTable(list);
    }

    /**
     * 导出商品参数列表
     */
    @PreAuthorize("@ss.hasPermi('shop:attribute:export')")
    @Log(title = "商品参数", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallGoodsAttribute litemallGoodsAttribute)
    {
        List<LitemallGoodsAttribute> list = litemallGoodsAttributeService.selectLitemallGoodsAttributeList(litemallGoodsAttribute);
        ExcelUtil<LitemallGoodsAttribute> util = new ExcelUtil<LitemallGoodsAttribute>(LitemallGoodsAttribute.class);
        return util.exportExcel(list, "attribute");
    }

    /**
     * 获取商品参数详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:attribute:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallGoodsAttributeService.selectLitemallGoodsAttributeById(id));
    }

    /**
     * 新增商品参数
     */
    @PreAuthorize("@ss.hasPermi('shop:attribute:add')")
    @Log(title = "商品参数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallGoodsAttribute litemallGoodsAttribute)
    {
        return toAjax(litemallGoodsAttributeService.insertLitemallGoodsAttribute(litemallGoodsAttribute));
    }

    /**
     * 修改商品参数
     */
    @PreAuthorize("@ss.hasPermi('shop:attribute:edit')")
    @Log(title = "商品参数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallGoodsAttribute litemallGoodsAttribute)
    {
        return toAjax(litemallGoodsAttributeService.updateLitemallGoodsAttribute(litemallGoodsAttribute));
    }

    /**
     * 删除商品参数
     */
    @PreAuthorize("@ss.hasPermi('shop:attribute:remove')")
    @Log(title = "商品参数", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallGoodsAttributeService.deleteLitemallGoodsAttributeByIds(ids));
    }
}
