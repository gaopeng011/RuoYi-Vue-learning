import request from '@/utils/request'

// 查询评论列表
export function listComment(query) {
  return request({
    url: '/shop/comment/list',
    method: 'get',
    params: query
  })
}

// 查询评论详细
export function getComment(id) {
  return request({
    url: '/shop/comment/' + id,
    method: 'get'
  })
}

// 新增评论
export function addComment(data) {
  return request({
    url: '/shop/comment',
    method: 'post',
    data: data
  })
}

// 修改评论
export function updateComment(data) {
  return request({
    url: '/shop/comment',
    method: 'put',
    data: data
  })
}

// 删除评论
export function delComment(id) {
  return request({
    url: '/shop/comment/' + id,
    method: 'delete'
  })
}

// 导出评论
export function exportComment(query) {
  return request({
    url: '/shop/comment/export',
    method: 'get',
    params: query
  })
}