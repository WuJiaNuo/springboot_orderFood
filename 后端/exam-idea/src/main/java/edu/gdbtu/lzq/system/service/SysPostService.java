package edu.gdbtu.lzq.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdbtu.lzq.system.model.SysPost;
import edu.gdbtu.lzq.system.vo.SysPostQueryVo;

import java.util.List;
/**
* @author tanlingfei
* @version 1.0
* @description 岗位信息表 Service接口
* @date 2023-04-30 12:37:35
*/
public interface SysPostService extends IService<SysPost> {
    IPage<SysPost> selectPage(Page<SysPost> pageParam, SysPostQueryVo queryVo);
    List<SysPost> queryList(SysPostQueryVo queryVo);
    public boolean save(SysPost sysPost);
    public boolean updateById(SysPost sysPost);
    public SysPost getById(String id);
    public List<SysPost> getByIds(List<String> ids);
    //void updateStatus(String id, Integer status);
}
