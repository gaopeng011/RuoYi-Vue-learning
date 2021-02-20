import request from '@/utils/request'

// 查询商品基本信息列表
export function listGoods(query) {
  return request({
    url: '/shop/goods/list',
    method: 'get',
    params: query
  })
}

// 查询商品基本信息详细
export function getGoods(id) {
  return request({
    url: '/shop/goods/detail/' + id,
    method: 'get'
  })
}

// 新增商品基本信息
export function addGoods(data) {
  return request({
    url: '/shop/goods',
    method: 'post',
    data: data
  })
}

// 修改商品基本信息
export function editGoods(data) {
  return request({
    url: '/shop/goods/update',
    method: 'post',
    data: data
  })
}

// 删除商品基本信息
export function delGoods(id) {
  return request({
    url: '/shop/goods/del/' + id,
    method: 'delete'
  })
}

export function publishGoods(data) {
  return request({
    url: '/shop/goods/create',
    method: 'post',
    data
  })
}

export function detailGoods(id) {
  return request({
    url: '/shop/goods/detail',
    method: 'get',
    params: { id }
  })
}



export function listCatAndBrand() {
  return request({
    url: '/shop/goods/catAndBrand',
    method: 'get'
  })
}

// 导出商品基本信息
export function exportGoods(query) {
  return request({
    url: '/shop/goods/export',
    method: 'get',
    params: query
  })
}
