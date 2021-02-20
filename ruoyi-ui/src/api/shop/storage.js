import request from '@/utils/request'
import { getToken } from '@/utils/auth'
// 查询文件存储列表
export function listStorage(query) {
  return request({
    url: '/shop/storage/list',
    method: 'get',
    params: query
  })
}

// 查询文件存储详细
export function getStorage(id) {
  return request({
    url: '/shop/storage/' + id,
    method: 'get'
  })
}

// 新增文件存储
export function createStorage(data) {
  return request({
    url: '/shop/storage/create',
    method: 'post',
    data: data,
  })
}
// 微信服务器提交文件存储
export function createStorageWx(data) {
  return request({
    url: '/shop/storage/createwx',
    method: 'post',
    data: data,
  })
}


export function readStorage(data) {
  return request({
    url: '/storage/read',
    method: 'get',
    data
  })
}
// 修改文件存储
export function updateStorage(data) {
  return request({
    url: '/shop/storage/update',
    method: 'put',
    data: data
  })
}

// 删除文件存储
export function delStorage(id) {
  return request({
    url: '/shop/storage/' + id,
    method: 'delete'
  })
}

// 导出文件存储
export function exportStorage(query) {
  return request({
    url: '/shop/storage/export',
    method: 'get',
    params: query
  })
}


const uploadPath = process.env.VUE_APP_BASE_API + '/shop/storage/create'
const uploadPathwx = process.env.VUE_APP_BASE_API + '/shop/merchant/upload'
export { uploadPath,uploadPathwx }



