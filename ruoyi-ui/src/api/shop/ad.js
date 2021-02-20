import request from '@/utils/request'

// 查询广告列表
export function listAd(query) {
  return request({
    url: '/shop/ad/list',
    method: 'get',
    params: query
  })
}

// 查询广告详细
export function getAd(id) {
  return request({
    url: '/shop/ad/' + id,
    method: 'get'
  })
}

// 新增广告
export function addAd(data) {
  return request({
    url: '/shop/ad',
    method: 'post',
    data: data
  })
}

// 修改广告
export function updateAd(data) {
  return request({
    url: '/shop/ad',
    method: 'put',
    data: data
  })
}

// 删除广告
export function delAd(id) {
  return request({
    url: '/shop/ad/' + id,
    method: 'delete'
  })
}

// 导出广告
export function exportAd(query) {
  return request({
    url: '/shop/ad/export',
    method: 'get',
    params: query
  })
}