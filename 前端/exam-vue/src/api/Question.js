import request from '@/utils/request'

const api_name = '/Question'
export default {
  getPageList(page, limit, searchObj) {
    return request({
      url: `${api_name}/${page}/${limit}`,
      method: 'get',
      params: searchObj // url查询字符串或表单键值对
    })
  },
  getAllTbQuestion(searchObj) {
    return request({
      url: `${api_name}/list`,
      method: 'get',
      params: searchObj
    })
  },
  getById(id) {
    return request({
      url: `${api_name}/get/${id}`,
      method: 'get'
    })
  },
  getByIds(ids) {
    return request({
      url: `${api_name}/getByIds`,
      method: 'post',
      data: ids
    })
  },
  save(data) {
    return request({
      url: `${api_name}/save`,
      method: 'post',
      data: data
    })
  },
  updateById(data) {
    return request({
      url: `${api_name}/update`,
      method: 'put',
      data: data
    })
  },
  removeById(id) {
    return request({
      url: `${api_name}/remove/${id}`,
      method: 'delete'
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

  //上传文件
  uploadFile(data) {
    return request({
      url: `${api_name}/import`,
      method: 'post',
      data
    })
  },

  updateStatus(id, status) {
    return request({
      url: `${api_name}/updateStatus/${id}/${status}`,
      method: 'get'
    })
  }
}
