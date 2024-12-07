import request from '@/utils/request'

const api_name = '/system/sysDic'
export default {
  getPageList(page, limit, searchObj) {
    return request({
      url: `${api_name}/${page}/${limit}`,
      method: 'get',
      params: searchObj // url查询字符串或表单键值对
    })
  },
  getAllSysDic() {
    return request({
      url: `${api_name}/findAllSpec`,
      method: 'get'
    })
  },
  getById(id) {
    return request({
      url: `${api_name}/get/${id}`,
      method: 'get'
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
  updateStatus(id, status) {
    return request({
      url: `${api_name}/updateStatus/${id}/${status}`,
      method: 'get'
    })
  }
}
