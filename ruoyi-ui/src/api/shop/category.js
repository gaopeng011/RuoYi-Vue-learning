import request from '@/utils/request'

// 查询类目列表
export function listCategory(query) {
  return request({
    url: '/shop/category/list',
    method: 'get',
    params: query
  })
}

export function listCatL1() {
  return request({
    url: '/shop/category/l1',
    method: 'get'
  })
}

// 查询类目详细
export function getCategory(id) {
  return request({
    url: '/shop/category/' + id,
    method: 'get'
  })
}

// 新增类目
export function addCategory(data) {
  return request({
    url: '/shop/category',
    method: 'post',
    data: data
  })
}

// 修改类目
export function updateCategory(data) {
  return request({
    url: '/shop/category',
    method: 'put',
    data: data
  })
}

// 删除类目
export function delCategory(id) {
  return request({
    url: '/shop/category/' + id,
    method: 'put'
  })
}

// 导出类目
export function exportCategory(query) {
  return request({
    url: '/shop/category/export',
    method: 'get',
    params: query
  })
}
