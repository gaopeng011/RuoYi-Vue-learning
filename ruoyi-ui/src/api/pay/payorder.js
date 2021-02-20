import request from '@/utils/request'

// 查询订单列表
export function listOrder(query) {
  return request({
    url: '/pay/payorder/list',
    method: 'get',
    params: query
  })
}

// 查询订单详细
export function getOrder(mchId) {
  return request({
    url: '/pay/payorder/' + mchId,
    method: 'get'
  })
}

// 导出订单
export function exportOrder(query) {
  return request({
    url: '/pay/payorder/export',
    method: 'get',
    params: query
  })
}
