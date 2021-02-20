import request from '@/utils/request'

// 查询优惠券信息及规则列表
export function listCoupon(query) {
  return request({
    url: '/shop/coupon/list',
    method: 'get',
    params: query
  })
}

// 查询优惠券信息及规则详细
export function readCoupon(id) {
  return request({
    url: '/shop/coupon/' + id,
    method: 'get'
  })
}

// 新增优惠券信息及规则
export function addCoupon(data) {
  return request({
    url: '/shop/coupon',
    method: 'post',
    data: data
  })
}

// 修改优惠券信息及规则
export function updateCoupon(data) {
  return request({
    url: '/shop/coupon',
    method: 'put',
    data: data
  })
}

// 删除优惠券信息及规则
export function delCoupon(id) {
  return request({
    url: '/shop/coupon/' + id,
    method: 'delete'
  })
}

export function listCouponUser(query) {
  return request({
    url: '/shop/couponUser/list',
    method: 'get',
    params: query
  })
}


// 导出优惠券信息及规则
export function exportCoupon(query) {
  return request({
    url: '/shop/coupon/export',
    method: 'get',
    params: query
  })
}
