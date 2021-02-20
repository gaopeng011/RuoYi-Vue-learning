import request from '@/utils/request'

// 查询用户浏览足迹列表
export function listFootprint(query) {
  return request({
    url: '/shop/footprint/list',
    method: 'get',
    params: query
  })
}

// 查询用户浏览足迹详细
export function getFootprint(id) {
  return request({
    url: '/shop/footprint/' + id,
    method: 'get'
  })
}

// 新增用户浏览足迹
export function addFootprint(data) {
  return request({
    url: '/shop/footprint',
    method: 'post',
    data: data
  })
}

// 修改用户浏览足迹
export function updateFootprint(data) {
  return request({
    url: '/shop/footprint',
    method: 'put',
    data: data
  })
}

// 删除用户浏览足迹
export function delFootprint(id) {
  return request({
    url: '/shop/footprint/' + id,
    method: 'delete'
  })
}

// 导出用户浏览足迹
export function exportFootprint(query) {
  return request({
    url: '/shop/footprint/export',
    method: 'get',
    params: query
  })
}