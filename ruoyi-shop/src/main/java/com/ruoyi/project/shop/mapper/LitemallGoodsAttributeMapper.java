package com.ruoyi.project.shop.mapper;

import com.ruoyi.project.shop.domain.LitemallGoodsAttribute;

import java.util.List;

/**
 * 商品参数Mapper接口
 *
 * @author ruoyi
 * @date 2020-03-11
 */
public interface LitemallGoodsAttributeMapper
{
    /**
     * 查询商品参数
     *
     * @param id 商品参数ID
     * @return 商品参数
     */
    public LitemallGoodsAttribute selectLitemallGoodsAttributeById(Long id);

    /**
     * @Author hanguanglei
     * @Description //通过goodsId进行查询
     * @Date 14:02 2020/3/16
     * @Param [id]
     * @return java.util.List<com.ruoyi.project.shop.domain.LitemallGoodsAttribute>
     **/

    public List<LitemallGoodsAttribute> queryByGid(Long goodsid);
    /**
     * 查询商品参数列表
     *
     * @param litemallGoodsAttribute 商品参数
     * @return 商品参数集合
     */
    public List<LitemallGoodsAttribute> selectLitemallGoodsAttributeList(LitemallGoodsAttribute litemallGoodsAttribute);

    /**
     * 新增商品参数
     *
     * @param litemallGoodsAttribute 商品参数
     * @return 结果
     */
    public int insertLitemallGoodsAttribute(LitemallGoodsAttribute litemallGoodsAttribute);

    /**
     * 修改商品参数
     *
     * @param litemallGoodsAttribute 商品参数
     * @return 结果
     */
    public int updateLitemallGoodsAttribute(LitemallGoodsAttribute litemallGoodsAttribute);

    /**
     * 删除商品参数
     *
     * @param id 商品参数ID
     * @return 结果
     */
    public int deleteLitemallGoodsAttributeById(Long id);

    /**
     * 批量删除商品参数
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLitemallGoodsAttributeByIds(Long[] ids);
}
