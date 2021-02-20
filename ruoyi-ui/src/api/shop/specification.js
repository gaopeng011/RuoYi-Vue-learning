import request from '@/utils/request'

// 查询商品规格列表
export function listSpecification(query) {
  return request({
    url: '/shop/specification/list',
    method: 'get',
    params: query
  })
}

// 查询商品规格详细
export function getSpecification(id) {
  return request({
    url: '/shop/specification/' + id,
    method: 'get'
  })
}

// 新增商品规格
export function addSpecification(data) {
  return request({
    url: '/shop/specification',
    method: 'post',
    data: data
  })
}

// 修改商品规格
export function updateSpecification(data) {
  return request({
    url: '/shop/specification',
    method: 'put',
    data: data
  })
}

// 删除商品规格
export function delSpecification(id) {
  return request({
    url: '/shop/specification/' + id,
    method: 'delete'
  })
}

// 导出商品规格
export function exportSpecification(query) {
  return request({
    url: '/shop/specification/export',
    method: 'get',
    params: query
  })
}