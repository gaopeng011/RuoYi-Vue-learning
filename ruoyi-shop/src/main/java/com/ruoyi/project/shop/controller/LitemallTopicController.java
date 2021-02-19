package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallGoods;
import com.ruoyi.project.shop.domain.LitemallTopic;
import com.ruoyi.project.shop.service.ILitemallGoodsService;
import com.ruoyi.project.shop.service.ILitemallTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 专题Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/topic")
public class LitemallTopicController extends BaseController
{
    @Autowired
    private ILitemallTopicService litemallTopicService;
    @Autowired
    private ILitemallGoodsService litemallGoodsService;

    /**
     * 查询专题列表
     */
    @PreAuthorize("@ss.hasPermi('shop:topic:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallTopic litemallTopic)
    {
        startPage();
        List<LitemallTopic> list = litemallTopicService.selectLitemallTopicList(litemallTopic);
        return getDataTable(list);
    }

    /**
     * 导出专题列表
     */
    @PreAuthorize("@ss.hasPermi('shop:topic:export')")
    @Log(title = "专题", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallTopic litemallTopic)
    {
        List<LitemallTopic> list = litemallTopicService.selectLitemallTopicList(litemallTopic);
        ExcelUtil<LitemallTopic> util = new ExcelUtil<LitemallTopic>(LitemallTopic.class);
        return util.exportExcel(list, "topic");
    }

    /**
     * 获取专题详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:topic:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
      LitemallTopic litemallTopic=  litemallTopicService.selectLitemallTopicById(id);
        Integer[] goodsIds = litemallTopic.getGoods();
        List<LitemallGoods> goodsList = null;
        if (goodsIds == null || goodsIds.length == 0) {
            goodsList = new ArrayList<>();
        } else {
            goodsList = litemallGoodsService.selectLitemallGoodsByIds(goodsIds);
        }
        AjaxResult ajaxResult=AjaxResult.success();
        ajaxResult.put("topic", litemallTopic);
        ajaxResult.put("goodsList", goodsList);
        return ajaxResult;
    }

    /**
     * 新增专题
     */
    @PreAuthorize("@ss.hasPermi('shop:topic:add')")
    @Log(title = "专题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallTopic litemallTopic)
    {
        litemallTopic.setAddTime(DateUtils.getNowDate());
        litemallTopic.setCreateTime(DateUtils.getNowDate());
        return toAjax(litemallTopicService.insertLitemallTopic(litemallTopic));
    }

    /**
     * 修改专题
     */
    @PreAuthorize("@ss.hasPermi('shop:topic:edit')")
    @Log(title = "专题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallTopic litemallTopic)
    {
        return toAjax(litemallTopicService.updateLitemallTopic(litemallTopic));
    }

    /**
     * 删除专题
     */
    @PreAuthorize("@ss.hasPermi('shop:topic:remove')")
    @Log(title = "专题", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallTopicService.deleteLitemallTopicByIds(ids));
    }
}
