import request from '@/utils/request'

// 查询收藏列表
export function listCollect(query) {
  return request({
    url: '/shop/collect/list',
    method: 'get',
    params: query
  })
}

// 查询收藏详细
export function getCollect(id) {
  return request({
    url: '/shop/collect/' + id,
    method: 'get'
  })
}

// 新增收藏
export function addCollect(data) {
  return request({
    url: '/shop/collect',
    method: 'post',
    data: data
  })
}

// 修改收藏
export function updateCollect(data) {
  return request({
    url: '/shop/collect',
    method: 'put',
    data: data
  })
}

// 删除收藏
export function delCollect(id) {
  return request({
    url: '/shop/collect/' + id,
    method: 'delete'
  })
}

// 导出收藏
export function exportCollect(query) {
  return request({
    url: '/shop/collect/export',
    method: 'get',
    params: query
  })
}