import request from '@/utils/request'

// 查询商品货品列表
export function listProduct(query) {
  return request({
    url: '/shop/product/list',
    method: 'get',
    params: query
  })
}

// 查询商品货品详细
export function getProduct(id) {
  return request({
    url: '/shop/product/' + id,
    method: 'get'
  })
}

// 新增商品货品
export function addProduct(data) {
  return request({
    url: '/shop/product',
    method: 'post',
    data: data
  })
}

// 修改商品货品
export function updateProduct(data) {
  return request({
    url: '/shop/product',
    method: 'put',
    data: data
  })
}

// 删除商品货品
export function delProduct(id) {
  return request({
    url: '/shop/product/' + id,
    method: 'delete'
  })
}

// 导出商品货品
export function exportProduct(query) {
  return request({
    url: '/shop/product/export',
    method: 'get',
    params: query
  })
}