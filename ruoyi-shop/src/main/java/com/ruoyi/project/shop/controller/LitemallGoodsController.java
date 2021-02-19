package com.ruoyi.project.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallGoods;
import com.ruoyi.project.shop.dto.GoodsAllinone;
import com.ruoyi.project.shop.service.ILitemallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 商品基本信息Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/goods")
public class LitemallGoodsController extends BaseController
{
    @Autowired
    private ILitemallGoodsService litemallGoodsService;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 查询商品基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('shop:goods:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallGoods litemallGoods)
    {
        startPage();
        List<LitemallGoods> list = litemallGoodsService.selectLitemallGoodsList(litemallGoods);
        return getDataTable(list);
    }


    @GetMapping("/catAndBrand")
    public AjaxResult list2() {

        return AjaxResult.success(litemallGoodsService.listCateAndBrand());
    }



    /**
     * 导出商品基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('shop:goods:export')")
    @Log(title = "商品基本信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallGoods litemallGoods)
    {
        List<LitemallGoods> list = litemallGoodsService.selectLitemallGoodsList(litemallGoods);
        ExcelUtil<LitemallGoods> util = new ExcelUtil<LitemallGoods>(LitemallGoods.class);
        return util.exportExcel(list, "goods");
    }

    /**
     * 获取商品基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:goods:query')")
    @GetMapping(value = "/detail/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallGoodsService.selectLitemallGoodsById(id));
    }

    @GetMapping("/detail")
    public AjaxResult detail(@NotNull Long id) {


        return AjaxResult.success(litemallGoodsService.goodsDetail(id));

    }



    /**
     * 新增商品基本信息
     */
    @PreAuthorize("@ss.hasPermi('shop:goods:add')")
    @Log(title = "商品基本信息", businessType = BusinessType.INSERT)
    @PostMapping("/create")
    public AjaxResult add(@RequestBody GoodsAllinone goodsAllinone)
    {

        return litemallGoodsService.createLitemallGoods(goodsAllinone);
    }

    /**
     * 修改商品基本信息
     */
    @PreAuthorize("@ss.hasPermi('shop:goods:edit')")
    @Log(title = "商品基本信息", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody GoodsAllinone goodsAllinone)
    {

       // GoodsAllinone goodsAllinone= null;

        try {

        //    String str=json.replaceAll("\"\\\\","\"");
         //   System.out.println(str);
        //   JSONObject jsonObject1=JSON.parseObject(json);
        //    goodsAllinone = objectMapper.readValue(jsonObject.getObject(, ), new TypeReference<GoodsAllinone>() {});

            return litemallGoodsService.updateLitemallGoods(goodsAllinone);
        } catch (Exception e) {

            e.printStackTrace();
            return AjaxResult.error("修改信息失败");
        }


    }

    /**
     * 删除商品基本信息
     */
    @PreAuthorize("@ss.hasPermi('shop:goods:remove')")
    @Log(title = "商品基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallGoodsService.deleteLitemallGoodsByIds(ids));
    }


    /**
     * 删除商品基本信息
     */
    @PreAuthorize("@ss.hasPermi('shop:goods:remove')")
    @Log(title = "商品基本信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/{id}")
    public AjaxResult delete(@PathVariable Long id)
    {
        return litemallGoodsService.delete(id);
    }
}
