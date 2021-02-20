import request from '@/utils/request'

// 查询管理员列表
export function listAdmin(query) {
  return request({
    url: '/shop/admin/list',
    method: 'get',
    params: query
  })
}

// 查询管理员详细
export function getAdmin(id) {
  return request({
    url: '/shop/admin/' + id,
    method: 'get'
  })
}

// 新增管理员
export function addAdmin(data) {
  return request({
    url: '/shop/admin',
    method: 'post',
    data: data
  })
}

// 修改管理员
export function updateAdmin(data) {
  return request({
    url: '/shop/admin',
    method: 'put',
    data: data
  })
}

// 删除管理员
export function delAdmin(id) {
  return request({
    url: '/shop/admin/' + id,
    method: 'delete'
  })
}

// 导出管理员
export function exportAdmin(query) {
  return request({
    url: '/shop/admin/export',
    method: 'get',
    params: query
  })
}