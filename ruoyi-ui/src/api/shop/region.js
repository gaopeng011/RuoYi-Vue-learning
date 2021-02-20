import request from '@/utils/request'

// 查询行政区域列表
export function listRegion(query) {
  return request({
    url: '/shop/region/list',
    method: 'get',
    params: query
  })
}



export function listRegionJl(query) {
  return request({
    url: '/shop/region/listRegoin',
    method: 'get',
    params: query
  })
}
// 查询行政区域详细
export function getRegion(id) {
  return request({
    url: '/shop/region/' + id,
    method: 'get'
  })
}

// 新增行政区域
export function addRegion(data) {
  return request({
    url: '/shop/region',
    method: 'post',
    data: data
  })
}

// 修改行政区域
export function updateRegion(data) {
  return request({
    url: '/shop/region',
    method: 'put',
    data: data
  })
}

// 删除行政区域
export function delRegion(id) {
  return request({
    url: '/shop/region/' + id,
    method: 'delete'
  })
}

// 导出行政区域
export function exportRegion(query) {
  return request({
    url: '/shop/region/export',
    method: 'get',
    params: query
  })
}
