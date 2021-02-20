import request from '@/utils/request'

// 查询系统配置列表
export function listSystem(query) {
  return request({
    url: '/shop/config/list',
    method: 'get',
    params: query
  })
}

// 查询系统配置详细
export function getSystem(id) {
  return request({
    url: '/shop/config/' + id,
    method: 'get'
  })
}

// 新增系统配置
export function addSystem(data) {
  return request({
    url: '/shop/config',
    method: 'post',
    data: data
  })
}

// 修改系统配置
export function updateSystem(data) {
  return request({
    url: '/shop/config',
    method: 'put',
    data: data
  })
}

// 删除系统配置
export function delSystem(id) {
  return request({
    url: '/shop/config/' + id,
    method: 'delete'
  })
}

// 导出系统配置
export function exportSystem(query) {
  return request({
    url: '/shop/config/export',
    method: 'get',
    params: query
  })
}
