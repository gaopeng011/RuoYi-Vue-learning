import request from '@/utils/request'

// 查询常见问题列表
export function listIssue(query) {
  return request({
    url: '/shop/issue/list',
    method: 'get',
    params: query
  })
}

// 查询常见问题详细
export function getIssue(id) {
  return request({
    url: '/shop/issue/' + id,
    method: 'get'
  })
}

// 新增常见问题
export function addIssue(data) {
  return request({
    url: '/shop/issue',
    method: 'post',
    data: data
  })
}

// 修改常见问题
export function updateIssue(data) {
  return request({
    url: '/shop/issue',
    method: 'put',
    data: data
  })
}

// 删除常见问题
export function delIssue(id) {
  return request({
    url: '/shop/issue/' + id,
    method: 'delete'
  })
}

// 导出常见问题
export function exportIssue(query) {
  return request({
    url: '/shop/issue/export',
    method: 'get',
    params: query
  })
}