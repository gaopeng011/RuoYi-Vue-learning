package com.ruoyi.project.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.ResponseUtil;
import com.ruoyi.project.shop.domain.*;
import com.ruoyi.project.shop.dto.GoodsAllinone;
import com.ruoyi.project.shop.mapper.LitemallGoodsMapper;
import com.ruoyi.project.shop.service.ILitemallGoodsService;
import com.ruoyi.project.shop.vo.CatVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品基本信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@Service
public class LitemallGoodsServiceImpl implements ILitemallGoodsService
{
    private final Log logger = LogFactory.getLog(LitemallGoodsServiceImpl.class);
    @Autowired
    private LitemallGoodsMapper litemallGoodsMapper;

    @Autowired
    private LitemallCategoryServiceImpl litemallCategoryService;

    @Autowired
    private LitemallGoodsSpecificationServiceImpl litemallGoodsSpecificationService;
    @Autowired
    private LitemallGoodsAttributeServiceImpl litemallGoodsAttributeService;
    @Autowired
    private LitemallGoodsProductServiceImpl litemallGoodsProductService;

    @Autowired
    private LitemallBrandServiceImpl litemallBrandService;
    @Autowired
    private LitemallCartServiceImpl litemallCartService;
    /**
     * 查询商品基本信息
     *
     * @param id 商品基本信息ID
     * @return 商品基本信息
     */
    @Override
    public LitemallGoods selectLitemallGoodsById(Long id)
    {
        return litemallGoodsMapper.selectLitemallGoodsById(id);
    }

    /**
     * 查询商品基本信息列表
     *
     * @param litemallGoods 商品基本信息
     * @return 商品基本信息
     */
    @Override
    public List<LitemallGoods> selectLitemallGoodsList(LitemallGoods litemallGoods)
    {
        return litemallGoodsMapper.selectLitemallGoodsList(litemallGoods);
    }

    /**
     * 新增商品基本信息
     *
     * @param litemallGoods 商品基本信息
     * @return 结果
     */
    @Override
    public int insertLitemallGoods(LitemallGoods litemallGoods)
    {
        return litemallGoodsMapper.insertLitemallGoods(litemallGoods);
    }

    @Override
    public long checkExistGoodsByName(LitemallGoods litemallGoods) {


        return litemallGoodsMapper.checkExistGoodsByName(litemallGoods);
    }

    //商品信息的发布创建
    @Override
    @Transactional
    public AjaxResult createLitemallGoods(GoodsAllinone goodsAllinone) {

        try {

        Object error = validate(goodsAllinone);
        if (error != null) {
            return AjaxResult.error("商品信息验证不通过！");
        }

        LitemallGoods goods = goodsAllinone.getGoods();
        LitemallGoodsAttribute[] attributes = goodsAllinone.getAttributes();
        LitemallGoodsSpecification[] specifications = goodsAllinone.getSpecifications();
        LitemallGoodsProduct[] products = goodsAllinone.getProducts();

        String name = goods.getName();
        LitemallGoods litemallGoods=new LitemallGoods();
        litemallGoods.setName(name);
        if (litemallGoodsMapper.checkExistGoodsByName(litemallGoods)>0) {
            return AjaxResult.error("商品名已经存在");
        }

        // 商品表里面有一个字段retailPrice记录当前商品的最低价
        BigDecimal retailPrice = new BigDecimal(Integer.MAX_VALUE);
        for (LitemallGoodsProduct product : products) {
            BigDecimal productPrice = product.getPrice();
            if(retailPrice.compareTo(productPrice) == 1){
                retailPrice = productPrice;
            }
        }
        goods.setRetailPrice(retailPrice);

        //添加商品信息表
        litemallGoodsMapper.insertLitemallGoods(goods);

        //将生成的分享图片地址写入数据库
//        String url = qCodeService.createGoodShareImage(goods.getId().toString(), goods.getPicUrl(), goods.getName());
//        if (!StringUtils.isEmpty(url)) {
//            goods.setShareUrl(url);
//            if (goodsService.updateById(goods) == 0) {
//                throw new RuntimeException("更新数据失败");
//            }
//        }

        // 商品规格表litemall_goods_specification
        for (LitemallGoodsSpecification specification : specifications) {
            specification.setGoodsId(goods.getId());
            litemallGoodsSpecificationService.insertLitemallGoodsSpecification(specification);
        }

        // 商品参数表litemall_goods_attribute
        for (LitemallGoodsAttribute attribute : attributes) {
            attribute.setGoodsId(goods.getId());
            litemallGoodsAttributeService.insertLitemallGoodsAttribute(attribute);
        }

        // 商品货品表litemall_product
        for (LitemallGoodsProduct product : products) {
            product.setGoodsId(goods.getId());
            litemallGoodsProductService.insertLitemallGoodsProduct(product);
        }


        return AjaxResult.success("创建商品成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return AjaxResult.error("商品创建失败");
        }


    }

    /**
     * 修改商品基本信息
     *
     * @param litemallGoods 商品基本信息
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult updateLitemallGoods(GoodsAllinone goodsAllinone)
    {
        try {
            Object error = validate(goodsAllinone);
            if (error != null) {
                return AjaxResult.error("商品信息验证不通过！");
            }

            LitemallGoods goods = goodsAllinone.getGoods();
            LitemallGoodsAttribute[] attributes = goodsAllinone.getAttributes();
            LitemallGoodsSpecification[] specifications = goodsAllinone.getSpecifications();
            LitemallGoodsProduct[] products = goodsAllinone.getProducts();

            // 商品表里面有一个字段retailPrice记录当前商品的最低价
            BigDecimal retailPrice = new BigDecimal(Integer.MAX_VALUE);
            for (LitemallGoodsProduct product : products) {
                BigDecimal productPrice = product.getPrice();
                if(retailPrice.compareTo(productPrice) == 1){
                    retailPrice = productPrice;
                }
            }
            goods.setRetailPrice(retailPrice);

            // 商品基本信息表litemall_goods
            if (litemallGoodsMapper.updateLitemallGoods(goods) == 0) {
                throw new RuntimeException("更新数据失败");
            }

            long gid = goods.getId();

            // 商品规格表litemall_goods_specification
            for (LitemallGoodsSpecification specification : specifications) {
                // 目前只支持更新规格表的图片字段
                if(specification.getUpdateTime() == null){
                    specification.setSpecification(null);
                    specification.setValue(null);
                    litemallGoodsSpecificationService.updateLitemallGoodsSpecification(specification);
                }
            }

            // 商品货品表litemall_product
            for (LitemallGoodsProduct product : products) {
                if(product.getUpdateTime() == null) {
                    litemallGoodsProductService.updateLitemallGoodsProduct(product);
                }
            }

            // 商品参数表litemall_goods_attribute
            for (LitemallGoodsAttribute attribute : attributes) {
                if (attribute.getId() == null || attribute.getId().equals(0)){
                    attribute.setGoodsId(goods.getId());
                    litemallGoodsAttributeService.insertLitemallGoodsAttribute(attribute);
                }
                else if(attribute.getDeleted()==1){
                    litemallGoodsAttributeService.selectLitemallGoodsAttributeById(attribute.getId());
                }
                else if(attribute.getUpdateTime() == null){
                    litemallGoodsAttributeService.updateLitemallGoodsAttribute(attribute);
                }
            }

            // 这里需要注意的是购物车litemall_cart有些字段是拷贝商品的一些字段，因此需要及时更新
            // 目前这些字段是goods_sn, goods_name, price, pic_url
            for (LitemallGoodsProduct product : products) {
                litemallCartService.updateLitemallCartByProductId(product.getId(), goods.getGoodsSn(), goods.getName(), product.getPrice(), product.getUrl());
            }




        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return AjaxResult.error("商品更新失败");
        }
       return AjaxResult.success("商品更新成功");
    }

    /**
     * 批量删除商品基本信息
     *
     * @param ids 需要删除的商品基本信息ID
     * @return 结果
     */
    @Override
    public int deleteLitemallGoodsByIds(Long[] ids)
    {
        return litemallGoodsMapper.deleteLitemallGoodsByIds(ids);
    }

    @Override
    public List<LitemallGoods> selectLitemallGoodsByIds(Integer[] ids) {
        return litemallGoodsMapper.selectLitemallGoodsByIds(ids);
    }

    /**
     * 删除商品基本信息信息
     *
     * @param id 商品基本信息ID
     * @return 结果
     */
    @Override
    public int deleteLitemallGoodsById(Long id)
    {
        return litemallGoodsMapper.deleteLitemallGoodsById(id);
    }


    /**
     * @Author hanguanglei
     * @Description 获取商品品类和品牌
     * @Date 16:48 2020/3/13
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @Override
    public Map<String, Object> listCateAndBrand() {
        List<LitemallCategory> l1CatList = litemallCategoryService.queryL1();
        List<CatVo> categoryList = new ArrayList<>(l1CatList.size());

        for (LitemallCategory l1 : l1CatList) {
            CatVo l1CatVo = new CatVo();
            l1CatVo.setValue(l1.getId());
            l1CatVo.setLabel(l1.getName());

            List<LitemallCategory> l2CatList = litemallCategoryService.queryL2ByPid(l1.getId());
            List<CatVo> children = new ArrayList<>(l2CatList.size());
            for (LitemallCategory l2 : l2CatList) {
                CatVo l2CatVo = new CatVo();
                l2CatVo.setValue(l2.getId());
                l2CatVo.setLabel(l2.getName());
                children.add(l2CatVo);
            }
            l1CatVo.setChildren(children);

            categoryList.add(l1CatVo);
        }


        List<LitemallBrand> list = litemallBrandService.selectLitemallBrandAll();
        List<Map<String, Object>> brandList = new ArrayList<>(l1CatList.size());
        for (LitemallBrand brand : list) {
            Map<String, Object> b = new HashMap<>(2);
            b.put("value", brand.getId());
            b.put("label", brand.getName());
            brandList.add(b);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("categoryList", categoryList);
        data.put("brandList", brandList);
        return data;
    }

    @Override
    public JSONObject goodsDetail(Long id) {
        JSONObject data = new JSONObject();
        LitemallGoods goods = litemallGoodsMapper.selectLitemallGoodsById(id);
        List<LitemallGoodsProduct> products = litemallGoodsProductService.queryByGid(id);
        List<LitemallGoodsSpecification> specifications = litemallGoodsSpecificationService.queryByGid(id);
        List<LitemallGoodsAttribute> attributes = litemallGoodsAttributeService.queryByGid(id);
        Long categoryId = goods.getCategoryId();
        LitemallCategory category = litemallCategoryService.selectLitemallCategoryById(categoryId);
        Long[] categoryIds = new Long[]{};
        if (category != null) {
            Long parentCategoryId = category.getParentId();
            categoryIds = new Long[]{parentCategoryId, categoryId};
        }
      //  Map<String, Object> data = new HashMap<>();
        data.put("goods", goods);
        data.put("specifications", specifications);
        data.put("products", products);
        data.put("attributes", attributes);
        data.put("categoryIds", categoryIds);
        return data;
    }

    @Override
    @Transactional
    public AjaxResult delete(Long id) {

        if (id == null) {
            return AjaxResult.error();
        }


        litemallGoodsMapper.deleteLitemallGoodsById(id);
        litemallGoodsSpecificationService.deleteLitemallGoodsSpecificationById(id);
        litemallGoodsAttributeService.deleteLitemallGoodsAttributeById(id);
        litemallGoodsProductService.deleteLitemallGoodsProductById(id);

        return AjaxResult.success();
    }


    /**
     * @Author hanguanglei
     * @Description //验证商品属性内容字段
     * @Date 18:05 2020/3/12
     * @Param [goodsAllinone]
     * @return java.lang.Object
     **/
    private Object validate(GoodsAllinone goodsAllinone) {
        LitemallGoods goods = goodsAllinone.getGoods();
        String name = goods.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        String goodsSn = goods.getGoodsSn();
        if (StringUtils.isEmpty(goodsSn)) {
            return ResponseUtil.badArgument();
        }
        // 品牌商可以不设置，如果设置则需要验证品牌商存在
        Long brandId = goods.getBrandId();
        if (brandId != null && brandId != 0) {
            if (litemallBrandService.selectLitemallBrandById(brandId) == null) {
                return ResponseUtil.badArgumentValue();
            }
        }
        // 分类可以不设置，如果设置则需要验证分类存在
        Long categoryId = goods.getCategoryId();
        if (categoryId != null && categoryId != 0) {
            if (litemallCategoryService.selectLitemallCategoryById(categoryId) == null) {
                return ResponseUtil.badArgumentValue();
            }
        }

        LitemallGoodsAttribute[] attributes = goodsAllinone.getAttributes();
        for (LitemallGoodsAttribute attribute : attributes) {
            String attr = attribute.getAttribute();
            if (StringUtils.isEmpty(attr)) {
                return ResponseUtil.badArgument();
            }
            String value = attribute.getValue();
            if (StringUtils.isEmpty(value)) {
                return ResponseUtil.badArgument();
            }
        }

        LitemallGoodsSpecification[] specifications = goodsAllinone.getSpecifications();
        for (LitemallGoodsSpecification specification : specifications) {
            String spec = specification.getSpecification();
            if (StringUtils.isEmpty(spec)) {
                return ResponseUtil.badArgument();
            }
            String value = specification.getValue();
            if (StringUtils.isEmpty(value)) {
                return ResponseUtil.badArgument();
            }
        }

        LitemallGoodsProduct[] products = goodsAllinone.getProducts();
        for (LitemallGoodsProduct product : products) {
            Long number = product.getNumber();
            if (number == null || number < 0) {
                return ResponseUtil.badArgument();
            }

            BigDecimal price = product.getPrice();
            if (price == null) {
                return ResponseUtil.badArgument();
            }

            String[] productSpecifications = product.getSpecifications();
            if (productSpecifications.length == 0) {
                return ResponseUtil.badArgument();
            }
        }

        return null;
    }
}
