package com.ruoyi.project.shop.dto;


import com.ruoyi.project.shop.domain.LitemallGoods;
import com.ruoyi.project.shop.domain.LitemallGoodsAttribute;
import com.ruoyi.project.shop.domain.LitemallGoodsProduct;
import com.ruoyi.project.shop.domain.LitemallGoodsSpecification;

public class GoodsAllinone {
    LitemallGoods goods;
    LitemallGoodsSpecification[] specifications;//商品规格
    LitemallGoodsAttribute[] attributes;//商品属性
    LitemallGoodsProduct[] products;//商品货品表

    public LitemallGoods getGoods() {
        return goods;
    }

    public void setGoods(LitemallGoods goods) {
        this.goods = goods;
    }

    public LitemallGoodsProduct[] getProducts() {
        return products;
    }

    public void setProducts(LitemallGoodsProduct[] products) {
        this.products = products;
    }

    public LitemallGoodsSpecification[] getSpecifications() {
        return specifications;
    }

    public void setSpecifications(LitemallGoodsSpecification[] specifications) {
        this.specifications = specifications;
    }

    public LitemallGoodsAttribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(LitemallGoodsAttribute[] attributes) {
        this.attributes = attributes;
    }

}
