package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.task.TaskService;
import com.ruoyi.common.utils.GrouponConstant;
import com.ruoyi.common.utils.ResponseUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.GrouponRuleExpiredTask;
import com.ruoyi.project.shop.domain.LitemallGoods;
import com.ruoyi.project.shop.domain.LitemallGrouponRules;
import com.ruoyi.project.shop.service.ILitemallGoodsService;
import com.ruoyi.project.shop.service.ILitemallGrouponRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 团购规则Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/rules")
public class LitemallGrouponRulesController extends BaseController
{
    @Autowired
    private ILitemallGrouponRulesService litemallGrouponRulesService;
    @Autowired
    private ILitemallGoodsService litemallGoodsService;
    @Autowired
    private TaskService taskService;
    /**
     * 查询团购规则列表
     */
    @PreAuthorize("@ss.hasPermi('shop:rules:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallGrouponRules litemallGrouponRules)
    {
        startPage();
        List<LitemallGrouponRules> list = litemallGrouponRulesService.selectLitemallGrouponRulesList(litemallGrouponRules);
        return getDataTable(list);
    }

    /**
     * 导出团购规则列表
     */
    @PreAuthorize("@ss.hasPermi('shop:rules:export')")
    @Log(title = "团购规则", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallGrouponRules litemallGrouponRules)
    {
        List<LitemallGrouponRules> list = litemallGrouponRulesService.selectLitemallGrouponRulesList(litemallGrouponRules);
        ExcelUtil<LitemallGrouponRules> util = new ExcelUtil<LitemallGrouponRules>(LitemallGrouponRules.class);
        return util.exportExcel(list, "rules");
    }

    /**
     * 获取团购规则详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:rules:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallGrouponRulesService.selectLitemallGrouponRulesById(id));
    }


    private Object validate(LitemallGrouponRules grouponRules) {
        Long goodsId = grouponRules.getGoodsId();
        if (goodsId == null) {
            return ResponseUtil.badArgument();
        }
        BigDecimal discount = grouponRules.getDiscount();
        if (discount == null) {
            return ResponseUtil.badArgument();
        }
        Long discountMember = grouponRules.getDiscountMember();
        if (discountMember == null) {
            return ResponseUtil.badArgument();
        }
        LocalDateTime expireTime = grouponRules.getExpireTime();
        if (expireTime == null) {
            return ResponseUtil.badArgument();
        }

        return null;
    }


    /**
     * 新增团购规则
     */
    @PreAuthorize("@ss.hasPermi('shop:rules:add')")
    @Log(title = "团购规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LitemallGrouponRules litemallGrouponRules)
    {
        Object error = validate(litemallGrouponRules);
        if (error != null) {
            return AjaxResult.error("参数校验不通过",error);
        }

        Long goodsId=litemallGrouponRules.getGoodsId();
        LitemallGoods goods=litemallGoodsService.selectLitemallGoodsById(goodsId);
        if (goods==null){
            return  AjaxResult.error("团购商品不存在");
        }
        LitemallGrouponRules goodsRule=new LitemallGrouponRules();
        goodsRule.setGoodsId(goodsId);
        List<LitemallGrouponRules> listRule=litemallGrouponRulesService.selectLitemallGrouponRulesList(goodsRule);
        if (listRule.size()>0){
            return AjaxResult.error("团购商品已存在");
        }

        litemallGrouponRules.setGoodsName(goods.getName());
        litemallGrouponRules.setPicUrl(goods.getPicUrl());
        litemallGrouponRules.setStatus(GrouponConstant.RULE_STATUS_ON);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expire = litemallGrouponRules.getExpireTime();
        long delay = ChronoUnit.MILLIS.between(now, expire);
        Integer result=litemallGrouponRulesService.insertLitemallGrouponRules(litemallGrouponRules);
        // 团购过期任务
        taskService.addTask(new GrouponRuleExpiredTask(litemallGrouponRules.getId(), delay));

        return toAjax(result);
    }

    /**
     * 修改团购规则
     */
    @PreAuthorize("@ss.hasPermi('shop:rules:edit')")
    @Log(title = "团购规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LitemallGrouponRules litemallGrouponRules)
    {
        Object error = validate(litemallGrouponRules);
        if (error != null) {
            return AjaxResult.error("参数校验不通过",error);
        }
        LitemallGrouponRules rules = litemallGrouponRulesService.selectLitemallGrouponRulesById(litemallGrouponRules.getId());
        if (rules==null){
            return AjaxResult.error("未找到对应规则信息");
        }
        if(!rules.getStatus().equals(GrouponConstant.RULE_STATUS_ON)){
            return AjaxResult.error("团购已经下线");
        }
        Long goodsId=litemallGrouponRules.getGoodsId();
        LitemallGoods goods=litemallGoodsService.selectLitemallGoodsById(goodsId);
        if (goods==null){
            return  AjaxResult.error("团购商品不存在");
        }

        litemallGrouponRules.setGoodsName(goods.getName());
        litemallGrouponRules.setPicUrl(goods.getPicUrl());
        return toAjax(litemallGrouponRulesService.updateLitemallGrouponRules(litemallGrouponRules));
    }

    /**
     * 删除团购规则
     */
    @PreAuthorize("@ss.hasPermi('shop:rules:remove')")
    @Log(title = "团购规则", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallGrouponRulesService.deleteLitemallGrouponRulesByIds(ids));
    }
}
