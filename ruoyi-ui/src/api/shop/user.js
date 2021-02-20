import request from '@/utils/request'

// 查询优惠券用户使用列表
export function listUser(query) {
  return request({
    url: '/shop/user/list',
    method: 'get',
    params: query
  })
}

// 查询优惠券用户使用详细
export function getUser(id) {
  return request({
    url: '/shop/user/' + id,
    method: 'get'
  })
}

// 新增优惠券用户使用
export function addUser(data) {
  return request({
    url: '/shop/user',
    method: 'post',
    data: data
  })
}

// 修改优惠券用户使用
export function updateUser(data) {
  return request({
    url: '/shop/user',
    method: 'put',
    data: data
  })
}

// 删除优惠券用户使用
export function delUser(id) {
  return request({
    url: '/shop/user/' + id,
    method: 'delete'
  })
}

// 导出优惠券用户使用
export function exportUser(query) {
  return request({
    url: '/shop/user/export',
    method: 'get',
    params: query
  })
}