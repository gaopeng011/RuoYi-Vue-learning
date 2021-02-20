import request from '@/utils/request'

// 查询团购活动列表
export function listGroupon(query) {
  return request({
    url: '/shop/groupon/list',
    method: 'get',
    params: query
  })
}

// 查询团购活动详细
export function getGroupon(id) {
  return request({
    url: '/shop/groupon/' + id,
    method: 'get'
  })
}

// 新增团购活动
export function addGroupon(data) {
  return request({
    url: '/shop/groupon',
    method: 'post',
    data: data
  })
}

// 修改团购活动
export function updateGroupon(data) {
  return request({
    url: '/shop/groupon',
    method: 'put',
    data: data
  })
}

// 删除团购活动
export function delGroupon(id) {
  return request({
    url: '/shop/groupon/' + id,
    method: 'delete'
  })
}

// 导出团购活动
export function exportGroupon(query) {
  return request({
    url: '/shop/groupon/export',
    method: 'get',
    params: query
  })
}