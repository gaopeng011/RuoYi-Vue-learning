import request from '@/utils/request'
import { getToken } from '@/utils/auth'



// 微信服务器提交文件存储
export function createStorageWx(data) {
  return request({
    url: '/merchant/upload',
    method: 'post',
    data: data,
  })
}


// 商户进件申请
export function merchantApplaymentWx(data) {
  return request({
    url: '/merchant/applayment/add',
    method: 'post',
    data: data,
  })
}

