import request from '@/utils/request'

// 查询专题列表
export function listTopic(query) {
  return request({
    url: '/shop/topic/list',
    method: 'get',
    params: query
  })
}

// 查询专题详细
export function readTopic(id) {
  return request({
    url: '/shop/topic/' + id,
    method: 'get'
  })
}

// 新增专题
export function createTopic(data) {
  return request({
    url: '/shop/topic',
    method: 'post',
    data: data
  })
}

// 修改专题
export function updateTopic(data) {
  return request({
    url: '/shop/topic',
    method: 'put',
    data: data
  })
}

// 删除专题
export function delTopic(id) {
  return request({
    url: '/shop/topic/' + id,
    method: 'delete'
  })
}

// 导出专题
export function exportTopic(query) {
  return request({
    url: '/shop/topic/export',
    method: 'get',
    params: query
  })
}
