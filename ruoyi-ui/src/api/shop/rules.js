import request from '@/utils/request'

// 查询团购规则列表
export function listRules(query) {
  return request({
    url: '/shop/rules/list',
    method: 'get',
    params: query
  })
}

// 查询团购规则详细
export function getRules(id) {
  return request({
    url: '/shop/rules/' + id,
    method: 'get'
  })
}

// 新增团购规则
export function addRules(data) {
  return request({
    url: '/shop/rules',
    method: 'post',
    data: data
  })
}

// 修改团购规则
export function updateRules(data) {
  return request({
    url: '/shop/rules',
    method: 'put',
    data: data
  })
}

// 删除团购规则
export function delRules(id) {
  return request({
    url: '/shop/rules/' + id,
    method: 'delete'
  })
}

// 导出团购规则
export function exportRules(query) {
  return request({
    url: '/shop/rules/export',
    method: 'get',
    params: query
  })
}