import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";

// 查询文法列表
export function listBunpou(query) {
  return request({
    url: '/nihongo/bunpou/list',
    method: 'get',
    params: query
  })
}

// 修改用例
export function updateExample(data) {
  return request({
    url: '/nihongo/bunpou/updateExample',
    method: 'put',
    data: data
  })
}

// 新增用例
export function addExample(data) {
  return request({
    url: '/nihongo/bunpou/addExample',
    method: 'post',
    data: data
  })
}

// 修改用法
export function updateUse(data) {
  return request({
    url: '/nihongo/bunpou/updateUse',
    method: 'put',
    data: data
  })
}

// 新增用法
export function addUse(data) {
  return request({
    url: '/nihongo/bunpou/addUse',
    method: 'post',
    data: data
  })
}

// 修改文法
export function updateBunpou(data) {
  return request({
    url: '/nihongo/bunpou/updateBunpou',
    method: 'put',
    data: data
  })
}

// 新增文法
export function addBunpou(data) {
  return request({
    url: '/nihongo/bunpou/addBunpou',
    method: 'post',
    data: data
  })
}

// 获取文法类型
export function listType(query) {
  return request({
    url: '/nihongo/general/types',
    method: 'get',
    params: query
  })
}