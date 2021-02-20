import request from '@/utils/request'

// 查询商品参数列表
export function listAttribute(query) {
  return request({
    url: '/shop/attribute/list',
    method: 'get',
    params: query
  })
}

// 查询商品参数详细
export function getAttribute(id) {
  return request({
    url: '/shop/attribute/' + id,
    method: 'get'
  })
}

// 新增商品参数
export function addAttribute(data) {
  return request({
    url: '/shop/attribute',
    method: 'post',
    data: data
  })
}

// 修改商品参数
export function updateAttribute(data) {
  return request({
    url: '/shop/attribute',
    method: 'put',
    data: data
  })
}

// 删除商品参数
export function delAttribute(id) {
  return request({
    url: '/shop/attribute/' + id,
    method: 'delete'
  })
}

// 导出商品参数
export function exportAttribute(query) {
  return request({
    url: '/shop/attribute/export',
    method: 'get',
    params: query
  })
}