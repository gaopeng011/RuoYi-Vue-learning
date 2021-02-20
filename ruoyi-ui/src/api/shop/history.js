import request from '@/utils/request'

// 查询搜索历史列表
export function listHistory(query) {
  return request({
    url: '/shop/history/list',
    method: 'get',
    params: query
  })
}

// 查询搜索历史详细
export function getHistory(id) {
  return request({
    url: '/shop/history/' + id,
    method: 'get'
  })
}

// 新增搜索历史
export function addHistory(data) {
  return request({
    url: '/shop/history',
    method: 'post',
    data: data
  })
}

// 修改搜索历史
export function updateHistory(data) {
  return request({
    url: '/shop/history',
    method: 'put',
    data: data
  })
}

// 删除搜索历史
export function delHistory(id) {
  return request({
    url: '/shop/history/' + id,
    method: 'delete'
  })
}

// 导出搜索历史
export function exportHistory(query) {
  return request({
    url: '/shop/history/export',
    method: 'get',
    params: query
  })
}