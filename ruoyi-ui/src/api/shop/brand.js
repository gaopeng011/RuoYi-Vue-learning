import request from '@/utils/request'

// 查询品牌商列表
export function listBrand(query) {
  return request({
    url: '/shop/brand/list',
    method: 'get',
    params: query
  })
}

// 查询品牌商详细
export function getBrand(id) {
  return request({
    url: '/shop/brand/' + id,
    method: 'get'
  })
}

// 新增品牌商
export function addBrand(data) {
  return request({
    url: '/shop/brand',
    method: 'post',
    data: data
  })
}

// 修改品牌商
export function updateBrand(data) {
  return request({
    url: '/shop/brand',
    method: 'put',
    data: data
  })
}

// 删除品牌商
export function delBrand(id) {
  return request({
    url: '/shop/brand/' + id,
    method: 'delete'
  })
}

// 导出品牌商
export function exportBrand(query) {
  return request({
    url: '/shop/brand/export',
    method: 'get',
    params: query
  })
}