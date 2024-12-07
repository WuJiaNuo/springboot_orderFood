import request from '@/utils/request'

//常量
const api_name = '/admin/system/sysUser'

export default {
  //列表
  getPageList(page, limit, searchObj) {
    return request({
      //接口路径
      url: `${api_name}/${page}/${limit}`,
      method: 'get', //提交方式
      //参数
      params: searchObj
    })
  },
  //添加
  save(user) {
    return request({
      //接口路径
      url: `${api_name}/save`,
      method: 'post', //提交方式
      //参数
      data: user
    })
  },
  //根据id查询
  getUserId(id) {
    return request({
      //接口路径
      url: `${api_name}/getUser/${id}`,
      method: 'get' //提交方式
    })
  },
  getByIds(ids) {
    return request({
      url: `${api_name}/getByIds`,
      method: 'post',
      data: ids
    })
  },
  //修改
  update(user) {
    return request({
      //接口路径
      url: `${api_name}/update`,
      method: 'post', //提交方式
      //参数
      data: user
    })
  },
  //删除
  removeById(id) {
    return request({
      //接口路径
      url: `${api_name}/remove/${id}`,
      method: 'delete' //提交方式
    })
  },

  //批量删除
  batchRemove(idList) {
    return request({
      url: `${api_name}/batchRemove`,
      method: 'delete',
      data: idList
    })
  },
  //更改用户状态
  updateStatus(id, status) {
    return request({
      //接口路径
      url: `${api_name}/updateStatus/${id}/${status}`,
      method: 'get' //提交方式
    })
  },
  //上传文件
  uploadFile(data) {
    return request({
      url: `${api_name}/import`,
      method: 'post',
      data
    })
  }
}
