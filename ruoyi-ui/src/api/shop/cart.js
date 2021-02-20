import request from '@/utils/request'

// 查询购物车商品列表
export function listCart(query) {
  return request({
    url: '/shop/cart/list',
    method: 'get',
    params: query
  })
}

// 查询购物车商品详细
export function getCart(id) {
  return request({
    url: '/shop/cart/' + id,
    method: 'get'
  })
}

// 新增购物车商品
export function addCart(data) {
  return request({
    url: '/shop/cart',
    method: 'post',
    data: data
  })
}

// 修改购物车商品
export function updateCart(data) {
  return request({
    url: '/shop/cart',
    method: 'put',
    data: data
  })
}

// 删除购物车商品
export function delCart(id) {
  return request({
    url: '/shop/cart/' + id,
    method: 'delete'
  })
}

// 导出购物车商品
export function exportCart(query) {
  return request({
    url: '/shop/cart/export',
    method: 'get',
    params: query
  })
}