import request from '@/utils/request'

/*
部门管理相关的API请求函数
*/
const api_name = '/admin/system/sysDept'

export default {

  /*
  获取权限(部门/功能)列表
  */
  findNodes() {
    return request({
      url: `${api_name}/findNodes`,
      method: 'get'
    })
  },

    /*
  获取权限(部门/功能)列表
  */
  findSelectNodes() {
    return request({
      url: `${api_name}/findSelectNodes`,
      method: 'get'
    })
  },

  /*
获取权限(部门/功能)列表
*/
  findNodesByParent(parentId) {
    return request({
      url: `${api_name}/findNodesByParent/${parentId}`,
      method: 'get'
    })
  },

  getById(id) {
    return request({
      url: `${api_name}/get/${id}`,
      method: 'get'
    })
  },

  /*
  删除
  */
  removeById(id) {
    return request({
      url: `${api_name}/remove/${id}`,
      method: "delete"
    })
  },

  /*
  保存
  */
  save(sysMenu) {
    return request({
      url: `${api_name}/save`,
      method: "post",
      data: sysMenu
    })
  },

  /*
  更新
  */
  updateById(sysMenu) {
    return request({
      url: `${api_name}/update`,
      method: "put",
      data: sysMenu
    })
  }
}
